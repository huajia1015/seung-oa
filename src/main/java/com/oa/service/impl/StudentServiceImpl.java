package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.IStudentDao;
import com.oa.domain.Course;
import com.oa.domain.Student;
import com.oa.domain.StudentCourse;
import com.oa.domain.StudentSignIn;
import com.oa.page.Page;
import com.oa.service.IStudentService;
@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	IStudentDao studentDao;

	@Override
	public List<Student> selectStudent(Student student) {
		return studentDao.selectStudent(student);
	}
	
	@Override
	public Student selectStudentById(long id) {
		return studentDao.selectStudentById(id);
	}

	@Override
	public void delStudent(long id) {
		studentDao.delStudent(id);
	}

	@Override
	public int addStudent(Student student) {
		return studentDao.addStudent(student);
	}

	@Override
	public Page<StudentCourse> selectStudentCourse(StudentCourse studentCourse) {
		int count = studentDao.selectStudentCourseCount(studentCourse);
		studentCourse.repaginate(count);
		List<StudentCourse> list = studentDao.selectStudentCourse(studentCourse);
		studentCourse.setList(list);
		return studentCourse;
	}
	
	@Override
	public StudentCourse selectStudentCourseById(long id) {
		return studentDao.selectStudentCourseById(id);
	}

	@Override
	public void delStudentCourse(StudentCourse studentCourse) {
		studentDao.delStudentCourse(studentCourse);
	}

	@Override
	public void updateStudentCourse(StudentCourse studentCourse) {
		studentDao.updateStudentCourse(studentCourse);
	}

	@Override
	public int addStudentCourse(StudentCourse studentCourse) {
		return studentDao.addStudentCourse(studentCourse);
	}

	@Override
	public Page<Course> selectCourse(Course course) {
		if (course.isHasPage()) {//有分页
			int count = studentDao.selectCourseCount(course);
			course.repaginate(count);
		}
		List<Course> list = studentDao.selectCourse(course);
		course.setList(list);
		return course;
	}
	
	@Override
	public Course selectCourseById(long id) {
		return studentDao.selectCourseById(id);
	}
	
	@Override
	public int addCourse(Course course) {
		return studentDao.addCourse(course);
	}

	@Override
	public void delCourse(long id) {
		studentDao.delCourse(id);
	}

	@Override
	public void updateCourse(Course course) {
		studentDao.updateCourse(course);
	}

	@Override
	public List<StudentSignIn> selectStudentSignInInfo(Course course) {
		return studentDao.selectStudentSignInInfo(course);
	}
	
	@Override
	public List<StudentSignIn> selectStudentSignInInfoBatch(Course course) {
		return studentDao.selectStudentSignInInfoBatch(course);
	}
	
	@Override
	public int insertStudentSignIn(StudentSignIn signIn) {
		return studentDao.insertStudentSignIn(signIn);
	}
	
	@Override
	public void insertStudentSignInBatch(List<StudentSignIn> signIns) {
		studentDao.insertStudentSignInBatch(signIns);
	}

	@Override
	public List<StudentSignIn> selectStudentSignInCount(StudentSignIn signIn) {
		return studentDao.selectStudentSignInCount(signIn);
	}

	@Override
	public Page<StudentSignIn> selectStudentSignIn(StudentSignIn signIn) {
		int count  = studentDao.studentSignInCount(signIn);
		signIn.repaginate(count);
		List<StudentSignIn> list = studentDao.selectStudentSignIn(signIn);
		signIn.setList(list);
		return signIn;
	}

	@Override
	public List<Course> selectAjaxCourse(String courseCode) {
		return studentDao.selectAjaxCourse(courseCode);
	}

	@Override
	public StudentSignIn hadStudentSignIn(StudentSignIn signIn) {
		return studentDao.hadStudentSignIn(signIn);
	}

	@Override
	public List<Student> selectStudentAjax(String studentName) {
		return studentDao.selectStudentAjax(studentName);
	}

	@Override
	public Course hadCourse(String courseCode) {
		return studentDao.hadCourse(courseCode);
	}

	@Override
	public List<StudentCourse> selectStudentCourseByName(String studentName) {
		return studentDao.selectStudentCourseByName(studentName);
	}

	@Override
	public Student selectStudentByName(Student student) {
		return studentDao.selectStudentByName(student);
	}


}
