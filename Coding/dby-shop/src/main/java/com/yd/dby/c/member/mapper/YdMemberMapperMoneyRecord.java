package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;


public interface YdMemberMapperMoneyRecord {
	
	List<Object> lists(HashMap<String, Object> request);
	
	Integer total(HashMap<String, Object> request);
}