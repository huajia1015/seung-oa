package com.oa.service;

import java.util.List;

import com.oa.domain.TeacherSignIn;
import com.oa.page.Page;

/**
 * 老师 - Service层
 * 
 * @author Dwen
 * @version v 0.1 2013-8-7 下午06:27:04
 */
public interface ITeacherService {

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
	Page<TeacherSignIn> selectTeacherSignIn(TeacherSignIn signIn);
	
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
