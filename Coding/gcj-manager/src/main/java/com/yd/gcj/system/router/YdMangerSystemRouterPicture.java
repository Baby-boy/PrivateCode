package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterPicture {
	@RequestMapping("/system/tpgl/picture")
	public String picture() {
		return "system/tpgl/picture";
	}
	
	@RequestMapping("/system/addpicture")
	public String addPicture() {
		return "system/tpgl/pictureadd";
	}
}
