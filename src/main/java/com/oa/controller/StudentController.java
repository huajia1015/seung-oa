package com.oa.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oa.domain.Course;
import com.oa.domain.Student;
import com.oa.domain.StudentCourse;
import com.oa.domain.User;
import com.oa.log.SysLog;
import com.oa.page.Page;
import com.oa.service.IStudentService;
import com.oa.service.IUserService;
import com.oa.util.DateUtil;
import com.oa.util.StringUtils;
import com.oa.util.constants.Constants;

/**
 * 学生 - Controller
 * 
 * @author Dwen
 * @version v 0.1 2013-8-7 下午06:31:36
 */
@Controller
public class StudentController extends BaseController {

	@Autowired
	IStudentService studentService;
	@Autowired
	IUserService userService;

	/**
	 * 学生管理
	 * @return
	 */
	@RequestMapping("/student-manager")
	public ModelAndView studentManager() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student/student-manager");
		return mav;
	}

	/**
	 * 查询学生课程
	 * @param studentCourse
	 * @return
	 */
	@RequestMapping("/select-student-course")
	public ModelAndView selectStudentCourse(HttpServletRequest request,StudentCourse studentCourse) {
		ModelAndView mav = new ModelAndView();
		//当前页码数
		String current = request.getParameter("current");
		if (StringUtils.hasText(current)) {
			studentCourse.setCurrent(current);
		}
		// 查询学生课程
		Page<StudentCourse> page = studentService.selectStudentCourse(studentCourse);
		mav.addObject("page", page);//分页
		
		mav.addObject("studentCourse", studentCourse);
		mav.setViewName("student/student-manager");
		return mav;
	}

	/**
	 * 添加学生基本信息 - 页面
	 * @return
	 */
	@RequestMapping("/add-student-page")
	public String addStudentPage() {
		return "student/add-student";
	}

	/**
	 * 添加学生基本信息
	 * @return
	 */
	@RequestMapping(value = "/add-student", method = RequestMethod.POST)
	public ModelAndView addStudent(Student student) {
		ModelAndView mav = new ModelAndView();
		// 添加学生信息
		int result = studentService.addStudent(student);
		SysLog.info("添加学生结果："+result);
		if (result > 0) {// 添加成功
			mav.addObject("returnResult", true);
		} else {
			mav.addObject("returnResult", false);
		}
		mav.setViewName("student/add-student");
		return mav;
	}
	
	/**
	 * 根据id查询学生基本信息
	 * @return
	 */
	@RequestMapping("/select-student-by-id")
	public ModelAndView selectStudent(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String idStr = request.getParameter("id");//学生id
		if (StringUtils.hasText(idStr)) {
			//根据id查询学生
			Student student = studentService.selectStudentById(Long.valueOf(idStr));
			mav.addObject("student", student);
		}
		mav.setViewName("student/student-detail");
		return mav;
	}

	/**
	 * 学生添加课程 - 页面
	 * @return
	 */
	@RequestMapping("/add-student-course-page")
	public ModelAndView addStudentCoursePage() {
		ModelAndView mav = new ModelAndView();
		//查询学生
		List<Student> students = studentService.selectStudent(new Student());
		mav.addObject("students", students);
		
		mav.setViewName("student/add-student-course");
		return mav;
	}
	
	/**
	 * 为学生添加课程
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/add-student-course", method = RequestMethod.POST)
	public ModelAndView addStudentCourse(HttpServletRequest request,StudentCourse studentCourse) {
		ModelAndView mav = new ModelAndView();
		//学生 {studentNameStr格式：学生id_学生姓名}
		String studentNameStr = request.getParameter("studentNameStr");
		if (StringUtils.hasLength(studentNameStr)) {
			String[] sns = studentNameStr.split("_");
			studentCourse.setStudentId(Long.valueOf(sns[0]));//设值学生id
			studentCourse.setStudentName(sns[1]);//设值学生姓名
		}
		//课程代号
		String courseCodeStr = request.getParameter("courseCode");
		if (StringUtils.hasLength(courseCodeStr)) {
			studentCourse.setCourseCode(courseCodeStr);
		}
		//报名日期
		String startAtStr = request.getParameter("startAtStr");
		if (StringUtils.hasLength(startAtStr)) {
			try {
				studentCourse.setStartAt(DateUtil.convertStringToDate(startAtStr.trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		// 为学生添加课程
		int result = studentService.addStudentCourse(studentCourse);
		if (result>0) {// 添加成功
			mav.addObject("returnResult", true);
		}
		
		// 查询学生课程
		studentCourse.setCourseCode(null);
		studentCourse.setStudentName(null);
		Page<StudentCourse> page = studentService.selectStudentCourse(studentCourse);
		mav.addObject("page", page);//传分页对象
		mav.setViewName("student/student-manager");
		return mav;
	}

	/**
	 * 删除学生课程
	 * @param studentCourse
	 * @return
	 */
	@RequestMapping("/del-student-course")
	public ModelAndView delStudentCourse(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		StudentCourse studentCourse = new StudentCourse();
		//学生课程id
		String studentCourseIdStr = request.getParameter("id");
		if (StringUtils.hasText(studentCourseIdStr)) {
			studentCourse.setId(Long.valueOf(studentCourseIdStr));
			//TODO 学生信息和学生签到也应要删除？？？
			// 根据学生课程id删除学生课程
			studentService.delStudentCourse(studentCourse);
		}
		// 查询学生课程
		Page<StudentCourse> page = studentService.selectStudentCourse(studentCourse);
		mav.addObject("page", page);//传分页对象
		mav.setViewName("student/student-manager");
		return mav;
	}
	
	/**
	 * 修改学生课程 - 页面
	 * @param studentCourse
	 * @return
	 */
	@RequestMapping("/update-student-course-page")
	public ModelAndView updateStudentCoursePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		StudentCourse studentCourse = null;
		//学生课程id
		String studentCourseIdStr = request.getParameter("id");
		if (StringUtils.hasText(studentCourseIdStr)) {
			// 根据id查询学生课程
			studentCourse = studentService.selectStudentCourseById(Long.valueOf(studentCourseIdStr));
			mav.addObject("studentCourse", studentCourse);
		}
		
		mav.setViewName("student/update-student-course");
		return mav;
	}

	/**
	 * 修改学生课程
	 * @param studentCourse
	 * @return
	 */
	@RequestMapping("/update-student-course")
	public ModelAndView updateStudentCourse(HttpServletRequest request,StudentCourse studentCourse) {
		ModelAndView mav = new ModelAndView();
		//课程代号
		String courseCodeStr = request.getParameter("courseCode");
		if (StringUtils.hasLength(courseCodeStr)) {
			studentCourse.setCourseCode(courseCodeStr);
		}
		//报名日期
		String startAtStr = request.getParameter("startAtStr");
		if (StringUtils.hasLength(startAtStr)) {
			try {
				studentCourse.setStartAt(DateUtil.convertStringToDate(startAtStr.trim()));
			} catch (ParseException e) {
				SysLog.error("Convert String to Date Error: "+e.getMessage());
			}
		}
		// 修改学生课程
		studentService.updateStudentCourse(studentCourse);
		// 查询学生课程
		studentCourse.setCourseCode(null);
		studentCourse.setStudentName(null);
		Page<StudentCourse> page = studentService.selectStudentCourse(studentCourse);
		mav.addObject("page", page);//传分页对象
		
		mav.setViewName("student/student-manager");
		return mav;
	}

	/**
	 * 课程管理
	 * @return
	 */
	@RequestMapping("/course-manager")
	public ModelAndView courseManager(HttpServletRequest request,Course course) {
		ModelAndView mav = new ModelAndView();
		
		//当前页码数
		String current = request.getParameter("current");
		if (StringUtils.hasText(current)) {
			course.setCurrent(current);
		}
		// 查询课程
		Page<Course> page = studentService.selectCourse(course);
		mav.addObject("page", page);
		mav.setViewName("student/course-manager");
		return mav;
	}

	/**
	 * 添加课程 - 页面
	 * @param student
	 * @return
	 */
	@RequestMapping("/add-course-page")
	public ModelAndView addCoursePage() {
		ModelAndView mav = new ModelAndView();
		//查询老师
		List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
		mav.addObject("users", users);
		mav.setViewName("student/add-course");
		return mav;
	}
	
	/**
	 * 添加课程
	 * @param student
	 * @return
	 */
	@RequestMapping("/add-course")
	public ModelAndView addCourse(HttpServletRequest request,Course course) {
		ModelAndView mav = new ModelAndView();
		// 添加课程
		int result = studentService.addCourse(course);
		if (result > 0) {// 添加成功
			mav.addObject("returnResult", true);
		}
		// 查询课程
		Page<Course> page = studentService.selectCourse(course);
		mav.addObject("page", page);
		mav.setViewName("student/course-manager");
		return mav;
	}

	/**
	 * 修改课程 - 页面
	 * @return
	 */
	@RequestMapping("/update-course-page")
	public ModelAndView updateCoursePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//课程id
		String idStr = request.getParameter("id");
		if (StringUtils.hasText(idStr)) {
			// 根据id查询课程
			Course course2 = studentService.selectCourseById(Long.valueOf(idStr));
			mav.addObject("course", course2);
			//查询老师
			List<User> users = userService.selectUser(new User(Constants.TEACHER_ROLE));
			mav.addObject("users", users);
			mav.setViewName("student/update-course");
		}
		return mav;
	}
	
	/**
	 * 修改课程
	 * @return
	 */
	@RequestMapping("/update-course")
	public ModelAndView updateCourse(HttpServletRequest request,Course course) {
		ModelAndView mav = new ModelAndView();
		//修改课程
		studentService.updateCourse(course);
		// 查询课程
		Page<Course> page = studentService.selectCourse(course);
		mav.addObject("page", page);
		
		mav.setViewName("student/course-manager");
		return mav;
	}

	/**
	 * 删除课程
	 * @param course
	 * @return
	 */
	@RequestMapping("/del-course")
	public ModelAndView delCourse(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//课程id
		String idStr = request.getParameter("id");
		if (StringUtils.hasText(idStr)) {
			// 删除课程
			studentService.delCourse(Long.valueOf(idStr));
		}
		// 查询课程
		Page<Course> page = studentService.selectCourse(new Course());
		mav.addObject("page", page);
		mav.setViewName("student/course-manager");
		return mav;
	}
	
}
