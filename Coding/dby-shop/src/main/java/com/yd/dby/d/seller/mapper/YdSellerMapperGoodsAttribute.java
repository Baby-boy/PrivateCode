package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;


public interface YdSellerMapperGoodsAttribute {
	
	Integer store(HashMap<String, Object> request);
	
	List<Object> lists(HashMap<String, Object> request);
	
	Integer delete(HashMap<String, Object> request);
}