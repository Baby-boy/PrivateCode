package com.yd.dby.b.shop.service;

import java.util.HashMap;


public interface YdShopServiceCart {
	
	Object lists(Integer p);
	
	Object window();
	
	Object store(HashMap<String, Object> request);
	
	Object update(HashMap<String, Object> request);
	
	Object total();
}
