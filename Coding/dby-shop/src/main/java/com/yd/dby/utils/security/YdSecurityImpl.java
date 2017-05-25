package com.yd.dby.utils.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.a.sys.mapper.YdSysMapperSecurity;
import com.yd.dby.utils.YdUtilTokenOnlyExpire;
import com.yd.dby.utils.jwt.YdJwt;
import com.yd.dby.utils.jwt.YdJwtType;
import com.yd.dby.utils.md5.YdMd5Util;
import com.yd.dby.utils.montnets.YdMontnets;
import com.yd.dby.utils.random.YdUtilRandom;

@Service
public class YdSecurityImpl extends YdZzzSecurityResetPassword implements YdSecurity {

	@Autowired
	private YdSysMapperSecurity mapper;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public YdSysUserJwt verifyToken(String token) {
		try {
			return YdJwt.verifyUser(YdJwtType.LOGIN, token);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected void zzUtilGenerateSms(YdJwtType type, String mobile, String salt) throws Exception {
		switch (type) {

		case SMS_REGISTER:
			YdMontnets.SendMultixSms(mobile, "您的正在注册账号, 验证码是:" + salt);
			return;
		case SMS_FORGETASSWORD:
			YdMontnets.SendMultixSms(mobile, "您的正在找回密码, 验证码是:" + salt);
			return;
		case SMS_RESETLOGINPASSWORD:
			YdMontnets.SendMultixSms(mobile, "您的正在修改登录密码, 验证码是:" + salt);
			return;
		case SMS_RESETPAYPASSWORD:
			YdMontnets.SendMultixSms(mobile, "您的正在修改支付密码, 验证码是:" + salt);
			return;
		case SMS_RESETMOBILE:
			YdMontnets.SendMultixSms(mobile, "您的正在修改手机号码, 验证码是:" + salt);
			return;
		default:
			break;
		}

	}

	@Override
	protected String zzUtilGenerateSalt() throws Exception {
		return YdUtilRandom.number(4);
	}

	@Override
	protected String zzUtilGenerateToken(YdJwtType type, String salt) throws Exception {
		return YdJwt.generate(type, salt);
	}

	@Override
	protected String zzUtilGenerateToken(YdJwtType type, YdSysUserJwt user) throws Exception {
		return YdJwt.generate(type, user);
	}

	@Override
	protected void zzVerifyUsernameIsEmpty(String username) throws Exception {
		if (username == null || username.isEmpty()) {
			throw new Exception();
		}
	}

	@Override
	protected void zzVerifyPasswordIsEmpty(String password) throws Exception {
		if (password == null || password.isEmpty()) {
			throw new Exception();
		}
	}
	
	@Override
	protected void zzVerifyPasswordConfirmation(String password, String passwordConfirmation) throws Exception {
		if ( !password.equals( passwordConfirmation ) ) {
			throw new Exception("两次密码输入不一致");
		}
	}

	@Override
	protected void zzVerifyPasswordIsAvailable(YdSysUserSecurity user, String password) throws Exception {
		if (!password.equals(user.getUser_password())) {
			throw new Exception();
		}
	}

	@Override
	protected void zzVerifyTokenIsEmpty(String token) throws Exception {
		if (token == null || token.isEmpty()) {
			throw new Exception();
		}

	}

	@Override
	protected YdSysUserJwt zzVerifyTokenIsAvailable(YdJwtType type, String token) throws Exception {
		return YdJwt.verifyUser(type, token);
	}

	@Override
	protected YdSysUserJwt zzVerifyTokenUserIsAvailable(YdJwtType type, String token, String salt) throws Exception {
		return YdJwt.verifyUser(type, token, salt);
	}

	@Override
	protected void zzVerifyTokenIsAvailable(YdJwtType type, String token, String salt) throws Exception {
		YdJwt.verify(type, token, salt);
	}

	@Override
	protected void zzVerifySaltIsEmpty(String salt) throws Exception {
		if (salt == null || salt.isEmpty()) {
			throw new Exception("验证码不能为空");
		}
	}
	
	@Override
	protected void zzVerifySaltIsValid(String salt, String token) throws Exception {
		if ( stringRedisTemplate.hasKey( YdMd5Util.GetMD5Code( salt ) ) ) {			
			throw new Exception("手机号验证码已经过期");
		}
		
		try {
			YdUtilTokenOnlyExpire.verification(token, salt);
			stringRedisTemplate.opsForValue().set( YdMd5Util.GetMD5Code( token ), salt );
			stringRedisTemplate.expire( YdMd5Util.GetMD5Code( token ), 60*6, TimeUnit.SECONDS );
		} catch (Exception e) {
			throw new Exception("验证码错误");
		}
	}
	
	@Override
	protected void zzVerifyUserIsExisit(YdJwtType type, YdSysUserSecurity user) throws Exception {
		switch (type) {

		case SMS_REGISTER:
			if (user == null) {
				throw new Exception();
			}
			return;
		case SMS_FORGETASSWORD:
			if (user == null) {
				throw new Exception();
			}
			return;
		case SMS_RESETLOGINPASSWORD:
			if (user == null) {
				throw new Exception();
			}
			return;
		case SMS_RESETPAYPASSWORD:
			if (user == null) {
				throw new Exception();
			}
			return;
		case LOGIN:
			if (user == null) {
				throw new Exception("用户名不存在");
			}
			return;
		case REGISTER_NAME:
			if (user != null) {
				throw new Exception("会员名已经存在");
			}
			return;
		case REGISTER_MOBILE:
			if (user != null) {
				throw new Exception("手机号已经存在");
			}
			return;
		case FORGETASSWORD:
			if (user == null) {
				throw new Exception();
			}
			return;
		case RESETLOGINPASSWORD:
			if (user == null) {
				throw new Exception();
			}
			return;
		case RESETPAYPASSWORD:
			if (user == null) {
				throw new Exception();
			}
			return;
		default:
			break;
		}
	}

	@Override
	protected void zzVerifyUserIsAvailable(YdSysUserSecurity user) throws Exception {

	}

	@Override
	protected YdSysUserSecurity zzSqlQueryUserById(Integer id) throws Exception {
		return mapper.queryUserById(id);
	}

	@Override
	protected YdSysUserSecurity zzSqlQueryUserByName(String name) throws Exception {
		return mapper.queryUserByName(name);
	}
	
	@Override
	protected YdSysUserSecurity zzSqlQueryUserByMobile(String mobile) throws Exception {
		return mapper.queryUserByMobile(mobile);
	}
	
	@Override
	protected YdSysUserFull zzSqlQueryFullUserById(Integer id) throws Exception {
		return mapper.queryFullUserById(id);
	}

	@Override
	protected Integer zzSqlCreateUser(YdSysUserSecurity user) throws Exception {
		return mapper.createUser(user);
	}

	@Override
	protected Integer zzSqlChangeLoginPassword(YdSysUserSecurity user) throws Exception {
		return mapper.changeLoginPasswordById(user);
	}

	@Override
	protected Integer zzSqlChangePayPassword(YdSysUserSecurity user) throws Exception {
		return mapper.changePayPasswordById(user);
	}

	@Override
	protected Integer zzSqlChangeUserDefaultValues(YdSysUserFull user) throws Exception {
		return mapper.changeUserDefaultValues(user);
	}

}