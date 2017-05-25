package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterServer {
	@RequestMapping("/system/yhgl/fuwushangupdate")
	public String serverLabel() {
		return "system/yhgl/fuwushangupdate";
	}
	
	
	@RequestMapping("/system/yhgl/fuwushangcompanyupdate")
	public String serverCompany() {
		return "system/yhgl/fuwushangcompanyupdate";
	}
	
	@RequestMapping("/system/yhgl/fuwushangdetails")
	public String detail() {
		return "system/yhgl/fuwushangdetails";
	}
}
