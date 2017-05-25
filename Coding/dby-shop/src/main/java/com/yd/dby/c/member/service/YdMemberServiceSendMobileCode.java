package com.yd.dby.c.member.service;

import java.util.HashMap;

public interface YdMemberServiceSendMobileCode {
	
	/*发送短信验证码-提交手机号*/
	Object sendMobileCode(HashMap<String, Object> request);
	
	/*发送短信验证码-用户手机号*/
	Object sendUserMobileCode(HashMap<String, Object> request);
	
}
