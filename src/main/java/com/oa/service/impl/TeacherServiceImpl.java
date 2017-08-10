package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.ITeacherDao;
import com.oa.domain.TeacherSignIn;
import com.oa.page.Page;
import com.oa.service.ITeacherService;
@Service
public class TeacherServiceImpl implements ITeacherService {
	
	@Autowired
	ITeacherDao teacherDao;

	@Override
	public int addTeacherSignIn(TeacherSignIn signIn) {
		return teacherDao.addTeacherSignIn(signIn);
	}

	@Override
	public List<TeacherSignIn> selectTeacherSignInCount(TeacherSignIn signIn) {
		return teacherDao.selectTeacherSignInCount(signIn);
	}

	@Override
	public Page<TeacherSignIn> selectTeacherSignIn(TeacherSignIn signIn) {
		int count = teacherDao.teacherSignInCount(signIn);
		signIn.repaginate(count);
		List<TeacherSignIn> list = teacherDao.selectTeacherSignIn(signIn);
		signIn.setList(list);
		return signIn;
	}

	@Override
	public void updateTeacherSignIn(TeacherSignIn signIn) {
		teacherDao.updateTeacherSignIn(signIn);
	}

	@Override
	public TeacherSignIn hadTeacherSignIn(TeacherSignIn signIn) {
		return teacherDao.hadTeacherSignIn(signIn);
	}

}
