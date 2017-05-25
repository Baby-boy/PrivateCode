package com.yd.dby.b.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.YdMain;
import com.yd.dby.b.shop.service.YdShopServiceComment;

@Controller
@RequestMapping(value = "/comment")
public class YdShopControllerComment {
	
	 @Autowired
	 private YdShopServiceComment ydShopServiceComment;
	 
	
	 /**
	  * 获取商品评价列表
	  * @param request
	  * @return
	  */
	 @RequestMapping(value = "lists")
	 public Object lists(@RequestBody Map<String, HashMap<String, Object>> request) {
		 return YdMain.MAV("_Comment", "lists", request);
	 }
}
