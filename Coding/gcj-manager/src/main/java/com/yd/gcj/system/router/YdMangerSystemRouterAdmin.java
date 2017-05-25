package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterAdmin {
	@RequestMapping("/system/addadmin")
	public String addAdmin() {
		return "system/gly/adminadd";
	}
	
	@RequestMapping("/system/gly/admin")
	public String admin() {
		return "system/gly/admin";
	}
	@RequestMapping("/system/adminupdate")
	public String upAdmin() {
		return "system/gly/adminupdate";
	}
	//修改密码
	@RequestMapping("/system/changePass")
	public String changePass() {
		return "system/gly/changepassword";
	}
}
