package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

import com.yd.dby.c.member.entity.YdWish;

public interface YdMemberServiceWish {

	/**
	 * @param ydWish
	 * @return
	 * 方法作用(用户添加许愿)
	 */
	Integer addWish(YdWish ydWish);
	
	/**
	 * @param request
	 * @return
	 * 方法作用(查询当前登录用户的许愿列表)
	 */
	Map<String,Object> queryWishAll(HashMap<String,Object> request); 

}
