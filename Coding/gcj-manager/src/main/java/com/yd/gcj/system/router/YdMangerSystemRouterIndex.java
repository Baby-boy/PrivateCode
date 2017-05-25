package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterIndex {
	
	@RequestMapping("/system")
	public String defualt() {
		return "system/index";
	}
	
	@RequestMapping("/system/index")
	public String index() {
		return "system/index";
	}
	
}
