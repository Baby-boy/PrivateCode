package com.yd.dby.utils.security;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.d.seller.entity.YdSellerCompany;
import com.yd.dby.d.seller.entity.YdSellerStore;
import com.yd.dby.d.seller.mapper.YdSellerMapperCompany;
import com.yd.dby.d.seller.mapper.YdSellerMapperStore;
import com.yd.dby.utils.jwt.YdJwtType;
import com.yd.dby.utils.md5.YdMd5Util;
import com.yd.dby.utils.response.YdUtilResponse;

public abstract class YdZzzSecurityRegister extends YdZzzSecuritySms {
	
	@Autowired
	YdSellerMapperStore ydSellerMapperStore;
	
	@Autowired
	YdSellerMapperCompany ydSellerMapperCompany;

	// 注册
	@Transactional(rollbackFor = Exception.class)
	public Object register(String token, String salt, String mobile, String password, String username) {
		try {
			YdSysUserJwt jwtUser;

			YdSysUserSecurity securityUser;

			YdSysUserFull fullUser;

			// 账号是否为空
			zzVerifyUsernameIsEmpty(mobile);

			// 密码是否为空
			zzVerifyPasswordIsEmpty(password);
			
//			// 检查令牌是否为空
//			zzVerifyTokenIsEmpty(token);
//
//			// 检查验证码是否为空
			zzVerifySaltIsEmpty(salt);
			
			// 检查验证码是否有效
			zzVerifySaltIsValid(salt, token);
//
//			// 验证令牌是否有效
//			zzVerifyTokenIsAvailable(YdJwtType.SMS_REGISTER, token, salt);

			// 通过用户名查找账号
			securityUser = zzSqlQueryUserByName(username);
			
			// 会员名是否存在
			zzVerifyUserIsExisit(YdJwtType.REGISTER_NAME, securityUser);
			
			// 通过手机号查找账号
			securityUser = zzSqlQueryUserByMobile(mobile);

			// 手机号是否存在
			zzVerifyUserIsExisit(YdJwtType.REGISTER_MOBILE, securityUser);

			// 构建实体类
			securityUser = new YdSysUserSecurity(mobile, password, username);

			// 注册用户
			zzSqlCreateUser(securityUser);

			// 构建实体类
			fullUser = new YdSysUserFull(securityUser);

			// 拉取用户基本信息
			zzSqlChangeUserDefaultValues(fullUser);

			// 构建实体类
			jwtUser = new YdSysUserJwt(securityUser);

			// 生成令牌
			token = zzUtilGenerateToken(YdJwtType.LOGIN, jwtUser);

			// 返回用户实体
			return YdUtilResponse.RegisterSuccess(token, fullUser);
		} catch (Exception e) {
			return YdUtilResponse.RegisterFailure(e.getMessage());
		}
	}
	
	
	// 注册店铺 
	@Transactional(rollbackFor = Exception.class)
	public Object register_seller(HashMap<String, Object> request) {
		YdSysUserJwt jwtUser;

		YdSysUserSecurity securityUser;

		YdSysUserFull fullUser;

		try {
			String username = request.get("user_username").toString();
			String mobile = request.get("user_mobile").toString();
			String password = request.get("password").toString();
			String passwordConfirmation = request.get("password_confirmation").toString();
			String salt = request.get("mobile_code").toString();
			String token = request.get("token").toString();
			
			// 手机号是否为空
			zzVerifyUsernameIsEmpty(mobile);

			// 密码是否为空
			zzVerifyPasswordIsEmpty(password);
			
			// 检查两次密码输入是否一致
			zzVerifyPasswordConfirmation(password, passwordConfirmation);
			
			// 检查验证码是否为空
			zzVerifySaltIsEmpty(salt);
			
			// 检查验证码是否有效
			zzVerifySaltIsValid(salt, token);
			
			// 通过用户名查找账号
			securityUser = zzSqlQueryUserByName(username);

			// 用户名是否存在
			zzVerifyUserIsExisit(YdJwtType.REGISTER_NAME, securityUser);
			
			// 通过手机号查找账号
			securityUser = zzSqlQueryUserByMobile(mobile);

			// 手机号是否存在
			zzVerifyUserIsExisit(YdJwtType.REGISTER_MOBILE, securityUser);
			
			// 构建实体类
			securityUser = new YdSysUserSecurity(mobile, YdMd5Util.GetMD5Code( password ), username );
			
			// 构建实体类
			fullUser = new YdSysUserFull(securityUser);

			// 注册用户
			zzSqlCreateUser(securityUser);
			
			// 注册商家
			YdSellerStore ydSellerStore = new YdSellerStore();
			ydSellerStore.setUser_id( securityUser.getUser_id() );
			ydSellerStore.setStore_name( request.get("store_name").toString() );
			ydSellerMapperStore.create(ydSellerStore);

			// 注册公司
			YdSellerCompany ydSellerCompany = new YdSellerCompany();
			ydSellerCompany.setUser_id( securityUser.getUser_id() );
			ydSellerCompany.setStore_id( ydSellerStore.getStore_id() );
			ydSellerCompany.setCompany_name( request.get("company_name").toString() );
			ydSellerCompany.setCompany_address( request.get("company_address").toString() );
			ydSellerCompany.setCompany_business_licence( request.get("company_business_licence").toString() );
			ydSellerCompany.setCompany_organization_code( request.get("company_organization_code").toString() );
			ydSellerCompany.setCompany_bank_account( request.get("company_bank_account").toString() );
			ydSellerCompany.setLegal_person_positive( request.get("legal_person_positive").toString() );
			ydSellerCompany.setLegal_person_negative( request.get("legal_person_negative").toString() );
			ydSellerMapperCompany.create(ydSellerCompany);
			
			// 构建实体类
			jwtUser = new YdSysUserJwt(securityUser);
			
			// 生成令牌
			token = zzUtilGenerateToken(YdJwtType.LOGIN, jwtUser);

			return YdUtilResponse.RegisterSuccess(token, fullUser);
		} catch (Exception e) {
			return YdUtilResponse.RegisterFailure(e.getMessage());
		}
	}

}