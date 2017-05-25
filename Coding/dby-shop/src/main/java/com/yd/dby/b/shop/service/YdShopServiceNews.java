package com.yd.dby.b.shop.service;

import java.util.HashMap;

public interface YdShopServiceNews {

	Object hot(HashMap<String, Object> request);

	Object count(HashMap<String, Object> request);

	Object selectAll(HashMap<String, Object> request);

	Object hot2(HashMap<String, Object> request);

	Object count2(HashMap<String, Object> request);

	Object selectAll2(HashMap<String, Object> request);

}