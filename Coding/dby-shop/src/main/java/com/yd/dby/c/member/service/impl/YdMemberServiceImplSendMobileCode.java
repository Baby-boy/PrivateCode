package com.yd.dby.c.member.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.User;
import com.yd.dby.c.member.mapper.YdMemberMapperInfo;
import com.yd.dby.c.member.service.YdMemberServiceSendMobileCode;
import com.yd.dby.utils.YdResponseUtil;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.security.YdSecurity;

@Service("_YdMemberServiceSendMobileCode")
public class YdMemberServiceImplSendMobileCode implements YdMemberServiceSendMobileCode {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperInfo ydMemberMapperInfo;

	@Autowired
	private YdSecurity ydSecurity;

	/**
	 * 修改登录密码
	 */
	@Override
	public Object sendUserMobileCode(HashMap<String, Object> request) {
		try {
			User userData = ydMemberMapperInfo.info((Integer) session.getAttribute("user_id"));
			
			if ( request.get("type") == null ) {
				return YdUtilResponse.fail("参数错误");
			}
			
			switch ( request.get("type").toString() ) {
				case "editPay":
					return YdUtilResponse.success(ydSecurity.resetPayPasswordSMS(userData.getUser_mobile().toString()), "发送验证码成功");
				case "setPay":
					return YdUtilResponse.success(ydSecurity.resetPayPasswordSMS(userData.getUser_mobile().toString()), "发送验证码成功");
				case "editMobile":
					return YdUtilResponse.success(ydSecurity.resetMobileSMS(userData.getUser_mobile().toString()), "发送验证码成功");
				case "editLogin":
					return YdUtilResponse.success(ydSecurity.resetLoginPasswordSMS(userData.getUser_mobile().toString()), "发送验证码成功");
			}
			return YdUtilResponse.success(ydSecurity.resetLoginPasswordSMS(userData.getUser_mobile().toString()), "发送验证码成功");
		} catch (Exception e) {
			return YdUtilResponse.fail(e.getMessage());
		}

	}

	/**
	 * 忘记密码
	 */
	@Override
	public Object sendMobileCode(HashMap<String, Object> request) {
		try {
			return YdResponseUtil.success(ydSecurity.forgetPasswordSMS(request.get("mobile").toString()));
		} catch (Exception e) {
			return YdResponseUtil.failure(e.getMessage());
		}
	}
}