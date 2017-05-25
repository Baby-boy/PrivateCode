package com.yd.dby.b.shop.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.b.shop.service.YdShopServicePay;

@Controller
@RequestMapping(value = "")
public class YdShopControllerPayReturn {
	
	@Autowired
	private YdShopServicePay ydShopServicePay;
	
	
	/**
	 * 异步通知
	 * @throws IOException 
	 */
	@RequestMapping(value = "notify", method = RequestMethod.POST)
	@ResponseBody
	public Object payNotify(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		return ydShopServicePay.payNotify(request, response);
	}
	
}
