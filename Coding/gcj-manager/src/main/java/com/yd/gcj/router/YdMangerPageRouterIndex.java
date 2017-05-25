package com.yd.gcj.router;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YdMangerPageRouterIndex {
	
	@RequestMapping("/")
	public String defualt(HttpServletRequest httpServletRequest) {
		return "index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}
