package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;

@Controller
@RequestMapping(value = "/api/v1/get/seller/nav")
public class YdSellerControllerStoreNav {
	
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreNav", "index", request);
	}
	
	@RequestMapping("create")
	public String navCreate() {
		return "seller/temp/nav/nav_create";
	}
	
	@RequestMapping(value = "store")
	public ModelAndView store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreNav", "store", request);
	}
	
	@RequestMapping(value = "edit")
	public ModelAndView edit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreNav", "edit", request);
	}
	
	@RequestMapping(value = "update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreNav", "update", request);
	}
	
	@RequestMapping(value = "setshow")
	public ModelAndView setDefault(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreNav", "setShow", request);
	}
	
	@RequestMapping(value = "destroy")
	public ModelAndView destroy(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStoreNav", "destroy", request);
	}

}
