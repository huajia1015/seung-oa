package com.oa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.domain.User;
import com.oa.log.SysLog;
import com.oa.service.IUserService;
import com.oa.util.MD5CoderUtils;
import com.oa.util.StringUtils;
import com.oa.util.constants.Constants;

/**
 * 用户 - Controller
 * 
 * @author Dwen
 * @version v 0.1 2013-8-1 上午10:27:59
 */
@Controller
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sys/index");
		return mav;
	}
	
	/**
	 * 登录
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, User user) {
		ModelMap mmap = new ModelMap();
		//验证码
		if (!StringUtils.hasText(user.getCheckCode())) {
			mmap.put("LoginStatus", "100");//验证码为空
			SysLog.info("Login Code is Null.");
			return new ModelAndView("redirect:login.jsp", mmap);
		}
		//验证码验证
		String checkCodeSession = String.valueOf(request.getSession().getAttribute(Constants.LOGIN_CHECK_CODE));
		if (!StringUtils.hasText(checkCodeSession) || !checkCodeSession.equals(user.getCheckCode())) {
			mmap.put("LoginStatus", "101");//验证码错误
			SysLog.info("Login Code is Error.");
			return new ModelAndView("redirect:login.jsp", mmap);
		}
	      //用户名
		if (!StringUtils.hasText(user.getUserName())) {
			mmap.put("LoginStatus", "102");//用户名不能为空
			SysLog.info("UserName is Null.");
			return new ModelAndView("redirect:login.jsp", mmap);
		}
		//密码
		if (!StringUtils.hasText(user.getPassword())) {
			mmap.put("LoginStatus", "103");//密码不能为空
			SysLog.info("Password is Null.");
			return new ModelAndView("redirect:login.jsp", mmap);
		}
		
		try {
			//密码MD5加密
			user.setPassword(MD5CoderUtils.encodeMD5Hex(user.getPassword()));
		} catch (Exception e) {
			SysLog.error("Password Encrypt Failure!!!");
		}
		// 登录
		User user2 = userService.login(user);
		if (null == user2) {
			mmap.put("LoginStatus", "104");//用户名或密码错误
			SysLog.info("UserName and Pass are Error！");
			return new ModelAndView("redirect:login.jsp", mmap);
		} else {
			SysLog.info("Login Success！");
			//保存用户session
			setSessionUser(request, user2);
			//更新登录状态
			user2.setIp(getIp(request));
			user2.setIsLogin(Constants.HAD_LOGIN);
			userService.updateLogin(user2);
			return new ModelAndView("redirect:index.html", mmap);
		}
	}
	
	/**
	 * 注销
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView loginout(HttpServletRequest request) {
		User user = getSessionUser(request);
		//移除用户Session
		removeSessionUser(request);
		//更新登出状态
		user.setIsLogin(Constants.HAD_LOGOUT);
		userService.logout(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sys/index");
		return mav;
	}
	
	/**
	 * 修改密码 - 页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/modify-pwd-page")
	public String modifyPwdPage() {
		return "sys/modify-pwd";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/modify-pwd")
	public ModelAndView modifyPwd(HttpServletRequest request,User user) {
		User user1 = getSessionUser(request);//获得用户
		ModelAndView mav = new ModelAndView();
		String password = request.getParameter("password");//旧密码
		String newPassword = request.getParameter("newPassword");//新密码
		String reNewPassword = request.getParameter("reNewPassword");//重复新密码
		try {
			if (!StringUtils.hasLength(password) || !user1.getPassword().equals(MD5CoderUtils.encodeMD5Hex(password))) {
				mav.addObject("ModifyPwdStatus", "100");//旧密码不正确
				mav.setViewName("sys/modify-pwd");
				return mav;
			}
			if (!StringUtils.hasLength(newPassword) || !newPassword.equals(reNewPassword)) {
				mav.addObject("ModifyPwdStatus", "101");//新密码与重复新密码不一致
				mav.setViewName("sys/modify-pwd");
				return mav;
			}
			user.setId(user1.getId());
			user.setPassword(MD5CoderUtils.encodeMD5Hex(newPassword));
			//修改密码
			userService.modifyPwd(user);
			//移除用户Session
			removeSessionUser(request);
			ModelMap mmap = new ModelMap();
			mmap.put("ModifyPwdStatus", "200");//修改密码成功
			return new ModelAndView("redirect:login.jsp", mmap);
		} catch (Exception e) {
			mav.addObject("ModifyPwdStatus", "500");//修改密码失败
			mav.setViewName("sys/modify-pwd");
			SysLog.error("Modify Password Error! "+e.getMessage());
			return mav;
		}
	}
	
	/**
	 * 添加用户 - 页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/add-user-page")
	public String addUserPage() {
		return "sys/add-user";
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/add-user")
	public ModelAndView addUser(HttpServletRequest request,User user) {
		ModelAndView mav = new ModelAndView();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");//密码
		String authority = request.getParameter("authority");//用户类型
		try {
			if (!StringUtils.hasLength(userName)) {
				mav.addObject("AddUserStatus", "100");//用户名不能为空
				mav.setViewName("sys/add-user");
				return mav;
			}
			if (!StringUtils.hasLength(password)) {
				mav.addObject("AddUserStatus", "101");//密码不能为空
				mav.setViewName("sys/add-user");
				return mav;
			}
			user.setUserName(userName);
			user.setPassword(MD5CoderUtils.encodeMD5Hex(password));
			user.setAuthority(authority);
			//修改密码
			boolean result = userService.addUser(user);
			if (result) {
				mav.addObject("AddUserStatus", "200");//添加用户成功
			}else{
				mav.addObject("AddUserStatus", "500");
			}
			
			mav.setViewName("sys/add-user");
			return mav;
		} catch (Exception e) {
			mav.addObject("AddUserStatus", "500");
			mav.setViewName("sys/add-user");
			SysLog.error("添加用户错误! "+e.getMessage());
			return mav;
		}
	}
	
}
