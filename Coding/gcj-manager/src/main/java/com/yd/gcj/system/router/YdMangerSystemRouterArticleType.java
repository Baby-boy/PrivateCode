package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterArticleType {
	@RequestMapping("/system/addArticleType")
	public String addArticleClass() {
		return "system/articlemanger/articletypeadd";
	}
	
}
