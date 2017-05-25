package com.yd.gcj.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work")
public class YdMangerPageRouterWork<HttpServletRequest> {
	
	private static final String pageFiles = "work/";
	
	@RequestMapping("/work-hall")
	public String defualt() {
		return pageFiles+"work-hall";
	}
	
}
