package com.yd.dby.d.seller.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.d.seller.entity.YdSellerSeckillGoods;
import com.yd.dby.d.seller.service.YdSellerServiceSeckill;

@Controller
@RequestMapping(value = "seller/seckill")
public class YdSellerControllerSeckill {
	
	@Autowired
	YdSellerServiceSeckill ydSellerServiceSeckill;
	
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("")
	public Object index(HashMap<String, Object> map) {
		map.put("data", ydSellerServiceSeckill.lists( map ));
		return "seller/temp/sales/seckill";
	}
	
	
	/**
	 * 创建
	 * @return
	 */
	@RequestMapping("create")
	public Object create(HashMap<String, Object> map) {
		return "seller/temp/sales/seckill_create";
	}
	
	
	/**
	 * 取消
	 */
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	@ResponseBody
	public Object cancel(Integer sgId) {
		return ydSellerServiceSeckill.cancel(sgId);
	}
	
	
	/**
	 * 添加
	 * @return
	 */
	@RequestMapping(value = "store", method = RequestMethod.POST)
	@ResponseBody
	public Object store(Integer depotId, float price, Integer num) {
		return ydSellerServiceSeckill.store(depotId, price, num);
	}
}
