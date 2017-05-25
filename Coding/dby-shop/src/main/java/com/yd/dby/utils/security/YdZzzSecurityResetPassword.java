package com.yd.dby.utils.security;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.utils.jwt.YdJwtType;
import com.yd.dby.utils.response.YdUtilResponse;

public abstract class YdZzzSecurityResetPassword extends YdZzzSecurityLogin {

	// 修改登录密码
	@Transactional(rollbackFor = Exception.class)
	public Object forgetLoginPassword(String token, String salt, String password) throws Exception {

		YdSysUserJwt jwtUser;

		YdSysUserSecurity securityUser;

		// 检查令牌是否为空
		zzVerifyTokenIsEmpty(token);

		// 检查验证码是否为空
		zzVerifySaltIsEmpty(salt);

		// 验证令牌是否有效
		jwtUser = zzVerifyTokenUserIsAvailable(YdJwtType.SMS_FORGETASSWORD, token, salt);

		// 查找账号
		securityUser = zzSqlQueryUserById(jwtUser.getId());

		// 用户名是否存在
		zzVerifyUserIsExisit(YdJwtType.FORGETASSWORD, securityUser);

		// 用户是否有效
		zzVerifyUserIsAvailable(securityUser);
		
		securityUser.setUser_password(password);
		// 重置密码
		zzSqlChangeLoginPassword(securityUser);

		return YdUtilResponse.ForgetPasswordSuccess();
	}

	// 修改登录密码
	@Transactional(rollbackFor = Exception.class)
	public Object resetLoginPassword(String token, String salt, String password) throws Exception {

		YdSysUserJwt jwtUser;

		YdSysUserSecurity securityUser;

		// 检查令牌是否为空
		zzVerifyTokenIsEmpty(token);

		// 检查验证码是否为空
		zzVerifySaltIsEmpty(salt);

		// 验证令牌是否有效
		jwtUser = zzVerifyTokenUserIsAvailable(YdJwtType.SMS_RESETLOGINPASSWORD, token, salt);

		// 查找账号
		securityUser = zzSqlQueryUserById(jwtUser.getId());

		// 用户名是否存在
		zzVerifyUserIsExisit(YdJwtType.RESETLOGINPASSWORD, securityUser);

		// 用户是否有效
		zzVerifyUserIsAvailable(securityUser);
		securityUser.setUser_password(password);
		// 重置密码
		zzSqlChangeLoginPassword(securityUser);

		return YdUtilResponse.ForgetPasswordSuccess();
	}

	// 修改支付密码
	@Transactional(rollbackFor = Exception.class)
	public Object resetPayPassword(String token, String salt, String password) throws Exception {

		YdSysUserJwt jwtUser;

		YdSysUserSecurity securityUser;

		// 检查令牌是否为空
		zzVerifyTokenIsEmpty(token);

		// 检查验证码是否为空
		zzVerifySaltIsEmpty(salt);

		// 验证令牌是否有效
		jwtUser = zzVerifyTokenUserIsAvailable(YdJwtType.SMS_RESETPAYPASSWORD, token, salt);

		// 查找账号
		securityUser = zzSqlQueryUserById(jwtUser.getId());

		// 用户名是否存在
		zzVerifyUserIsExisit(YdJwtType.RESETPAYPASSWORD, securityUser);

		// 用户是否有效
		zzVerifyUserIsAvailable(securityUser);

		securityUser.setUser_password(password);

		// 重置密码
		zzSqlChangeLoginPassword(securityUser);

		return YdUtilResponse.ForgetPasswordSuccess();
	}

}