package com.oa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oa.domain.Course;
import com.oa.domain.Student;
import com.oa.log.SysLog;
import com.oa.service.IStudentService;
import com.oa.util.JSONUtils;
import com.oa.util.StringUtils;

/**
 * Ajax - Controller
 * 
 * @author Dwen
 * @version v 0.1 2013-8-27 下午09:50:40
 */
@Controller
public class AjaxController extends BaseController {
	@Autowired
	IStudentService studentService;

	/**
	 * 课程 - ajax
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax-course")
	public String ajaxCourse(HttpServletRequest request,
			HttpServletResponse response) {
		String courseCode = request.getParameter("course");// 课程字符
		if (StringUtils.hasText(courseCode)) {
			//查询课程
			List<Course> result = studentService.selectAjaxCourse(courseCode);
			String resultStr = JSONUtils.toJSONString(result);
			SysLog.info("resultStr : " + resultStr);
			response.setContentType("text/plain;charset=utf-8");
			try {
				PrintWriter out = response.getWriter();
				out.print(resultStr == null ? "" : resultStr);
				out.flush();
				out.close();
			} catch (IOException e) {
				SysLog.error(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 学生 - ajax
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ajax-student")
	public String ajaxStudent(HttpServletRequest request,
			HttpServletResponse response) {
		String studentName = request.getParameter("studentName");// 学生字符
		if (StringUtils.hasText(studentName)) {
			//查询学生
			List<Student> result = studentService.selectStudentAjax(studentName);
			String resultStr = JSONUtils.toJSONString(result);
			SysLog.info("resultStr : " + resultStr);
			response.setContentType("text/plain;charset=utf-8");
			try {
				PrintWriter out = response.getWriter();
				out.print(resultStr == null ? "" : resultStr);
				out.flush();
				out.close();
			} catch (IOException e) {
				SysLog.error(e.getMessage());
			}
		}
		return null;
	}
}
