package com.yd.dby.utils.security;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;

public abstract class YdZzzSecuritySQL {

	// 通过用户编号查找账号
	protected abstract YdSysUserSecurity zzSqlQueryUserById(Integer id) throws Exception;

	// 通过用户名查找账号
	protected abstract YdSysUserSecurity zzSqlQueryUserByName(String name) throws Exception;
	
	// 通过手机号查找账号
	protected abstract YdSysUserSecurity zzSqlQueryUserByMobile(String mobile) throws Exception;

	// 通过编号查找账号
	protected abstract YdSysUserFull zzSqlQueryFullUserById(Integer id) throws Exception;

	// 注册用户
	protected abstract Integer zzSqlCreateUser(YdSysUserSecurity user) throws Exception;

	// 修改登录密码
	protected abstract Integer zzSqlChangeLoginPassword(YdSysUserSecurity user) throws Exception;

	// 修改支付密码
	protected abstract Integer zzSqlChangePayPassword(YdSysUserSecurity user) throws Exception;

	protected abstract Integer zzSqlChangeUserDefaultValues(YdSysUserFull user) throws Exception;
}
