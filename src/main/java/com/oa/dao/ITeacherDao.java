package com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.domain.TeacherSignIn;

/**
 * 老师 - DAO层
 * 
 * @author Dwen
 * @version v 0.1 2013-8-6 下午05:36:36
 */
@Repository
public interface ITeacherDao {

	/**
	 * 添加老师考勤
	 * @param signIn
	 * @return
	 */
	int addTeacherSignIn(TeacherSignIn signIn);
	
	/**
	 * 老师考勤统计查询
	 * @param signIn
	 * @return
	 */
	List<TeacherSignIn> selectTeacherSignInCount(TeacherSignIn signIn);
	
	/**
	 * 老师考勤查询
	 * @param signIn
	 * @return
	 */
	List<TeacherSignIn> selectTeacherSignIn(TeacherSignIn signIn);
	
	/**
	 * 老师考勤查询 - 总数
	 * @param signIn
	 * @return
	 */
	int teacherSignInCount(TeacherSignIn signIn);
	
	/**
	 * 老师考勤修改(只有管理员有权限)
	 * @param signIn
	 */
	void updateTeacherSignIn(TeacherSignIn signIn);
	
	/**
	 * 判断当天是否已添加该考勤
	 * @param signIn
	 * @return
	 */
	TeacherSignIn hadTeacherSignIn(TeacherSignIn signIn);
	
	
}
