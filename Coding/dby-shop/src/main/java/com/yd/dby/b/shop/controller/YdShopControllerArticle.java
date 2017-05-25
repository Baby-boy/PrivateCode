package com.yd.dby.b.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yd.dby.b.shop.service.impl.YdShopServiceImplArticle;

@Controller
@RequestMapping(value = "/article")
public class YdShopControllerArticle {
	
	@Autowired
	private YdShopServiceImplArticle ydShopServiceImplArticle;
	
	 // 详情
	 @RequestMapping(value = "{id}", method = RequestMethod.GET)
	 public Object info(Model model, @PathVariable Integer id, Integer ac_id) {
		 model.addAttribute("data", ydShopServiceImplArticle.info(id));
		 model.addAttribute("ac_id", ac_id);
		 return "shop/article/help";
	 }
}
