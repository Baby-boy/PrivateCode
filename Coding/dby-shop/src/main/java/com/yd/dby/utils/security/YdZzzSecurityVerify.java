package com.yd.dby.utils.security;

import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.utils.jwt.YdJwtType;

public abstract class YdZzzSecurityVerify extends YdZzzSecuritySQL {

	// 账号是否为空
	protected abstract void zzVerifyUsernameIsEmpty(String username) throws Exception;

	// 密码是否为空
	protected abstract void zzVerifyPasswordIsEmpty(String password) throws Exception;
	
	// 验证两次密码是否相同
	protected abstract void zzVerifyPasswordConfirmation(String password, String passwordConfirmation) throws Exception;

	// 密码是否正确
	protected abstract void zzVerifyPasswordIsAvailable(YdSysUserSecurity user, String password) throws Exception;

	// 检查令牌是否为空
	protected abstract void zzVerifyTokenIsEmpty(String token) throws Exception;

	// 检查令牌是否有效
	protected abstract YdSysUserJwt zzVerifyTokenIsAvailable(YdJwtType type, String token) throws Exception;

	// 检查令牌是否有效
	protected abstract YdSysUserJwt zzVerifyTokenUserIsAvailable(YdJwtType type, String token, String salt) throws Exception;

	// 检查令牌是否有效
	protected abstract void zzVerifyTokenIsAvailable(YdJwtType type, String token, String salt) throws Exception;

	// 检查验证码是否为空
	protected abstract void zzVerifySaltIsEmpty(String salt) throws Exception;
	
	// 检查验证码是否有效 
	protected abstract void zzVerifySaltIsValid(String salt, String token) throws Exception;

	// 用户名是否存在
	protected abstract void zzVerifyUserIsExisit(YdJwtType type, YdSysUserSecurity user) throws Exception;

	// 用户是否有效
	protected abstract void zzVerifyUserIsAvailable(YdSysUserSecurity user) throws Exception;

}