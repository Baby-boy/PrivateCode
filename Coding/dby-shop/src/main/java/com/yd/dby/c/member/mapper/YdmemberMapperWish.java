package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import com.yd.dby.c.member.entity.YdWish;

public interface YdmemberMapperWish {

	/**
	 * @param ydWish
	 * @return
	 * 方法作用(用户添加许愿)
	 */
	Integer addWish(YdWish ydWish);

	/**
	 * @param request
	 * @param user_id
	 * @return
	 * 方法作用(查询当前用户的许愿列表)
	 */
	List<HashMap<String, Object>> queryWishAll(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(查询当前用户的所有许愿数)
	 */
	Integer totalWish(HashMap<String, Object> request);

}
