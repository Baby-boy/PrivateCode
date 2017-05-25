package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;

@Controller
@RequestMapping(value = "/api/v1/get/seller/setting")
public class YdSellerControllerStore {

	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStore", "setting", request);
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStore", "update", request);
	}
	
	@RequestMapping("/slide")
	public String slide() {
		return "seller/temp/store/slide";
	}
	
	@RequestMapping("/slide_update")
	public ModelAndView slideUpdate(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceStore", "slideUpdate", request);
	}
}
