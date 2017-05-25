package com.yd.dby.utils.security;

import java.util.HashMap;

import com.yd.dby.a.sys.entity.YdSysUserJwt;

public interface YdSecurity {

	YdSysUserJwt verifyToken(String token);

	Object registerSMS(String mobile);

	Object forgetPasswordSMS(String mobile);

	Object resetLoginPasswordSMS(String token);

	Object resetPayPasswordSMS(String token);

	Object login(String token);

	Object login(String username, String password);

	Object register(String token, String salt, String mobile, String password, String username);
	
	Object register_seller(HashMap<String, Object> request);

	Object forgetLoginPassword(String token, String salt, String password) throws Exception;

	Object resetLoginPassword(String token, String salt, String password) throws Exception;

	Object resetPayPassword(String token, String salt, String password) throws Exception;

	Object resetMobileSMS(String string);

}