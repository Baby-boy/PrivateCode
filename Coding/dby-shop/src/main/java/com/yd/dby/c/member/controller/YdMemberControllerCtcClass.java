package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.c.member.service.YdMemberServiceCtcClass;

@Controller
@RequestMapping(value = "/api/v1/get/member/ctcclass")
public class YdMemberControllerCtcClass {


	@Autowired
	private YdMemberServiceCtcClass ydMemberServiceCtcClass; 

	
	@RequestMapping(value = "son")
	public ModelAndView son(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtcClass", "son", request);
	}
}
