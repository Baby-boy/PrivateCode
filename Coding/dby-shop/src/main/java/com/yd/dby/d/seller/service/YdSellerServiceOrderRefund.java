package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface YdSellerServiceOrderRefund {
	
	/**
	 * 售后列表
	 * @param request
	 * @return
	 */
	Map<String, Object> index(HashMap<String, Object> request);


	/**
	 * 退款-退货-处理页面
	 * @param request
	 * @return
	 */
	Object handle(HashMap<String, Object> request);
	
	
	/**
	 * 退款-退货 - 详情
	 * @param request
	 * @return
	 */
	Object detail(HashMap<String, Object> request);


	/**
	 * 商家处理退款 - 提交
	 * @param refundId
	 * @param sellerMessage
	 * @param sellerState
	 * @return
	 */
	Object update(Integer refundId, String sellerMessage, Integer sellerState);


	/**
	 * 发起退款
	 * @param refundId
	 * @return
	 */
	Object playMoney(Integer refundId);


	/**
	 * 退款成功
	 * @param request
	 * @param response
	 * @return
	 */
	Object success(HttpServletRequest request, HttpServletResponse response);
}