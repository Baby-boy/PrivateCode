package com.yd.dby.a.sys.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yd.dby.a.sys.entity.YdSysThirdPartyLoginUser;
import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.a.sys.mapper.YdSysMapperSecurity;
import com.yd.dby.a.sys.service.YdSysServiceSecurity;
import com.yd.dby.c.member.mapper.YdMemberMapperInfo;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.YdUtilTokenOnlyExpire;
import com.yd.dby.utils.md5.YdMd5Util;
import com.yd.dby.utils.security.YdSecurity;

@Service("_security")
public class YdSysServiceImplSecurity implements YdSysServiceSecurity {

	@Autowired
	private YdSecurity ydSecurity;
	
	@Autowired
	private YdSysMapperSecurity ydSysMapperSecurity;
	
	@Autowired
	private StringRedisTemplate redis;
	
	
	@Autowired
	private YdMemberMapperInfo YdMemberMapperInfo;
	

	@Override
	public Object login(HashMap<String, Object> request) {
		// HttpClient httpClient=new HttpClient(new HttpClientParams(), new
		// SimpleHttpConnectionManager(true));
		String username = request.get("username").toString();
		String password = YdMd5Util.GetMD5Code(request.get("password").toString());
		Object userObject = ydSecurity.login(username, password);
		// @SuppressWarnings("unchecked")
		// HashMap<String, Object> userMap = (HashMap<String, Object>)
		// userObject;
		// @SuppressWarnings("unchecked")
		// HashMap<String, Object> userData = (HashMap<String, Object>)
		// userMap.get("data");
		// response.setHeader("Authenticate", userData.get("token").toString());

		return userObject;
	}

	/**
	 * 注册用户
	 */
	@Override
	public Object register_member(HashMap<String, Object> request) {
		String username = request.get("user_username").toString();
		String mobile = request.get("mobile").toString();
		String password = YdMd5Util.GetMD5Code(request.get("password").toString());
		String token = request.get("token").toString();
		String salt = request.get("salt").toString();
		return ydSecurity.register(token, salt, mobile, password, username);
	}

	/**
	 * 注册店铺
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public Object register_seller(HashMap<String, Object> request) {
		// String mobile = request.get("user_mobile").toString();
		// String password =
		// YdMd5Util.GetMD5Code(request.get("password").toString());
		// String token = request.get("token").toString();
		// String salt = request.get("salt").toString();
		// System.out.println( request );
		return ydSecurity.register_seller(request);
	}

	// 注册 - 获取短信验证码
	@Override
	public Object register_member_sms(HashMap<String, Object> request) {
		String mobile = request.get("mobile").toString();
		return ydSecurity.registerSMS(mobile);
	}
	
	
	/**
	 * 找回密码 -获取短信验证码
	 */
	@Override
	public Object forgetPasswordSms(HashMap<String, Object> request) {
		String mobile = request.get("mobile").toString();
		YdSysUserSecurity ydSysUserSecurity = ydSysMapperSecurity.queryUserByMobile(mobile);
		if ( ydSysUserSecurity == null ) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("code", 401);
			map.put("msg", "此用户不存在");
			return map;
		}
		
		return ydSecurity.forgetPasswordSMS(mobile);
	}
	
	
	/**
	 * 找回密码
	 */
	@Override
	public Object forgetPassword(HashMap<String, Object> request) {
		if ( request.get("mobile") == null || request.get("mobile").equals("") ) {
			return YdUtilResponse.fail("手机号不能为空");
		}
		if ( request.get("password") == null || request.get("password").equals("") ) {
			return YdUtilResponse.fail("密码不能为空");
		}
		if ( request.get("password").toString().length() < 6 ) {
			return YdUtilResponse.fail("密码长度密码大于6位");
		}
		if ( !request.get("password_repeat").equals( request.get("password") ) ) {
			return YdUtilResponse.fail("两次密码输入不一致");
		}
		if ( request.get("token") == null || request.get("token").equals("") ) {
			return YdUtilResponse.fail("参数错误");
		}
		if ( request.get("salt") == null || request.get("salt").equals("") ) {
			return YdUtilResponse.fail("验证码不能为空");
		}
		
		if ( redis.hasKey( YdMd5Util.GetMD5Code( request.get("salt").toString() ) ) ) {			
			return YdUtilResponse.fail("手机号验证码已经过期");
		}
		
		try {
			YdUtilTokenOnlyExpire.verification(request.get("token").toString(), request.get("salt").toString());
			redis.opsForValue().set( YdMd5Util.GetMD5Code( request.get("token").toString() ), request.get("salt").toString() );
			redis.expire( YdMd5Util.GetMD5Code( request.get("token").toString() ), 60*6, TimeUnit.SECONDS );
		} catch (Exception e) {
			return YdUtilResponse.fail("验证码错误");
		}
		
		YdSysUserSecurity ydSysUserSecurity = ydSysMapperSecurity.queryUserByMobile( request.get("mobile").toString() );
		
		if ( ydSysUserSecurity == null ) {
			return YdUtilResponse.fail("此用户不存在");
		}
		
		try {
			request.put("user_id", ydSysUserSecurity.getUser_id() );
			request.put("password", YdMd5Util.GetMD5Code(request.get("password").toString()));
			YdMemberMapperInfo.editLoginPassword(request);
			return YdUtilResponse.success(null, "找回密码成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("系统错误");
		}
	}

	/**
	 * 查询第三方登陆表的用户id
	 */
	@Override
	public Map<String, Object> queryUserByOpenId(String openid) {
		Map<String,Object> map = new HashMap<String, Object>();
		YdSysThirdPartyLoginUser ydSysThirdPartyLoginUser = ydSysMapperSecurity.queryUserByOpenId(openid);
		if (ydSysThirdPartyLoginUser!=null) {
			Integer tpl_user_id = ydSysThirdPartyLoginUser.getTpl_user_id();
			map.put("tpl_user_id",tpl_user_id);
			map.put("msg","success");
			
		}else {
			map.put("msg","fail");
		}
		return map;
	}

	
	/**
	 * 查询用户信息
	 */
	@Override
	public YdSysUserFull queryUserByUserId(Integer tplUserId) {
		
		YdSysUserFull ydSysUserFull =ydSysMapperSecurity.queryUserInfoById(tplUserId);
		return ydSysUserFull;
	}
	

}