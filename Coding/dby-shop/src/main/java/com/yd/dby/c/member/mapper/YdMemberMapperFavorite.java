package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

public interface YdMemberMapperFavorite {

	List<Object> goods(HashMap<String, Object> request);

	List<Object> ctcs(HashMap<String, Object> request);

	List<HashMap<String, Object>> store(HashMap<String, Object> request);

	Integer total(HashMap<String, Object> request);

	Integer destroy(HashMap<String, Object> request);

}