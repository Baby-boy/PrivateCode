package com.yd.dby.b.shop.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yd.dby.b.shop.service.YdShopServiceGoods;
import com.yd.dby.b.shop.service.YdShopServiceSeckill;


@Controller
@RequestMapping(value = "seckill")
public class YdShopControllerSeckill {
	
	@Autowired
	private YdShopServiceSeckill ydShopServiceSeckill;
	
	@Autowired
	private YdShopServiceGoods ydShopServiceGoods;
	
	// 秒杀主页
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object seckill(Model model) {
		model.addAttribute("dataSeckill", ydShopServiceSeckill.lists());
		return "shop/index/seckill";
	}
	
	
	// 秒杀商品列表
	@RequestMapping(value = "{seckillId}", method = RequestMethod.GET)
	public Object seckillList(Model model, @PathVariable("seckillId") Integer seckillId, Integer p) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("p", p);
		map.put("depotType", 5);
		map.put("activityId", seckillId);
		model.addAttribute("dataSeckill", ydShopServiceSeckill.info(seckillId));
		
		model.addAttribute("dataActivityGoods", ydShopServiceGoods.activityGoods(map) );
		return "shop/seckill/seckillList";
	}
}
