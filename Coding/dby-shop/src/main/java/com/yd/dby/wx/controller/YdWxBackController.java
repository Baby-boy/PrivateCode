package com.yd.dby.wx.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.service.YdSysServiceSecurity;
import com.yd.dby.wx.utils.YdWxParamUtils;

@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/wxAuth")
public class YdWxBackController {
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private YdSysServiceSecurity ydSysServiceSecurity;

	@RequestMapping(value = "")
	public Object callBack(String code) throws ClientProtocolException, IOException {
		System.out.println("code的值为" + code);
		// String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
		// "appid=" + YdWxParamUtils.AppId + "&secret=" +
		// YdWxParamUtils.AppSecret + "&code=" + code +
		// "&grant_type=authorization_code";
		/*
		 * JSONObject doGetJson = YdWxParamUtils.doGetJson(url); String
		 * access_token = (String) doGetJson.getString("access_token"); String
		 * openid = (String) doGetJson.getString("openid");
		 * System.out.println(access_token); System.out.println(openid);
		 */
		/*
		 * String urlInfo =
		 * "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token +
		 * "&openid="+openid + "&lang=zh_CN"; JSONObject userinfo =
		 * YdWxParamUtils.doGetJson(urlInfo); System.out.println(userinfo);
		 */

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + YdWxParamUtils.AppId + "&secret=" + YdWxParamUtils.AppSecret + "&code=" + code + "&grant_type=authorization_code";
		// String url =
		// "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa517d463f51813d1&secret=93af3cd349dccf7057e525326a4a1974&code="
		// + code + "&grant_type=authorization_code";
		JSONObject callBack = YdWxParamUtils.doGetJson(url);
		String openid = (String) callBack.get("openid");
		//查询是否已经关联登陆 
		Map<String,Object> userResult = ydSysServiceSecurity.queryUserByOpenId(openid);
		if (userResult.get("msg")=="success") {
			//已经绑定了用户
			Integer tplUserId = (Integer) userResult.get("tpl_user_id");
			YdSysUserFull yu = ydSysServiceSecurity.queryUserByUserId(tplUserId);
			session.setAttribute("user_id",yu.getUser_id());
			session.setAttribute("user",yu);
			return "redirect:/";
		}else {
			//用户第一次使用第三方登陆还没绑定
			return "redirect:/thirdPartyLogin/bind?openid="+openid+"&tpl_type=1"; 
		}
	}
}
