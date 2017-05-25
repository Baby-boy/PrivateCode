package com.yd.dby.a.sys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yd.dby.a.sys.entity.YdSysThirdPartyLoginUser;
import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;

public interface YdSysMapperSecurity {

	@Select("select user_id, user_username, user_nickname, user_mobile, user_password, user_role from yd_user where user_username = #{name} limit 1")
	@ResultType(YdSysUserSecurity.class)
	YdSysUserSecurity queryUserByName(@Param("name") String name);

	@Select("select user_id, user_username, user_nickname, user_mobile, user_password, user_role from yd_user where user_mobile = #{mobile} limit 1")
	@ResultType(YdSysUserSecurity.class)
	YdSysUserSecurity queryUserByMobile(@Param("mobile") String mobile);

	@Select("select user_id, user_username, user_mobile, user_nickname, user_password, user_role from yd_user where user_id = #{id}")
	@ResultType(YdSysUserSecurity.class)
	YdSysUserSecurity queryUserById(@Param("id") Integer id);

	@Select("SELECT " + "user_id," + "user_sex," + "user_username," + "user_nickname," + "user_avatar," + "user_address," + "user_birthday," + "user_grade," + "user_role,"
			+ "user_province_id," + "user_city_id," + "user_area_id," + "user_province_value," + "user_city_value," + "user_area_value," + "user_account_balance,"
			+ "user_integration," + "user_total_coupon," + "user_total_bankcard," + "user_im_token" + " FROM yd_user WHERE user_id = #{id}")
	@ResultType(YdSysUserFull.class)
	YdSysUserFull queryFullUserById(@Param("id") Integer id);

	@Insert("insert yd_user(user_mobile,user_password, user_username,user_birthday,user_created_time,user_login_time,user_old_login_time)value(#{user_mobile},#{user_password}, #{user_username},now(),now(),now(),now())")
	@ResultType(YdSysUserSecurity.class)
	@Options(useGeneratedKeys = true, keyProperty = "user_id")
	Integer createUser(YdSysUserSecurity user);

	@Update("update yd_user set user_password = #{password} where user_id = #{id}")
	Integer changeLoginPasswordById(YdSysUserSecurity user);

	@Update("update yd_user set user_payment_password = #{password} where user_id = #{id}")
	Integer changePayPasswordById(YdSysUserSecurity user);

	@Update("update yd_user set" + " user_sex = #{user_sex}," + " user_nickname = #{user_nickname}," + " user_avatar = #{user_avatar}," + " user_email = #{user_email},"
			+ " user_address = #{user_address}," + " user_birthday = #{user_birthday}," + " user_grade = #{user_grade}," + " user_province_id = #{user_province_id},"
			+ " user_city_id = #{user_city_id}," + " user_area_id = #{user_area_id}," + " user_province_value = #{user_province_value}," + " user_city_value = #{user_city_value},"
			+ " user_area_value = #{user_area_value}," + " user_account_balance = #{user_account_balance}," + " user_integration = #{user_integration},"
			+ " user_total_coupon = #{user_total_coupon}," + " user_total_bankcard = #{user_total_bankcard}" + " WHERE user_id = #{user_id}")
	@ResultType(YdSysUserFull.class)
	Integer changeUserDefaultValues(YdSysUserFull user);

	/**
	 * @param openid
	 * @return
	 * 方法作用(根据第三方返回的唯一标识查询当前用户是否存在)
	 */
	YdSysThirdPartyLoginUser queryUserByOpenId(@Param("tpl_openid")String openid);

	/**
	 * @param tplUserId
	 * @return
	 * 方法作用(查询用户信息)
	 */
	YdSysUserFull queryUserInfoById(Integer tplUserId);

}
