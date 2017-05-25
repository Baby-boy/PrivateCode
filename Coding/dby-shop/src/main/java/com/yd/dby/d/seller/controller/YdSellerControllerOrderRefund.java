package com.yd.dby.d.seller.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.d.seller.service.YdSellerServiceOrderRefund;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "seller/refund")
public class YdSellerControllerOrderRefund {
	
	@Autowired
	private YdSellerServiceOrderRefund ydSellerServiceOrderRefund;
	
	
	/**
	 * 售后列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_ydSellerMapperOrderRefund", "index", request);
	}

	
	/**
	 * 处理页面
	 */
	@RequestMapping(value = "handle")
	public ModelAndView handle(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_ydSellerMapperOrderRefund", "handle", request);
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "detail")
	public ModelAndView detail(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_ydSellerMapperOrderRefund", "detail", request);
	}
	
	
	/**
	 * 商家处理退款 - 提交
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Integer refundId, String sellerMessage, Integer sellerState) {
		return ydSellerServiceOrderRefund.update(refundId, sellerMessage, sellerState);
	}
	
	
	/**
	 * 商家处理退款 - 提交
	 * @return
	 */
	@RequestMapping(value = "playMoney")
	@ResponseBody
	public Object playMoney(Integer refundId) {
		return ydSellerServiceOrderRefund.playMoney(refundId);
	}
	
	
	/**
	 * 退款成功
	 * @return
	 */
	@RequestMapping(value = "success")
	@ResponseBody
	public Object success(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		return ydSellerServiceOrderRefund.success(request, response);
	}
}
