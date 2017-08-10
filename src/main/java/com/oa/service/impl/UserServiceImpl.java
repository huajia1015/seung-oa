package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.IUserDao;
import com.oa.domain.User;
import com.oa.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public void logout(User user) {
		userDao.logout(user);
	}

	@Override
	public List<User> selectUser(User user) {
		return userDao.selectUser(user);
	}

	@Override
	public void updateLogin(User user) {
		userDao.updateLogin(user);
	}

	@Override
	public void modifyPwd(User user) {
		userDao.modifyPwd(user);
	}

	@Override
	public boolean addUser(User user) {
		int result = userDao.insertUser(user);
		if (result>0) {
			return true;
		}
		return false;
	}

}
