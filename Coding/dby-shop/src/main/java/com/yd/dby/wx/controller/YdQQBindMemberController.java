package com.yd.dby.wx.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.YdMain;

/** 
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月18日 下午1:21:15 
 * 说明(绑定并注册新用户和已有用户)
 */
@Controller
@RequestMapping(value="/thirdPartyLogin")
public class YdQQBindMemberController {

	@RequestMapping(value="bind")
	public String bind (Model model,String openid,String tpl_type){
		model.addAttribute("openid",openid);
		model.addAttribute("tpl_type",Integer.valueOf(tpl_type));
		return "member/security/bind";
	}
	
	@RequestMapping(value="/bind_user")
	public Object bindUser(@RequestBody Map<String,HashMap<String,Object>> request){
		return YdMain.MAV("_YdSysThirdBindServcie","bind", request);
	}
	
	//绑定已有用户
	@RequestMapping(value="/bind_exist_user")
	public Object bindExistUser(@RequestBody Map<String,HashMap<String,Object>> request){
		return YdMain.MAV("_YdSysThirdBindServcie","bindExistUser", request);
	}
	
	
}
