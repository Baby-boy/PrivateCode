package com.yd.dby.utils.security;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.d.seller.entity.YdSellerStore;
import com.yd.dby.d.seller.mapper.YdSellerMapperStore;
import com.yd.dby.utils.jwt.YdJwtType;
import com.yd.dby.utils.response.YdUtilResponse;

public abstract class YdZzzSecurityLogin extends YdZzzSecurityRegister {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperStore ydSellerMapperStore;
	
	// 账号密码登录
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
	public Object login(String mobile, String password) {
		
		try {
			String token;

			YdSysUserJwt jwtUser;

			YdSysUserSecurity securityUser;

			YdSysUserFull fullUser;

			// 账号是否为空
			zzVerifyUsernameIsEmpty(mobile);

			// 密码是否为空
			zzVerifyPasswordIsEmpty(password);

			// 查找账号
			securityUser = zzSqlQueryUserByMobile(mobile);

			// 手机号是否存在
			zzVerifyUserIsExisit(YdJwtType.LOGIN, securityUser);

			// 手机号是否有效
			zzVerifyUserIsAvailable(securityUser);

			// 密码是否正确
			zzVerifyPasswordIsAvailable(securityUser, password);

			jwtUser = new YdSysUserJwt(securityUser);

			// 生成令牌
			token = zzUtilGenerateToken(YdJwtType.LOGIN, jwtUser);

			// 拉取用户基本信息
			fullUser = zzSqlQueryFullUserById(securityUser.getUser_id());
			
			// 获取店铺信息
			HashMap<String, Object> storeMap = new HashMap<String, Object>();
			storeMap = (HashMap<String, Object>) ydSellerMapperStore.infoUserId( securityUser.getUser_id() );
			
			if ( storeMap != null) {
				YdSellerStore ydSellerStore = new YdSellerStore();
				ydSellerStore.setStore_id( Integer.parseInt( storeMap.get("storeId").toString() ) );
				ydSellerStore.setStore_name( storeMap.get("storeName").toString() );
				session.setAttribute("store", ydSellerStore);
				session.setAttribute("store_id", ydSellerStore.getStore_id());
			} else {
				session.setAttribute("store", "");
			}
			
			session.setAttribute("user", fullUser);
			session.setAttribute("user_id", fullUser.getUser_id());

			// 返回用户
			return YdUtilResponse.LoginSuccess(token, fullUser);

		} catch (Exception e) {

			return YdUtilResponse.LoginFailure(e.getMessage());
		}
	}

	// 令牌登录
	@Transactional(rollbackFor = Exception.class)
	public Object login(String token) {
		try {
			YdSysUserJwt jwtUser;

			YdSysUserSecurity securityUser;

			YdSysUserFull fullUser;

			// 检查令牌是否为空
			zzVerifyTokenIsEmpty(token);

			// 验证令牌是否有效
			jwtUser = zzVerifyTokenIsAvailable(YdJwtType.LOGIN, token);

			// 查找账号
			securityUser = zzSqlQueryUserById(jwtUser.getId());

			// 用户名是否存在
			zzVerifyUserIsExisit(YdJwtType.LOGIN, securityUser);

			// 用户是否有效
			zzVerifyUserIsAvailable(securityUser);

			// 生成令牌
			token = zzUtilGenerateToken(YdJwtType.LOGIN, jwtUser);

			// 拉取用户基本信息
			fullUser = zzSqlQueryFullUserById(securityUser.getUser_id());

			// 返回用户实体
			return YdUtilResponse.LoginSuccess(token, fullUser);
		} catch (Exception e) {
			return YdUtilResponse.LoginFailure(e.getMessage());
		}
	}

}