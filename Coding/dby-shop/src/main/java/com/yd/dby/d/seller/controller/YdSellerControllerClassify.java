package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;

@Controller
@RequestMapping(value = "/api/v1/get/seller/classify")
public class YdSellerControllerClassify {

	@RequestMapping(value = "")
	public ModelAndView son(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceClassify", "son", request);
	}

}
