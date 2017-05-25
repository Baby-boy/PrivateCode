package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

public interface YdShopMapperArticle {

	List<Object> footer();
	
	Object info(Integer id);
	
	List<Object> indexNews();

	List<Object> lists(HashMap<String, Object> request);
	
	Integer total(HashMap<String, Object> request);

	List<Object> footer1();
}