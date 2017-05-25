package com.yd.dby.c.member.mapper;

import java.util.HashMap;

import com.yd.dby.c.member.entity.User;


public interface YdMemberMapperInfo {
	
	User info(Integer user_id);
	
	User infos(HashMap<String, Object> request);
	
	Integer update(HashMap<String, Object> request);
	
	Integer editLoginPassword(HashMap<String, Object> request);
	
	Integer editPayPassword(HashMap<String, Object> request);
	
	Integer editLoginPayPassword(HashMap<String, Object> request);
	
}