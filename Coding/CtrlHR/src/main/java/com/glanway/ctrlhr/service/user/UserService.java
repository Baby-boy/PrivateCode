package com.glanway.ctrlhr.service.user;

import com.glanway.ctrlhr.entity.user.User;
import com.glanway.ctrlhr.service.BaseService;

public interface UserService extends BaseService<User> {

	/**
	 * 说明 : 用户登录
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月25日 下午2:23:08
	 */
	public User doLogin(String name, String pwd);

}
