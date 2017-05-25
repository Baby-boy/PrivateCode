package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import com.yd.dby.c.member.entity.User;

public interface YdMemberMapperUser {

	List<Object> index(HashMap<String, Object> request);

	/* 统计-用户全部订单 */
	Integer dataOrderTotal(HashMap<String, Object> request);

	/* 统计-用户全部优惠券 */
	Integer dataCouponTotal(HashMap<String, Object> request);

	/* 统计-用户全部收藏 */
	List<Object> dataFollowTotal(HashMap<String, Object> request);

	/* 修改手机号 */
	Integer updateMobile(HashMap<String, Object> request);

	// 查询个人详情
	User queryDetailByUid(Integer uid);

	User findByMobile(String string);
}