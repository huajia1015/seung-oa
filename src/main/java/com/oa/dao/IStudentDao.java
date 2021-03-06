package com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.domain.Course;
import com.oa.domain.Student;
import com.oa.domain.StudentCourse;
import com.oa.domain.StudentSignIn;

/**
 * 学生 - DAO层
 * 
 * @author Dwen
 * @version v 0.1 2013-8-6 下午05:36:06
 */
@Repository
public interface IStudentDao {

	/**
	 * 查询学生
	 * @param student
	 * @return
	 */
	List<Student> selectStudent(Student student);
	
	/**
	 * 查询某个学生
	 * @param id
	 * @return
	 */
	Student selectStudentById(long id);
	
	/**
	 * 根据名字查学生
	 * @param student
	 * @return
	 */
	Student selectStudentByName(Student student);
	
	/**
	 * 删除学生
	 * @param id
	 */
	void delStudent(long id);
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	int addStudent(Student student);
	
	/**
	 *  查询学生 - ajax
	 * @param studentName
	 * @return
	 */
	List<Student> selectStudentAjax(String studentName);
	
	/**
	 * 学生课程管理查询
	 * @param studentCourse
	 * @return
	 */
	List<StudentCourse> selectStudentCourse(StudentCourse studentCourse);
	
	/**
	 * 查该学生所有课程
	 * @param studentName
	 * @return
	 */
	List<StudentCourse> selectStudentCourseByName(String studentName);
	
	/**
	 * 学生课程管理查询
	 * @param studentCourse
	 * @return
	 */
	int selectStudentCourseCount(StudentCourse studentCourse);
	
	/**
	 * 根据id查询学生课程
	 * @param id
	 * @return
	 */
	StudentCourse selectStudentCourseById(long id);
	
	/**
	 * 删除学生课程
	 * @param studentCourse
	 */
	void delStudentCourse(StudentCourse studentCourse);
	
	/**
	 * 修改学生课程
	 * @param studentCourse
	 */
	void updateStudentCourse(StudentCourse studentCourse);
	
	/**
	 * 为学生添加课程
	 * @param studentCourse
	 * @return
	 */
	int addStudentCourse(StudentCourse studentCourse);
	
	/**
	 * 查询课程
	 * @param course
	 * @return
	 */
	List<Course> selectCourse(Course course);
	
	/**
	 * 查询课程 - 总数
	 * @param course
	 * @return
	 */
	int selectCourseCount(Course course);
	
	/**
	 * 查询该课程是否存在
	 * @param courseCode
	 * @return
	 */
	Course hadCourse(String courseCode);
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	Course selectCourseById(long id);
	
	/**
	 * 添加课程
	 * @param course
	 * @return
	 */
	int addCourse(Course course);
	
	/**
	 * 删除课程
	 * @param id
	 */
	void delCourse(long id);
	
	/**
	 * 修改课程
	 * @param course
	 */
	void updateCourse(Course course);
	
	/**
	 * 查询课程 - Ajax
	 * @param courseCode
	 * @return
	 */
	List<Course> selectAjaxCourse(String courseCode);
	
	/**
	 * 加载当前日期学生签到信息
	 * @param course
	 * @return
	 */
	List<StudentSignIn> selectStudentSignInInfo(Course course);
	
	/**
	 * 批量 - 加载当前日期学生签到信息
	 * @param course
	 * @return
	 */
	List<StudentSignIn> selectStudentSignInInfoBatch(Course course);
	
	/**
	 * 学生签到/缺勤(单个签到)
	 * @param signIn
	 */
	int insertStudentSignIn(StudentSignIn signIn);
	
	/**
	 * 学生签到/缺勤(批量签到)
	 * @param signIn
	 */
	void insertStudentSignInBatch(List<StudentSignIn> signIns);
	
	/**
	 * 学生签到统计查询
	 * @param signIn
	 * @return
	 */
	List<StudentSignIn> selectStudentSignInCount(StudentSignIn signIn);
	
	/**
	 * 学生签到查询
	 * @param signIn
	 * @return
	 */
	List<StudentSignIn> selectStudentSignIn(StudentSignIn signIn);
	
	/**
	 * 学生签到查询 - 总数
	 * @param signIn
	 * @return
	 */
	int studentSignInCount(StudentSignIn signIn);
	
	/**
	 * 判断该学生是否已签到 {当天+课程+学生}
	 * @param signIn
	 * @return
	 */
	StudentSignIn hadStudentSignIn(StudentSignIn signIn);
}
