package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterMessage {
	@RequestMapping("/system/xxgl/usermessage")
	public String userMessage() {
		return "system/xxgl/usermessage";
	}
	@RequestMapping("/system/xxgl/adminmessage")
	public String adminMessage() {
		return "system/xxgl/adminmessage";
	}
	
	@RequestMapping("/system/addmessage")
	public String addMessage() {
		return "system/xxgl/messageadd";
	}
}
