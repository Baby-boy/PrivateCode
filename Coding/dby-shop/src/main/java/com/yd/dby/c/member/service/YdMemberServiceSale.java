package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

public interface YdMemberServiceSale {
	
	/*我的拍卖列表*/
	Map<String, Object> index(HashMap<String, Object> request);
	
	
	/*我的拍卖订单列表*/
	Map<String, Object> order(HashMap<String, Object> request);
}
