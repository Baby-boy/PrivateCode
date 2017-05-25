package com.yd.dby.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/wx")
@Controller
public class YdWxLoginController {
	
//	public static final String redirect_url= "http://dbywx.tunnel.2bdata.com/wxAuth";
	public static final String redirect_url= "https://www.wadby.com/";
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("login")
	public String wxLogin(){
		String url = 
				"https://open.weixin.qq.com/connect/qrconnect?"
				+ "appid=wxa517d463f51813d1"
				+ "&redirect_uri=https://www.wadby.com/wxAuth"
				+ "&response_type=code"
				+ "&scope=snsapi_login"
				+ "#wechat_redirect";	
		return "redirect:"+url;
	} 
}
