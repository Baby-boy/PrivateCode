package com.yd.dby.b.shop.service.impl;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdAppOnlineBooking;
import com.yd.dby.b.shop.mapper.YdShopMapperOnlinebooking;
import com.yd.dby.b.shop.service.YdShopServiceOnlinebooking;
import com.yd.dby.utils.YdUtilResponse;

@Service("_Onlinebooking")
public class YdShopServiceImplOnlinebooking implements YdShopServiceOnlinebooking {
	@Autowired
	private YdShopMapperOnlinebooking ydShopMapperOnlinebooking;
	
	@Autowired
	private HttpSession session;
	

	@Override
	public Object insert(HashMap<String, Object> request) {
		return ydShopMapperOnlinebooking.insert(request);
	}
	
	
	/**
	 * 线上预约
	 */
	@Override
	public Object add(YdAppOnlineBooking request) {
		
		//获取当前时间戳
		Date date = new Date();
		long time = date.getTime();
		String created_time = time+"";
		try {
			Integer user_id = (Integer) session.getAttribute("user_id");
			request.setUser_id(user_id);
			request.setCreated_time(created_time);
			ydShopMapperOnlinebooking.add(request);
			return YdUtilResponse.success(null, "提交成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("提交失败");
		}
	}
}