package com.yd.dby.b.shop.service;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface YdShopServicePay {
	
	Object generateRedisOrder(String ids, String type) throws Exception;
	
	Object orderConfirm(String spm, HttpServletResponse response) throws Exception;
	
	Object updateOrderConfirm(String token, String uu, String type, String value);
	
	Object generateOrder(String token, String uu, String key, String addressId, String message, String invoice);
	
	Object generateCtcOrder(String token, String key, String addressId, String message, Integer ctc_id);
	
	Object paymentSelect(String uu, ServletResponse __response) throws IOException;
	
	Object paymentCtcSelect(String uu, ServletResponse __response) throws IOException;
	
	Object payment(String key, String type) throws Exception;
	
	Object paymentCtc(String key, String type) throws Exception;
	
	Object paymentSuccess();
	
	Object payNotify(HttpServletRequest request, HttpServletResponse response) throws IOException;

	Object orderPay(Integer orderId);

}