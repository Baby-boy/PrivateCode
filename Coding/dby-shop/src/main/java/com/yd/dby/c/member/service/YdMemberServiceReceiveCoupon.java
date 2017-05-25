package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

public interface YdMemberServiceReceiveCoupon {
	
	/*用户领取优惠券列表*/
	Map<String, Object> index(HashMap<String, Object> request);
}