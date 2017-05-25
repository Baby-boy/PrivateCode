package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerSystemAdmin;

public interface YdMangerMapperSystemAdmin {

	/**
	 * description(查询所有的管理员)
	 * @param
	 * @return
	 */
	List<YdMangerSystemAdmin> queryAllAdmin(@Param("admin_name")String admin_name,@Param("admin_is_super")Integer admin_is_super);

	/**
	 * description(添加管理员)
	 * @param
	 * @param ydMangerAdmin
	 * @return
	 */
	Integer addAdmin(YdMangerSystemAdmin ydMangerAdmin);

	/**
	 * description(根据指定的admin_id删除管理员)
	 * @param
	 * @param admin_id
	 * @return
	 */
	Integer deleteAdmin(Integer admin_id);

	/**
	 * description(根据指定的id修改管理员信息)
	 * @param
	 * @param ydMangerSystemAdmin
	 * @return
	 */
	Integer updateAdmin(YdMangerSystemAdmin ydMangerSystemAdmin);

	/**
	 * description(ajax验证手机号码的唯一性)
	 * @param
	 * @param admin_phone
	 * @return
	 */
	YdMangerSystemAdmin queryAdminByAdminPhon(String admin_phone);
	
	/**
	 * description(ajax验证用户名称的唯一性)
	 * @param
	 * @param admin_name
	 * @return
	 */
	YdMangerSystemAdmin queryAdminByAdmiName(String admin_name);

	/**
	 * description(ajax验证管理员账号的唯一性)
	 * @param
	 * @param admin_account
	 * @return
	 */
	YdMangerSystemAdmin queryAdminByAdminAccount(String admin_account);

	/**
	 * description(修改之前先根据admin_id查到当前管理员信息)
	 * @param
	 * @param admin_id
	 * @return
	 */
	YdMangerSystemAdmin queryAdminByAdminId(Integer admin_id);

	/**
	 * description(管理员登陆)
	 * @param
	 * @param admin_account
	 * @param admin_password
	 * @return
	 */
	YdMangerSystemAdmin queryAdminByUserAccountAndUserPassword(@Param("admin_account")String admin_account, @Param("admin_password")String admin_password);

	/**
	 * description(修改管理员密码)
	 * @param
	 * @param admin_id
	 * @param newpassword
	 * @return
	 */
	Integer updateAdminPassword(@Param("admin_id")Integer admin_id,@Param("admin_newpassword")String newpassword);

	/**
	 * description(登录成功之后修改登录 次数)
	 * @param
	 * @param admin_id
	 * @return
	 */
	Integer updateAdminByAdminId(YdMangerSystemAdmin systemAdmin);

}
