package com.yd.dby.b.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.b.shop.mapper.YdShopMapperWish;

@Controller
@RequestMapping(value = "/simple")
public class YdShopControllerSimple {
	@Autowired
	private HttpServletRequest ydRequest;

	@Autowired
	private YdShopMapperWish ydShopMapperWish;

//	@RequestMapping(value = "/insert/wish")
//	public ModelAndView page(@RequestBody Map<String, HashMap<String, Object>> request) {
//		return YdUtilApp.MAV("_Wish", "insert", request);
//	}
}
