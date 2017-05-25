package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;


public interface YdSellerMapperGoodsPic {
	
	List<Object> index(HashMap<String, Object> request);
	
	Integer total(HashMap<String, Object> request);
}