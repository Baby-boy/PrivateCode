package com.yd.dby.b.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.YdMain;
import com.yd.dby.b.shop.service.YdShopServiceStore;

@Controller
@RequestMapping(value = "store")
public class YdShopControllerStore {

	@Autowired
	private YdShopServiceStore ydShopServiceStore;

	// 店铺首页
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Object index(Model model, HashMap<String, Object> map, @PathVariable Integer id, Integer p, String goods_name, Float start_price, Float end_price, Integer class_id) {
		map.put("store_id", id);
		map.put("goods_name", goods_name);
		map.put("start_price", start_price);
		map.put("end_price", end_price);
		map.put("class_id", class_id);
		map.put("p", p);
		model.addAttribute("dataStore", ydShopServiceStore.info(id));
		model.addAttribute("data", ydShopServiceStore.goodsAll(map));
		return "shop/store/index";
	}

	// 店铺商品
	@RequestMapping(value = "goods", method = RequestMethod.POST)
	public Object goodsAll(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Store", "goodsAll", request);
	}

	// 首页商品
	@RequestMapping(value = "info", method = RequestMethod.POST)
	public Object info(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Store", "info", request);
	}

	// 品牌店列表
	@RequestMapping(value = "brand", method = RequestMethod.POST)
	public Object brand(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Store", "list", request);
	}

	/**
	 * 附近店铺
	 */
	@RequestMapping(value = "nearby", method = RequestMethod.POST)
	@ResponseBody
	public Object nearby(float lat, float lng, Integer p) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lat", lat);
		map.put("lng", lng);
		return ydShopServiceStore.nearbyStore(map);
	}

}
