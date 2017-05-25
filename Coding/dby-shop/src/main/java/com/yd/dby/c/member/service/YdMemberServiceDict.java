package com.yd.dby.c.member.service;
import java.util.HashMap;
import java.util.List;
public interface YdMemberServiceDict {
	
	List<Object> listByCode(String dt_code);
	
    List<HashMap<String, Object>> list(String dict_type);
    List<HashMap<String, Object>> list(String dict_type, String dict_pid);
}