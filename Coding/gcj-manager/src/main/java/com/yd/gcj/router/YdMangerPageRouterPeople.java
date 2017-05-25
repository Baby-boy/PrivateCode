package com.yd.gcj.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class YdMangerPageRouterPeople<HttpServletRequest> {
	
	private static final String pageFiles = "people/";
	@RequestMapping("/people")
	public String people() {
		return pageFiles+"people";
	}
	
	
}
