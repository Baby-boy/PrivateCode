package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface YdShopMapperInformation {
	
	public List<HashMap<String, Object>> findAll(@Param("page_start") Integer page_start);
	
	public HashMap<String, Object> findById(@Param("id") Integer id);
	
	public Integer total();
	
	public List<HashMap<String, Object>> page(@Param("page_start") Object page_start);
	
	public List<HashMap<String, Object>> messageList(@Param("id") Integer id, @Param("page_start") Integer page_start);
	
	public Integer messageTotal(@Param("id") Integer id);
	
	// 回复留言
	public Integer msgReply(HashMap<String, Object> request);
	
	// 留言
	public Integer Reply(HashMap<String, Object> request);
	
}