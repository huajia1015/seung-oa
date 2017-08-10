package com.oa.controller;

import javax.servlet.http.HttpServletRequest;

import com.oa.domain.User;
import com.oa.util.constants.Constants;

/**
 * Controller基类
 * 
 * @author Dwen
 * @version v 0.1 2013-8-1 上午10:27:01
 */
public class BaseController {

	/**
	 * 获取系统用户Session
	 * @param request
	 * @return
	 */
	public User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(
				Constants.CRYSTAL_USER_SESSION);
	}

	/**
	 * 保存用户Session
	 * @param request
	 * @param user
	 */
	public void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constants.CRYSTAL_USER_SESSION, user);
	}

	/**
	 * 移除用户Session
	 * @param request
	 */
	public void removeSessionUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.CRYSTAL_USER_SESSION);
	}

	/**
	 * 获取基于应用程序的url绝对路径
	 * @param request
	 * @param url
	 * @return
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		return request.getContextPath() + url;
	}

	/**
	 * 获取请求IP
	 * @param request
	 * @return
	 */
	public String getIp(HttpServletRequest request) {
		String addr = request.getRemoteAddr();
		String headerXForwardedFor = request.getHeader("X-Forwarded-For");
		return headerXForwardedFor == null ? addr : headerXForwardedFor
				.split(",")[0];
	}
	
}
