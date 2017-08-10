package com.oa.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.domain.Course;
import com.oa.domain.TeacherSignIn;
import com.oa.domain.User;
import com.oa.log.SysLog;
import com.oa.page.Page;
import com.oa.service.IStudentService;
import com.oa.service.ITeacherService;
import com.oa.service.IUserService;
import com.oa.util.DateUtil;
import com.oa.util.StringUtils;
import com.oa.util.constants.Constants;

/**
 * 老师 - Controller
 * 
 * @author Dwen
 * @version v 0.1 2013-8-7 下午06:31:05
 */
@Controller
public class TeacherController extends BaseController {

	@Autowired
	ITeacherService teacherService;
	@Autowired
	IStudentService studentService;
	@Autowired
	IUserService userService;
	
	/**
	 * 老师考勤 - 页面
	 * @return
	 */
	@RequestMapping("/teacher-sign-in-page")
	public ModelAndView teacherSignInPage() {
		ModelAndView mav = new ModelAndView();
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.setViewName("teacher/teacher-sign-in");
		return mav;
	}
	
	/**
	 * 添加老师考勤
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/teacher-sign-in")
	public ModelAndView teacherSignIn(HttpServletRequest request,TeacherSignIn signIn) {
		ModelAndView mav = new ModelAndView();
		//考勤日期
		if (StringUtils.hasText(signIn.getSignDateStr())) {
			try {
				signIn.setSignDate(DateUtil.convertStringToDate(signIn.getSignDateStr().trim()));
				if (StringUtils.hasText(signIn.getClassOn()) && StringUtils.hasText(signIn.getClassOff())) {
					//2013-08-15 8:00
					String startClass = signIn.getSignDateStr()+" "+signIn.getClassOn();
					String endClass = signIn.getSignDateStr()+" "+signIn.getClassOff();
					//计算课时
					signIn.setLesson(DateUtil.minutesToHour(startClass, endClass));
				}
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
	    //判断课程是否存在
		Course course = studentService.hadCourse(signIn.getCourseCode());
		TeacherSignIn teacherSignIn = null;
		if (course!=null) {
			//判断该考勤是否已经存在
			teacherSignIn = teacherService.hadTeacherSignIn(signIn);
		}
		if (course != null && teacherSignIn == null) {
			User user = getSessionUser(request);//登录用户
			signIn.setOp(user.getUserName());
			//添加老师考勤
			int result = teacherService.addTeacherSignIn(signIn);
			if (result > 0) {
				mav.addObject("addTeacherSignInSuccess", true);
			}
		}else if(course == null){//课程不存在
			mav.addObject("addTeacherSignInSuccess", "existCourse");
		}else if(teacherSignIn != null){//该老师考勤已存在
			mav.addObject("addTeacherSignInSuccess", "existTeacherSignIn");
		}
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.setViewName("teacher/teacher-sign-in");
		return mav;
	}
	
	/**
	 * 老师考勤统计查询 - 页面
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/teacher-sign-in-count-page")
	public ModelAndView teacherSignInCountPage(TeacherSignIn signIn) {
		ModelAndView mav = new ModelAndView();
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.setViewName("teacher/teacher-sign-in-count");
		return mav;
	}
	
	/**
	 * 老师考勤统计查询
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/teacher-sign-in-count")
	public ModelAndView teacherSignInCount(TeacherSignIn signIn) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.hasText(signIn.getStartAtStr()) && StringUtils.hasText(signIn.getEndAtStr())) {
			try {
				signIn.setStartAt(DateUtil.convertStringToDate(signIn.getStartAtStr().trim()));
				signIn.setEndAt(DateUtil.convertStringToDate(signIn.getEndAtStr().trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		//老师考勤统计查询
		List<TeacherSignIn> teacherSignInCount = teacherService.selectTeacherSignInCount(signIn);
		mav.addObject("teacherSignInCount", teacherSignInCount);
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.addObject("signIn", signIn);
		mav.setViewName("teacher/teacher-sign-in-count");
		return mav;
	}
	
	/**
	 * 老师考勤查询 - 页面
	 * @return
	 */
	@RequestMapping("/select-teacher-sign-in-page")
	public ModelAndView selectTeacherSignInPage() {
		ModelAndView mav = new ModelAndView();
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.setViewName("teacher/select-teacher-sign-in");
		return mav;
	}
	
	/**
	 * 老师考勤查询
	 * @param request
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/select-teacher-sign-in")
	public ModelAndView selectTeacherSignIn(HttpServletRequest request,TeacherSignIn signIn) {
		ModelAndView mav = new ModelAndView();
		//请求参数
		String courseCodeStr = request.getParameter("courseCode");//课程代号
		String teacherStr = request.getParameter("teacher");//老师
		String startAtStr = request.getParameter("startAtStr");//开始时间
		String endAtStr = request.getParameter("endAtStr");//结束时间
		String signDateStr = request.getParameter("signDateStr");//签到日期
		//时间条件
		if (StringUtils.hasText(startAtStr) && StringUtils.hasText(endAtStr)) {
			try {
				signIn.setStartAt(DateUtil.convertStringToDate(signIn.getStartAtStr().trim()));
				signIn.setEndAt(DateUtil.convertStringToDate(signIn.getEndAtStr().trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		//课程条件
		if (StringUtils.hasText(courseCodeStr)) {
			signIn.setCourseCode(courseCodeStr);
		}
		//老师条件
		if (StringUtils.hasText(teacherStr)) {
			signIn.setTeacher(teacherStr);
		}
		//签到日期
		if (StringUtils.hasText(signDateStr)) {
			try {
				signIn.setSignDate(DateUtil.convertStringToDate(signDateStr.trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		//老师考勤查询
		Page<TeacherSignIn> page = teacherService.selectTeacherSignIn(signIn);
		mav.addObject("page", page);
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.addObject("signIn", signIn);
		mav.setViewName("teacher/select-teacher-sign-in");
		return mav;
	}
}
