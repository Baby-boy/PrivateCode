package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.d.seller.service.YdSellerServiceOrder;

@Controller
@RequestMapping(value = "seller/order")
public class YdSellerControllerOrder {
	
	@Autowired
	private YdSellerServiceOrder ydSellerServiceOrder;


	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceOrder", "index", request);
	}
	
	
	/**
	 * 发货页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delivery")
	public ModelAndView delivery(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceOrder", "delivery", request);
	}
	
	
	/**
	 * 更新收货地址
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update-take")
	@ResponseBody
	public Object updateTake(Integer order_id, String text) {
		return ydSellerServiceOrder.updateTake(order_id, text);
	}
	
	
	/**
	 * 发货
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deliver")
	@ResponseBody
	public Object deliver(Integer order_id, String shipping_express, String logis_code, String shipping_code,
			String deliver_explain, String shipping_address, Integer shipping_id
			) {
		return ydSellerServiceOrder.deliver(order_id, shipping_express, logis_code, shipping_code, deliver_explain, shipping_address, shipping_id);
	}
	
	
	/**
	 * 订单详情
	 */
	@RequestMapping(value = "detail")
	public Object detail(Model model ,Integer id) {
		model.addAttribute("data", ydSellerServiceOrder.info(id));
		model.addAttribute("dataGoods", ydSellerServiceOrder.orderGoods(id));
		return "seller/temp/order/order_detail";
	}
	

}
