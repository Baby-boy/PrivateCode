package com.yd.dby.wx.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;

/** 
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月18日 下午2:23:28 
 * 
 */
@SuppressWarnings("all")
public interface YdThirdBindMapper {

	/**
	 * @param yUserSecurity
	 * @return
	 * 方法作用(默认注册新用户)
	 */
	Integer createUser(YdSysUserSecurity yUserSecurity);

	/**
	 * @param request
	 * @return
	 * 方法作用(绑定默认注册的新用户)
	 */
	Integer bindUser(HashMap<String, Object> request);

	/**
	 * @param user_mobile
	 * @return
	 * 方法作用(查询已有用户)
	 */
	YdSysUserFull queryUserByUserMobile(@Param("user_mobile")String user_mobile);

	/**
	 * @param request
	 * @return
	 * 方法作用(绑定已有用户)
	 */
	Integer bindExistUser(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(根据默认添加的用户手机号查询当前用户的id)
	 */
	/*YdSysUserFull queryUserIdByMobile(HashMap<String, Object> request);*/


}
