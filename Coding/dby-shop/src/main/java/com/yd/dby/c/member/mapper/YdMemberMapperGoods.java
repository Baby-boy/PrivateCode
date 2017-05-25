package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface YdMemberMapperGoods {
	
	List<Object> lists(HashMap<String, Object> request);
	
	
	/**
	 * 关注的店铺 - 商品销量倒序排序
	 */
	List<Object> followStoreGoods(@Param("store_id") Integer store_id);

}