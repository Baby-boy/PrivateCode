package com.glanway.ctrlhr.service.user.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.user.UserDao;
import com.glanway.ctrlhr.entity.user.User;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.user.UserService;
import com.glanway.ctrlhr.util.MD5Util;

@Service
@Transactional
public class UserSereviceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User doLogin(String name, String pwd, String ip) {
		// 密码进行MD5加密
		pwd = MD5Util.getMd5Encoding(pwd);

		User user = userDao.getUser(name, pwd);

		if (null != user) {
			user.setLastLoginIp(ip);
			user.setLastLoginTime(new Date());
			userDao.updateByPrimaryKey(user);
		}

		return user;
	}

}
