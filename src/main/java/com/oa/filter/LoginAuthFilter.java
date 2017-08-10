package com.oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oa.domain.User;
import com.oa.log.SysLog;
import com.oa.util.constants.Constants;
/**
 * 拦截用户是否登录
 * @author Dwen
 * @version v 0.1 2013-8-15 下午05:47:23
 */
public class LoginAuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SysLog.info("User login auth filter will start!");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURL().toString();
		SysLog.info("Request URL------------------" + url + " , "+request.getRequestURI());
		request.setAttribute("tab", remarkTab(request.getRequestURI()));
		//保证该过滤器在一次请求中只被调用一次
		if (req != null && req.getAttribute(Constants.FILTERED_REQUEST)!=null) {
			chain.doFilter(request, response);
		}else{
			//设置过滤标识，防止一次请求多次过滤
			req.setAttribute(Constants.FILTERED_REQUEST, Boolean.TRUE);
			//不过滤登录url
			if (!url.endsWith("login.html") && url.endsWith(".html")) {
				// 获取session
				User user = (User) request.getSession().getAttribute(Constants.CRYSTAL_USER_SESSION);
				if (null == user) {
					response.sendRedirect("login.jsp");
					return;
				}
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		SysLog.info("User login auth filter was destory!");
	}
	
	/**
	 * 功能菜单高亮
	 * @param url
	 * @return
	 */
	private static String remarkTab(String url){
		String tab = null;
		//学生签到
		String[] tab1 = {"/student-sign-in-page.html","/student-sign-in-info.html","/student-sign-in.html"};
		for (String string : tab1) {
			if (url.contains(string)) {
				tab = "1";
				return tab;
			}
		}
		//学生签到统计
		String[] tab2 = {"/student-sign-in-count-page.html","/student-sign-in-count.html"};
		for (String string : tab2) {
			if (url.contains(string)) {
				tab = "2";
				return tab;
			}
		}
		//学生签到查询
		String[] tab3 = {"/select-student-sign-in-page.html","/select-student-sign-in.html"};
		for (String string : tab3) {
			if (url.contains(string)) {
				tab = "3";
				return tab;
			}
		}
		//学生管理
		String[] tab4 = {"/student-manager.html","/select-student-course.html","/add-student-page.html","/add-student.html","/add-student-course-page.html","/add-student-course.html","/update-student-course-page.html","/update-student-course.html","/del-student-course.html","/select-student-by-id.html"};
		for (String string : tab4) {
			if (url.contains(string)) {
				tab = "4";
				return tab;
			}
		}
		//课程设置
		String[] tab5 = {"/course-manager.html","/add-course-page.html","/add-course.html","/update-course-page.html","/update-course.html","/del-course.html"};
		for (String string : tab5) {
			if (url.contains(string)) {
				tab = "5";
				return tab;
			}
		}
		//老师考勤
		String[] tab6 = {"/teacher-sign-in-page.html","/teacher-sign-in.html"};
		for (String string : tab6) {
			if (url.contains(string)) {
				tab = "6";
				return tab;
			}
		}
		//老师考勤统计
		String[] tab7 = {"/teacher-sign-in-count-page.html","/teacher-sign-in-count.html"};
		for (String string : tab7) {
			if (url.contains(string)) {
				tab = "7";
				return tab;
			}
		}
		//老师考勤查询
		String[] tab8 = {"/select-teacher-sign-in-page.html","/select-teacher-sign-in.html"};
		for (String string : tab8) {
			if (url.contains(string)) {
				tab = "8";
				return tab;
			}
		}
		//学费单管理
		String[] tab9 = {"/tuition-bill-page.html","/tuition-bill.html","/add-tuition-bill-page.html","/add-tuition-bill.html","/update-tuition-bill-page.html","/update-tuition-bill.html","/tuition-bill-detail.html"};
			for (String string : tab9) {
				if (url.contains(string)) {
					tab = "9";
					return tab;
			}
		}
		return tab;
	}
	
}
