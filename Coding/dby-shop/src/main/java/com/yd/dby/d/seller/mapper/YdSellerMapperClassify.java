package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;


public interface YdSellerMapperClassify {
	
	List<Object> one(HashMap<String, Object> request);
	
	List<Object> son(HashMap<String, Object> request);
	
	Object info(Integer id);

}