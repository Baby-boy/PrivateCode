package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;

@Controller
@RequestMapping(value = "/api/v1/get/member/follow")
public class YdMemberControllerFavorite {

	@RequestMapping(value = "goods")
	public ModelAndView goods(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceFavorite", "goods", request);
	}

	@RequestMapping(value = "store")
	public ModelAndView store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceFavorite", "store", request);
	}

	@RequestMapping(value = "ctcs")
	public ModelAndView ctcs(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceFavorite", "ctcs", request);
	}

	@RequestMapping(value = "destroy")
	public ModelAndView destroy(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceFavorite", "destroy", request);
	}
}
