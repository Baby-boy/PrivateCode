package com.yd.dby.c.member.controller;

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
import com.yd.dby.c.member.entity.Address;
import com.yd.dby.c.member.service.YdMemberServiceAddress;

@Controller
@RequestMapping(value = "/api/v1/get/member/address")
public class YdMemberControllerAddress {
	@Autowired
	private YdMemberServiceAddress ydMemberServiceAddress;
	
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceAddress", "index", request);
	}
	
	@RequestMapping(value = "/store")
	public ModelAndView store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceAddress", "store", request);
	}
	
	
	/**
	 * 保存地址
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "storePayPage", method = RequestMethod.POST)
	@ResponseBody 
	public Object storePay(Address address) {
		return ydMemberServiceAddress.storePayPage(address);
	}
	
	
	/**
	 * 保存地址
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "store", method = RequestMethod.POST)
	@ResponseBody 
	public Object storeAdress(@RequestBody Map<String,HashMap<String,Object>> request) {
		return YdMain.MAV("_YdMemberServiceAddress", "storeEntity", request);
	}
	
	@RequestMapping(value = "update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceAddress", "update", request);
	}
	
	@RequestMapping(value = "setdefault")
	public ModelAndView setDefault(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceAddress", "setDefault", request);
	}
	
	@RequestMapping(value = "/destroy")
	public ModelAndView destory(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceAddress", "destroy", request);
	}
}
