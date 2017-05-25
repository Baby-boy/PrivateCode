package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface YdShopServiceInformation {
	
	List<HashMap<String, Object>> findAll(Integer page_start);
	
	HashMap<String, Object> findById(Integer id);
	
	Integer total();
	
	Map<String, Object> page(HashMap<String, Object> request);
	
	List<HashMap<String, Object>> messageList(Integer id, Integer page_start);
	
	Integer messageTotal(Integer id);
	
	Map<String, Object> messagePage(HashMap<String, Object> request);
	
	// 信息商城  -- 回复留言
	HashMap<String, Object> msgReply(HashMap<String, Object> request);
	
	// 信息商城  -- 留言
	HashMap<String, Object> Reply(HashMap<String, Object> request);
	
}
