package com.yd.dby.utils.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.utils.jwt.YdJwtType;
import com.yd.dby.utils.response.YdUtilResponse;

public abstract class YdZzzSecuritySms extends YdZzzSecurityUtils {
	@Autowired
	private HttpSession session;

	// 注册
	public Object registerSMS(String mobile) {
		try {
			String salt;

			String token;

			// securityUser = zzSqlQueryUserByName(mobile);

			salt = zzUtilGenerateSalt();
			
			token = zzUtilGenerateToken(YdJwtType.SMS_REGISTER, salt);
			
			zzUtilGenerateSms(YdJwtType.SMS_REGISTER, mobile, salt);

			return YdUtilResponse.SmsSuccess(token);
		} catch (Exception e) {
			return YdUtilResponse.SmsFailure(e.getMessage());
		}

	}

	// 忘记密码
	public Object forgetPasswordSMS(String mobile) {
		try {
			String salt;

			String token;

			YdSysUserSecurity securityUser = null;

			securityUser = zzSqlQueryUserByMobile(mobile);

			salt = zzUtilGenerateSalt();

			token = zzUtilGenerateToken(YdJwtType.SMS_FORGETASSWORD, salt);
			zzUtilGenerateSms(YdJwtType.SMS_FORGETASSWORD, securityUser.getUser_mobile(), salt);

			return YdUtilResponse.SmsSuccess(token);
		} catch (Exception e) {
			return YdUtilResponse.SmsFailure(e.getMessage());
		}
	}

	// 修改登录密码
	public Object resetLoginPasswordSMS(String token) {
		try {
			String salt;
			
			YdSysUserSecurity securityUser;
			
			securityUser = zzSqlQueryUserById( (Integer)session.getAttribute("user_id") );

			salt = zzUtilGenerateSalt();

			token = zzUtilGenerateToken(YdJwtType.SMS_RESETLOGINPASSWORD, salt);
			zzUtilGenerateSms(YdJwtType.SMS_RESETLOGINPASSWORD, securityUser.getUser_mobile(), salt);
			System.out.println( salt );
			return YdUtilResponse.SmsSuccess(token);
		} catch (Exception e) {
			return YdUtilResponse.SmsFailure(e.getMessage());
		}
	}

	// 修改支付密码
	public Object resetPayPasswordSMS(String token) {
		try {
			String salt;

			// YdSysUserJwt jwtUser;

			YdSysUserSecurity securityUser;

			// jwtUser = zzVerifyTokenIsAvailable(YdJwtType.LOGIN, token);

			securityUser = zzSqlQueryUserById( (Integer)session.getAttribute("user_id") );

			salt = zzUtilGenerateSalt();

			token = zzUtilGenerateToken(YdJwtType.SMS_RESETPAYPASSWORD, salt);

			System.out.println( salt );
			zzUtilGenerateSms(YdJwtType.SMS_RESETPAYPASSWORD, securityUser.getUser_mobile(), salt);

			return YdUtilResponse.SmsSuccess(token);
		} catch (Exception e) {
			return YdUtilResponse.SmsFailure(e.getMessage());
		}
	}
	
	
	// 修改手机号
	public Object resetMobileSMS(String token) {
		try {
			String salt;


			YdSysUserSecurity securityUser;

			securityUser = zzSqlQueryUserById( (Integer)session.getAttribute("user_id") );

			salt = zzUtilGenerateSalt();

			token = zzUtilGenerateToken(YdJwtType.SMS_RESETMOBILE, salt);

			System.out.println(salt);
			
			zzUtilGenerateSms(YdJwtType.SMS_RESETMOBILE, securityUser.getUser_mobile(), salt);

			return YdUtilResponse.SmsSuccess(token);
		} catch (Exception e) {
				return YdUtilResponse.SmsFailure(e.getMessage());
		}
	}

}