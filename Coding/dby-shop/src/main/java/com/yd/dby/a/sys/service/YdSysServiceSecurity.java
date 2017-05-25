package com.yd.dby.a.sys.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.a.sys.entity.YdSysUserFull;

public interface YdSysServiceSecurity {
	Object login(HashMap<String, Object> request);
	Object register_member_sms(HashMap<String, Object> request);
	Object register_member(HashMap<String, Object> request);
	Object register_seller(HashMap<String, Object> request);
	
	Object forgetPasswordSms(HashMap<String, Object> request);
	
	Object forgetPassword(HashMap<String, Object> request);
	
	/**
	 * @param openid
	 * @return
	 * 方法作用(根据腾讯返回的openid查询用户是否存在)
	 */
	Map<String, Object> queryUserByOpenId(String openid);
	
	/**
	 * @param tplUserId
	 * @return
	 * 方法作用(查询已绑定用户的所有信息)
	 */
	YdSysUserFull queryUserByUserId(@Param("user_id")Integer tplUserId);
}