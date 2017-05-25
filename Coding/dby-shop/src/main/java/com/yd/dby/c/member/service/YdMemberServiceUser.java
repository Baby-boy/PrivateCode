package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

import com.yd.dby.c.member.entity.User;

public interface YdMemberServiceUser {

	/* 个人中心首页 */
	Map<String, Object> index(HashMap<String, Object> request);

	// 查询个人详情
	User queryDetailByUid(Integer uid);

}
