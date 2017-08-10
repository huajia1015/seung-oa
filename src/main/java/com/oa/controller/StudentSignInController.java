package com.oa.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.domain.Course;
import com.oa.domain.Student;
import com.oa.domain.StudentSignIn;
import com.oa.domain.User;
import com.oa.log.SysLog;
import com.oa.page.Page;
import com.oa.service.IStudentService;
import com.oa.util.ArrayUtils;
import com.oa.util.DateUtil;
import com.oa.util.StringUtils;
import com.oa.util.constants.Constants;

/**
 * 学生签到 - Controller
 * 
 * @author Dwen
 * @version v 0.1 2013-8-7 下午06:31:36
 */
@Controller
public class StudentSignInController extends BaseController {

	@Autowired
	IStudentService studentService;

	/**
	 * 学生签到 - 页面
	 * @return
	 */
	@RequestMapping("/student-sign-in-page")
	public ModelAndView studentSignInPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student/student-sign-in");
		return mav;
	}
	
	/**
	 * 学生签到 
	 * 获得id、签到日期，根据id进行查询需要签到的信息，查出后再插入签到表
	 * @return
	 */
	@RequestMapping("/student-sign-in")
	public ModelAndView studentSignIn(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//获得id、签到日期，根据id进行查询需要签到的信息，查出后再插入签到表
		String sidStr = request.getParameter("sid");//id
		String courseCodeStr = request.getParameter("courseCode");//课程代号
		String schoolDateStr = request.getParameter("schoolDateStr");//日期
		String statusStr = request.getParameter("status");//签到状态
		String time = request.getParameter("time");//时间段
		String lesson = request.getParameter("lesson");//课时
		if (StringUtils.hasText(sidStr) && StringUtils.hasText(courseCodeStr)
				&& StringUtils.hasText(schoolDateStr)
				&& StringUtils.hasText(statusStr)) {
			Course course = new Course();
			course.setId(Long.valueOf(sidStr));
			course.setCourseCode(courseCodeStr);
			//预载当前日期学生签到信息
			List<StudentSignIn> studentSignIns = studentService.selectStudentSignInInfo(course);
			StudentSignIn ss = studentSignIns.get(0);
			//组装签到信息
			StudentSignIn signIn = new StudentSignIn();
			signIn.setStudentId(ss.getStudentId());
			signIn.setStudentName(ss.getStudentName());
			signIn.setCourseCode(ss.getCourseCode());
			try {
				signIn.setSignDate(DateUtil.convertStringToDate(schoolDateStr.trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
			//时间段(手动输入时间段)
			if (StringUtils.hasText(time)) {
				signIn.setTime(time.trim());
			}else{//默认课程时间段
				signIn.setTime(ss.getTime());
			}
			//课时
			if (StringUtils.hasText(lesson)) {
				signIn.setLesson(Double.valueOf(lesson.trim()));
			}else{
				signIn.setLesson(Constants.HOURS_2);
			}
//			signIn.setTime(ss.getTime());
//			signIn.setLesson(Constants.HOURS_2);
			signIn.setStatus(statusStr);
			//判断该学生是否已签到
			StudentSignIn studentSignIn = studentService.hadStudentSignIn(signIn);
			if (null==studentSignIn) {//末签过到
				User user = getSessionUser(request);//登录用户
				signIn.setOp(user.getUserName());
				//添加学生签到
				int result = studentService.insertStudentSignIn(signIn);
				if (result>0) {
					mav.addObject("studentSignInSuccess", true);
				}
			}else{//已签过到
				mav.addObject("studentSignInSuccess", false);
			}
		}
		
		//签到后，查询最新信息
		Course course2 = new Course();
		course2.setCourseCode(courseCodeStr);
		//预载当前日期学生签到信息
		List<StudentSignIn> studentSignIns = studentService.selectStudentSignInInfo(course2);
		mav.addObject("studentSignIns", studentSignIns);
		
		mav.addObject("schoolDateStr", schoolDateStr);
		mav.setViewName("student/student-sign-in");
		return mav;
	}
	
	/**
	 * 临时添加学生签到
	 * @param request
	 * @return
	 */
	@RequestMapping("/add-student-sign-in")
	public ModelAndView addStudentSignIn(HttpServletRequest request,StudentSignIn studentSignIn) {
		ModelAndView mav = new ModelAndView();
			Student student = new Student();
			student.setStudentName(studentSignIn.getStudentName());
			//根据学生姓名查学生信息
			student = studentService.selectStudentByName(student);
			studentSignIn.setStudentId(student.getId());
			try {
				studentSignIn.setSignDate(DateUtil.convertStringToDate(studentSignIn.getSchoolDateStr().trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
			studentSignIn.setStatus(Constants.STUDENT_SIGN_YES);//签到状态
			//判断该学生是否已签到
			StudentSignIn studentSignIn2 = studentService.hadStudentSignIn(studentSignIn);
			if (null==studentSignIn2) {//末签过到
				User user = getSessionUser(request);//登录用户
				studentSignIn.setOp(user.getUserName());
				//添加学生签到
				int result = studentService.insertStudentSignIn(studentSignIn);
				if (result>0) {
					mav.addObject("studentSignInSuccess", true);
				}
			}else{//已签过到
				mav.addObject("studentSignInSuccess", false);
			}
		
//		//签到后，查询最新信息
//		Course course2 = new Course();
//		course2.setCourseCode(studentSignIn.getCourseCode());
//		//预载当前日期学生签到信息
//		List<StudentSignIn> studentSignIns = studentService.selectStudentSignInInfo(course2);
//		mav.addObject("studentSignIns", studentSignIns);
//		
//		mav.addObject("schoolDateStr", schoolDateStr);
		mav.setViewName("student/student-sign-in");
		return mav;
	}
	
	/**
	 * 学生签到 （批量）
	 * 获得id、签到日期，根据id进行查询需要签到的信息，查出后再插入签到表
	 * @return
	 */
	@RequestMapping("/batch-student-sign-in")
	public ModelAndView batchStudentSignIn(HttpServletRequest request,Course course) {
		Date signDate = null;//签到日期
		ModelAndView mav = new ModelAndView();
		//获取需要签到的id
		String[] signIds = request.getParameterValues("subBox");
		if (ArrayUtils.hasLength(signIds)) {
			course.setIds(signIds);
		}
		//查询所有需要签到的信息
		//批量预载当前日期学生签到
		List<StudentSignIn> studentSignIns = studentService.selectStudentSignInInfoBatch(course);
		String schoolDateStr = request.getParameter("schoolDateStr");//日期
		if (StringUtils.hasText(schoolDateStr)) {
			try {
				signDate = DateUtil.convertStringToDate(schoolDateStr.trim());//签到日期
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
			List<StudentSignIn> sSignIns = new ArrayList<StudentSignIn>();
			for (int i = 0; i < studentSignIns.size(); i++) {
				StudentSignIn ss= studentSignIns.get(i);
				ss.setLesson(Constants.HOURS_2);
				ss.setSignDate(signDate);
				ss.setStatus("1");//签到
				sSignIns.add(ss);
			}
			//批量添加学生签到
			studentService.insertStudentSignInBatch(sSignIns);
		}
		
		mav.addObject("schoolDateStr", schoolDateStr);
		mav.setViewName("student/student-sign-in");
		return mav;
	}
	
	/**
	 * 预载当前日期学生签到信息
	 * @return
	 */
	@RequestMapping("/student-sign-in-info")
	public ModelAndView studentSignInInfo(HttpServletRequest request,Course course) {
		ModelAndView mav = new ModelAndView();
		String schoolDateStr = request.getParameter("schoolDateStr");//日期
		String time = request.getParameter("time");//时间段
		String lesson = request.getParameter("lesson");//课时
		//预载当前日期学生签到信息
		List<StudentSignIn> studentSignIns = studentService.selectStudentSignInInfo(course);
		mav.addObject("studentSignIns", studentSignIns);
		
		mav.addObject("courseVO", course);
		mav.addObject("schoolDateStr", schoolDateStr);
		mav.addObject("time", time);
		mav.addObject("lesson", lesson);
		mav.setViewName("student/student-sign-in");
		return mav;
	}
	
	/**
	 * 学生签到统计 - 页面
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/student-sign-in-count-page")
	public ModelAndView studentSignInCountPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student/student-sign-in-count");
		return mav;
	}
	
	/**
	 * 学生签到统计
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/student-sign-in-count")
	public ModelAndView studentSignInCount(StudentSignIn signIn) {
		ModelAndView mav = new ModelAndView();
		//时间条件
		if (StringUtils.hasText(signIn.getStartAtStr()) && StringUtils.hasText(signIn.getEndAtStr())) {
			try {
				signIn.setStartAt(DateUtil.convertStringToDate(signIn.getStartAtStr().trim()));
				signIn.setEndAt(DateUtil.convertStringToDate(signIn.getEndAtStr().trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		//学生签到统计
		List<StudentSignIn> studentSignIns = studentService.selectStudentSignInCount(signIn);
		mav.addObject("studentSignIns", studentSignIns);
		
		mav.addObject("signIn", signIn);
		mav.setViewName("student/student-sign-in-count");
		return mav;
	}
	
	/**
	 * 学生签到查询 - 页面
	 * @param request
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/select-student-sign-in-page")
	public ModelAndView selectStudentSignInPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student/select-student-sign-in");
		return mav;
	}
	
	/**
	 * 学生签到查询
	 * @param request
	 * @param signIn
	 * @return
	 */
	@RequestMapping("/select-student-sign-in")
	public ModelAndView selectStudentSignIn(HttpServletRequest request,StudentSignIn signIn) {
		ModelAndView mav = new ModelAndView();
		//当前页码数
		String current = request.getParameter("current");
		if (StringUtils.hasText(current)) {
			signIn.setCurrent(current);
		}
		String startAtStr = request.getParameter("startAtStr");//开始时间
		String endAtStr = request.getParameter("endAtStr");//结束时间
		String courseCodeStr = request.getParameter("courseCode");//课程代号
		String studentNameStr = request.getParameter("studentName");//学生
		String statusStr = request.getParameter("status");//学生签到状态
		String signDateStr = request.getParameter("signDateStr");//签到日期
		//时间条件
		if (StringUtils.hasText(startAtStr) && StringUtils.hasText(endAtStr)) {
			try {
				signIn.setStartAt(DateUtil.convertStringToDate(startAtStr.trim()));
				signIn.setEndAt(DateUtil.convertStringToDate(endAtStr.trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		//课程代号条件
		if (StringUtils.hasText(courseCodeStr)) {
			signIn.setCourseCode(courseCodeStr);
		}
		//学生条件
		if (StringUtils.hasText(studentNameStr)) {
			signIn.setStudentName(studentNameStr);
		}
		//学生签到状态
		if (StringUtils.hasText(statusStr)) {
			signIn.setStatus(statusStr);
		}
		//签到日期
		if (StringUtils.hasText(signDateStr)) {
			try {
				signIn.setSignDate(DateUtil.convertStringToDate(signDateStr.trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		//查询学生签到
		Page<StudentSignIn> page = studentService.selectStudentSignIn(signIn);
		mav.addObject("page", page);//分页
		mav.addObject("signIn", signIn);
		mav.setViewName("student/select-student-sign-in");
		return mav;
	}
	
}
