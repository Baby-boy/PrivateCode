package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.utils.YdUtilCookie;

@Controller
@RequestMapping(value = "/api/v1/get/member/member")
public class YdMemberControllerInfo {

	@Autowired
	private StringRedisTemplate redis;

	@RequestMapping(value = "info")
	public ModelAndView info(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "info", request);
	}

	@RequestMapping(value = "update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "update", request);
	}

	@RequestMapping(value = "security")
	public ModelAndView security(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "info", request);
	}

	@RequestMapping(value = "editPassword")
	public ModelAndView editPassword(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "editPassword", request);
	}

	@RequestMapping(value = "editPasswordVerifyMobile")
	public ModelAndView editPasswordVerifyMobile(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "editPasswordVerifyMobile", request);
	}

	@RequestMapping(value = "jumpEditPassword")
	public ModelAndView jumpEditPassword(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "jumpEditPassword", request);
	}

	@RequestMapping(value = "jumpEidtPasswordSuccess")
	public ModelAndView jumpEidtPasswordSuccess(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "jumpEidtPasswordSuccess", request);
	}

	@RequestMapping(value = "editPasswordSubPass")
	public ModelAndView editPasswordSubPass(@RequestBody Map<String, HashMap<String, Object>> request, HttpServletRequest _request) {
		// 图形验证码是否正确
		String ydCaptchaCodeKey = YdUtilCookie.getCookieValue("ydCaptchaCode", _request);
		request.get("val").put("ydCaptchaCodeKey", ydCaptchaCodeKey);
		return YdMain.MAV("_YdWebServiceMemberInfo", "editPasswordSubPass", request);
	}

	@RequestMapping(value = "balance")
	public ModelAndView balance(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "balance", request);
	}

	@RequestMapping(value = "verifyMobile")
	public ModelAndView verifyMobile(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "verifyMobile", request);
	}

	@RequestMapping(value = "editMobile")
	public ModelAndView editMobile(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "editMobile", request);
	}

	@RequestMapping(value = "verifyMobileSub")
	public ModelAndView verifyMobileSub(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "verifyMobileSub", request);
	}

	@RequestMapping(value = "editMobileSub")
	public ModelAndView editMobileSub(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "editMobileSub", request);
	}

	@RequestMapping(value = "setPayPassword")
	public ModelAndView setPayPassword(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "setPayPassword", request);
	}

	@RequestMapping(value = "setPayPasswordTwo")
	public ModelAndView setPayPasswordTwo(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "setPayPasswordTwo", request);
	}
	
	@RequestMapping(value = "setPayPasswordTwoTwo")
	public String setPayPasswordTwoT(@RequestBody Map<String, HashMap<String, Object>> request) {
		return "member/temp/member/set_pay_password_two";
	}

	@RequestMapping(value = "setPayPasswordThree")
	public ModelAndView setPayPasswordThree(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "setPayPasswordThree", request);
	}
	
	@RequestMapping(value = "setPayPasswordThreeThree")
	public String setPayPasswordThreeThree(@RequestBody Map<String, HashMap<String, Object>> request) {
		return "member/temp/member/set_pay_password_three";
	}

	@RequestMapping(value = "updatePwd")
	public ModelAndView resetPassword(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberInfo", "reset_password", request);
	}

}
