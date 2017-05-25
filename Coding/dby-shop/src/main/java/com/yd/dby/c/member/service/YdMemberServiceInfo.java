package com.yd.dby.c.member.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public interface YdMemberServiceInfo {

	/* 用户信息 */
	Object info(HashMap<String, Object> request);

	/* 用户信息-更新 */
	Map<String, Object> update(HashMap<String, Object> request) throws ParseException;

	/* 修改登录密码 */
	Object editPassword(HashMap<String, Object> request);

	/* 修改登录密码，验证手机号 */
	Object editPasswordVerifyMobile(HashMap<String, Object> request);

	/* 修改密码-跳转输入密码页面 */
	Object jumpEditPassword(HashMap<String, Object> request);

	/* 修改密码-成功页面 */
	Object jumpEidtPasswordSuccess(HashMap<String, Object> request);

	/* 修改登录密码，提交密码 */
	Object editPasswordSubPass(HashMap<String, Object> request);

	/* 账户余额 */
	Object balance(HashMap<String, Object> request);

	/* 验证手机号 */
	Object verifyMobile(HashMap<String, Object> request);

	/* 修改手机号 */
	Object editMobile(HashMap<String, Object> request);

	/* 修改手机号-提交 */
	Object editMobileSub(HashMap<String, Object> request);

	/* 验证手机号-提交 */
	Object verifyMobileSub(HashMap<String, Object> request);

	/* 设置支付密码 */
	Object setPayPassword(HashMap<String, Object> request);

	/* 设置支付密码-第二步，输入支付密码 */
	Object setPayPasswordTwo(HashMap<String, Object> request);

	/* 设置支付密码-第二步，输入支付密码 */
	Object setPayPasswordTwoTwo(HashMap<String, Object> request);

	/* 设置支付密码-第三步，跳转设置成功界面 */
	Object setPayPasswordThree(HashMap<String, Object> request);

	Map<String, Object> reset_password(HashMap<String, Object> request);

}
