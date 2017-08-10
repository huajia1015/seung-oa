package com.oa.service;

import java.util.List;

import com.oa.domain.StudentCourse;
import com.oa.domain.User;

/**
 * 用户 - Service层
 * 
 * @author Dwen
 * @version v 0.1 2013-8-1 上午10:16:15
 */
public interface IUserService {

	/**
	 * 登录
	 * @param user
	 * @return user
	 */
	User login(User user);
	
	/**
	 * 更新登录状态
	 * @param user
	 */
	void updateLogin(User user);
	
	/**
	 * 登出
	 * @param user
	 */
	void logout(User user);
	
	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	List<User> selectUser(User user);
	
	/**
	 * 修改密码
	 * @param user
	 */
	void modifyPwd(User user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
}
