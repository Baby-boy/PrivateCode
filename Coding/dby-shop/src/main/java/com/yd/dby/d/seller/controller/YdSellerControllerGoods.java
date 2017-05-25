package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.d.seller.service.YdSellerServiceGoods;

@Controller
@RequestMapping(value = "/seller/goods")
public class YdSellerControllerGoods {

	@Autowired
	private YdSellerServiceGoods ydSellerServiceGoods;

	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "index", request);
	}
	
	@RequestMapping(value = "/depot")
	public ModelAndView depot(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "index", request);
	}
	
	@RequestMapping(value = "/illegal")
	public ModelAndView illegal(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "index", request);
	}
	
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "create", request);
	}
	
	@RequestMapping(value = "/store")
	public ModelAndView store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "store", request);
	}
	

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "edit", request);
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "update", request);
	}
	
	@RequestMapping(value = "/destroy")
	public ModelAndView destroy(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceGoods", "destroy", request);
	}
	
	
	/**
	 * 上下架
	 */
	@RequestMapping(value = "/show-hide", method = RequestMethod.POST)
	@ResponseBody
	public Object showhide(Integer id, Integer up) {
		return ydSellerServiceGoods.showhide(id, up);
	}
	
	
	/**
	 * 搜索
	 */
	@RequestMapping(value = "searchName", method = RequestMethod.POST)
	@ResponseBody
	public Object searchName(String goodsName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("p", 1);
		map.put("goods_name", goodsName);
		map.put("depot_type", 1);
		return ydSellerServiceGoods.search( map );
	}
}
