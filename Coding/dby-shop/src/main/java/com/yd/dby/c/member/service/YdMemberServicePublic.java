package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

public interface YdMemberServicePublic {
	
	/*验证手机号*/
	Map<String, Object> verifyMobileCode(HashMap<String, Object> request);
	
	
	/*验证手机号-成功后跳转页面*/
	Map<String, Object> verifyMobileSuccess(HashMap<String, Object> request);
	
}
