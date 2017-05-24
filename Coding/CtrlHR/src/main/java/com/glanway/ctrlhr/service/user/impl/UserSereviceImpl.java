package com.glanway.ctrlhr.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.user.UserDao;
import com.glanway.ctrlhr.entity.user.User;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.user.UserService;

@Service
@Transactional
public class UserSereviceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User doLogin(String name, String pwd) {
		return userDao.getUser(name, pwd);
	}

}
