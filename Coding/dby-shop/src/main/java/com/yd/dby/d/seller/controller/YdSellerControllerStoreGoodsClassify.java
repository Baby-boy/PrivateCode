package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.d.seller.entity.YdSellerStoreGoodsClassify;
import com.yd.dby.d.seller.service.YdSellerServiceStoreGoodsClassify;

@Controller
@RequestMapping(value = "/api/v1/get/seller/store/classify")
public class YdSellerControllerStoreGoodsClassify {
	
	@Autowired
	private YdSellerServiceStoreGoodsClassify ydSellerServiceStoreGoodsClassify;
	
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreGoodsClassify", "index", request);
	}
	
	public String navCreate() {
		return "seller/temp/classify/classify_create";
	}
	
	@RequestMapping("create")
	public ModelAndView create(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreGoodsClassify", "create", request);
	}
	
	@RequestMapping(value = "store")
	@ResponseBody
	public Object store(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify) {
		return ydSellerServiceStoreGoodsClassify.store(ydSellerStoreGoodsClassify);
	}
	
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreGoodsClassify", "edit", request);
	}
	
	
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify) {
		return ydSellerServiceStoreGoodsClassify.update(ydSellerStoreGoodsClassify);
	}
	
	@RequestMapping(value = "setshow")
	public ModelAndView setDefault(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreGoodsClassify", "setShow", request);
	}
	
	@RequestMapping(value = "destroy")
	public ModelAndView destroy(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreGoodsClassify", "destroy", request);
	}

}
