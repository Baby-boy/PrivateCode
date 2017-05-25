package com.yd.dby.b.shop.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.b.shop.service.YdShopServiceCart;
import com.yd.dby.b.shop.service.YdShopServiceGoods;
import com.yd.dby.b.shop.service.YdShopServicePay;
import com.yd.dby.c.member.service.YdMemberServiceCtc;

@Controller
@RequestMapping(value = "/pay")
public class YdShopControllerPay {
	
	@Autowired
	private YdShopServiceCart ydShopServiceCart;
	
	@Autowired
	private YdShopServiceGoods ydShopServiceGoods;
	
	@Autowired
	private YdShopServicePay ydShopServicePay;
	
	@Autowired
	private YdMemberServiceCtc ydMemberServiceCtc;
	
	
	
	/**
	 * 购买提交 - 生成秘钥
	 * String type cart为购物车提交
	 * @throws Exception 
	 */
	@RequestMapping(value = "generate", method = RequestMethod.POST)
	@ResponseBody
	public Object cart(String ids, String type) throws Exception {
		return ydShopServicePay.generateRedisOrder(ids, type);
	}
	
	
	/**
	 * 确认订单页
	 * @param depotid
	 * @param model
	 * @return 订单信息
	 * @throws Exception
	 */
	@RequestMapping(value = "order_confirm", method = RequestMethod.GET)
	public Object orderConfirm(Model model, String spm, HttpServletResponse response) throws Exception {
		model.addAttribute("dataOrder", ydShopServicePay.orderConfirm(spm, response) );
		return "shop/pay/order_confirm";
	}
	
	
	/**
	 * 确认订单页 - 相关操作更新订单信息
	 * @return
	 */
	@RequestMapping(value = "update_order_confirm", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateOrderConfirm(String token, String uu, String type, String value) {
		return ydShopServicePay.updateOrderConfirm(token, uu, type, value);
	}
	
	
	/**
	 * 生成订单
	 * @param token
	 * @param uu
	 * @param key
	 * @param addressId   	地址id
	 * @param message	  	留言信息
	 * @param invoice	 	发票信息
	 * @return
	 */
	@RequestMapping(value = "generate_order", method = RequestMethod.POST)
	@ResponseBody
	public Object generateOrder(String token, String uu, String key, String addressId, String message, String invoice) {
		return ydShopServicePay.generateOrder(token, uu, key, addressId, message, invoice);
	}
	
	
	/**
	 * 用户订单列表 - 发起支付
	 */
	@RequestMapping(value = "order_pay", method = RequestMethod.POST)
	@ResponseBody
	public Object orderPay(Integer orderId) {
		return ydShopServicePay.orderPay(orderId);
	}
	
	
	/**
	 * 跳转订单确认页 - ctc
	 * 
	 * @param ctc_id寄卖商品的id
	 * @param model
	 * @return 方法作用()
	 */
	@RequestMapping(value = "/ctc_order", method = RequestMethod.GET)
	public Object toCtcOderConfirm(Integer ctc_id, Model model) {
		// if (session.getAttribute("user_id") == null) {
		// return YdUtilResponse.fail("请登录再购买");
		// }
		// 获取寄卖商品信息
		HashMap<String, Object> ydMemberCtc = ydMemberServiceCtc.queryOneCtcDetailById(ctc_id);
		model.addAttribute("item", ydMemberCtc);
		return "shop/pay/ctc_order_confirm";
	}
	
	
	/**
	 * 生成订单 - ctc
	 * @param token
	 * @param uu
	 * @param key
	 * @param addressId   	地址id
	 * @param message	  	留言信息
	 * @return
	 */
	@RequestMapping(value = "generate_ctc_order", method = RequestMethod.POST)
	@ResponseBody
	
	public Object generateOrder(String token, String key, String addressId, String message, Integer ctc_id) {
		return ydShopServicePay.generateCtcOrder(token, key, addressId, message, ctc_id);
	}
	
	
	
	
	/**
	 * 选择支付方式页面
	 * @throws IOException 
	 */
	@RequestMapping(value = "ctc_select", method = RequestMethod.GET)
	public Object paymentCtcSelect(Model model, String uu, ServletResponse __response) throws IOException {
		model.addAttribute("data", ydShopServicePay.paymentCtcSelect(uu, __response));
		model.addAttribute("key", uu);
		return "shop/pay/payCtcSelect";
	}
	
	
	/**
	 * 选择支付方式页面
	 * @throws IOException 
	 */
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public Object paymentSelect(Model model, String uu, ServletResponse __response) throws IOException {
		model.addAttribute("data", ydShopServicePay.paymentSelect(uu, __response));
		model.addAttribute("key", uu);
		return "shop/pay/paySelect";
	}
	
	
	
	
	
	/**
	 * 支付 - ctc  - 验证
	 * @throws Exception 
	 */
	@RequestMapping(value = "paymentCtc", method = RequestMethod.POST)
	@ResponseBody
	public Object paymentCtc(String key, String type) throws Exception {
		return ydShopServicePay.paymentCtc(key, type);
	}
	
	
	/**
	 * 支付  - 验证
	 * @throws Exception 
	 */
	@RequestMapping(value = "payment", method = RequestMethod.POST)
	@ResponseBody
	public Object paymentSelect(String key, String type) throws Exception {
		return ydShopServicePay.payment(key, type);
	}
	
	
	/**
	 * 支付成功-返回页面
	 */
	@RequestMapping(value = "success", method = RequestMethod.GET)
	public Object paymentSuccess(Model model){
		model.addAttribute("data", ydShopServicePay.paymentSuccess());
		return "shop/pay/success";
	}
	
}
