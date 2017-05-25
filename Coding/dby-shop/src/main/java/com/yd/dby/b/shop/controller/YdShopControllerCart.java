package com.yd.dby.b.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.YdMain;
import com.yd.dby.b.shop.service.YdShopServiceCart;

@Controller
@RequestMapping(value = "/cart")
public class YdShopControllerCart {
	
	 @Autowired
	 private YdShopServiceCart ydShopServiceCart;
	 
	
	 /**
	  * 购物车列表
	  * @param request
	  * @return
	  */
	 @RequestMapping(value = "", method = RequestMethod.GET)
	 public Object lists(Model model, Integer p) {
		 model.addAttribute("data", ydShopServiceCart.lists(p));
		 return "shop/cart/index";
	 }
	 
	 
	 /**
	  * 加入购物车
	  * @param request
	  * @return
	  */
	 @RequestMapping(value = "store", method = RequestMethod.POST)
	 public Object store(@RequestBody Map<String, HashMap<String, Object>> request) {
		 return YdMain.MAV("_Cart", "store", request);
	 }
	 
	 
	 /**
	  * 更新库存
	  */
	 @RequestMapping(value = "update", method = RequestMethod.POST)
	 public Object update(@RequestBody Map<String, HashMap<String, Object>> request) {
		 return YdMain.MAV("_Cart", "update", request);
	 }
	 
	 
	 
	 /**
	  * 删除
	  */
	 @RequestMapping(value = "delete", method = RequestMethod.POST)
	 public Object delete(@RequestBody Map<String, HashMap<String, Object>> request) {
		 return YdMain.MAV("_Cart", "delete", request);
	 }
	 
	 
	 /**
	  * 购物车窗口
	  */
	 @RequestMapping(value = "window", method = RequestMethod.POST)
	 @ResponseBody
	 public Object window(){
		 return ydShopServiceCart.window();
	 }
	 
}
