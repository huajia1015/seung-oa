package com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.domain.User;

/**
 * 用户 - DAO层
 * 
 * @author Dwen
 * @version v 0.1 2013-8-1 上午10:15:25
 */
@Repository
public interface IUserDao {

	/**
	 * 登录
	 * @param user
	 * @return
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
	public void modifyPwd(User user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int insertUser(User user);
}
