package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import com.yd.dby.c.member.entity.Address;


public interface YdMemberMapperAddress {
	
	List<Object> index(HashMap<String, Object> request);
	
	// 添加商家地址
	Integer store(HashMap<String, Object> request);
	
	// 添加商家地址 - 实体类
	Integer storeEntity(HashMap<String, Object> request);
	
	// 修改商家地址
	Integer update(HashMap<String, Object> request);
	
	// 设为默认
	Integer setDefault(HashMap<String, Object> request);
	
	// 删除商家地址
	Integer destroy(HashMap<String, Object> request);

	Integer storePayPage(Address address);
}