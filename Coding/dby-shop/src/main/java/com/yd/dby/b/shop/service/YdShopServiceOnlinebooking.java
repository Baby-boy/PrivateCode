package com.yd.dby.b.shop.service;

import java.util.HashMap;

import com.yd.dby.b.shop.entity.YdAppOnlineBooking;

public interface YdShopServiceOnlinebooking {
	Object insert(HashMap<String, Object> request);
	/**
	 * 线上预约
	 * @param request
	 * @return
	 */
	Object add(YdAppOnlineBooking request);
}