package com.yd.dby.b.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.b.shop.entity.YdFavorite;
import com.yd.dby.b.shop.service.YdShopServiceFavorite;

@Controller
@RequestMapping(value = "favorite")
public class YdShopControllerFavorite {

	@Autowired
	private YdShopServiceFavorite ydShopServiceFavorite;
	
	
	@RequestMapping(value = "store")
	@ResponseBody
	public Object store(YdFavorite ydFavorite) {
		return ydShopServiceFavorite.store(ydFavorite);
	}
	
	
	@RequestMapping(value = "destory")
	@ResponseBody
	public Object destory(YdFavorite ydFavorite) {
		return ydShopServiceFavorite.destory(ydFavorite);
	}
}
