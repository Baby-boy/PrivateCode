package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;

@Controller
@RequestMapping(value = "/api/v1/get/member/public")
public class YdMemberControllerPublic {

	/**
	 * 发送手机验证码-用户手机号
	 */
	@RequestMapping(value = "sendUserMobileCode")
	public ModelAndView sendUserMobileCode(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceSendMobileCode", "sendUserMobileCode", request);
	}

	/**
	 * 发送手机验证码-提交手机号
	 */
	@RequestMapping(value = "sendMobileCode")
	public ModelAndView sendMobileCode(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServiceSendMobileCode", "sendMobileCode", request);
	}

	/**
	 * 验证手机和验证码-提交
	 */
	@RequestMapping(value = "verifyMobileCode")
	public ModelAndView verifyMobileCode(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberPublic", "verifyMobileCode", request);
	}

	/**
	 * 验证手机和验证码-跳转
	 */
	@RequestMapping(value = "verifyMobileSuccess")
	public ModelAndView verifyMobileSuccess(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberPublic", "verifyMobileSuccess", request);
	}

}
