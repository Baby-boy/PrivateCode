package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;

@Controller
@RequestMapping(value = "/api/v1/get/member/sale")
public class YdMemberControllerSale {


	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberSale", "index", request);
	}
	
	@RequestMapping(value = "order")
	public ModelAndView order(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberSale", "order", request);
	}
}
