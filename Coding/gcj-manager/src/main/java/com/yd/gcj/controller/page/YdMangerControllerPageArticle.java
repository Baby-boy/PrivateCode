package com.yd.gcj.controller.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.tool.MapInitFactory;

@RequestMapping("/page/article")
public class YdMangerControllerPageArticle {
	
	@RequestMapping("/set")
	public Object set(Integer type,Integer pageIndexNum,HttpServletRequest request){
		request.getSession().setAttribute("articleType", type);
		return new MapInitFactory("200","ok").getMap();
	}
	
}
