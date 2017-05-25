package com.yd.gcj.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/section")
public class YdMangerPageRouterSection<HttpServletRequest> {
	
	private static final String pageFiles1 = "section/article/";
	private static final String pageFiles2 = "section/auth/";
	
	@RequestMapping("/article/classify_create")
	public String classifyCreate() {
		return pageFiles1+"classify_create";
	}
	
	@RequestMapping("/article/")
	public String defualt() {
		return pageFiles1+"index";
	}
	
	@RequestMapping("/article/classify_edit")
	public String classifyEdit() {
		return pageFiles1+"classify_edit";
	}
	
	
	
	
	
	@RequestMapping("/auth/forget")
	public String forget() {
		return pageFiles2+"forget";
	}
	
	@RequestMapping("/auth/login")
	public String login() {
		return pageFiles2+"login";
	}
	
	@RequestMapping("/auth/register")
	public String register() {
		return pageFiles2+"register";
	}
	
}
