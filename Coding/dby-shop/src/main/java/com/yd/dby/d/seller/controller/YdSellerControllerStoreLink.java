package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.d.seller.mapper.YdSellerMapperStoreLink;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月15日 上午10:05:38
 * 
 */
@Controller
@RequestMapping(value = "/api/v1/get/seller/link")
public class YdSellerControllerStoreLink {

	@Autowired
	private YdSellerMapperStoreLink ydSellerMapperLinkStore;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdServiceLinkStore", "index", request);
	}

	@RequestMapping("create")
	public String navCreate(Model model) {
		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id"));
		List<HashMap<String, Object>> stores = ydSellerMapperLinkStore.stores(request);
		if (stores != null && stores.size() >= 10) {
			model.addAttribute("data", stores);
			return "seller/temp/link/link";
		}
		return "seller/temp/link/link_create";
	}

	@RequestMapping(value = "store")
	public ModelAndView store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdServiceLinkStore", "store", request);
	}

	@RequestMapping(value = "edit")
	public ModelAndView edit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdServiceLinkStore", "edit", request);
	}

	@RequestMapping(value = "update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdServiceLinkStore", "update", request);
	}

	@RequestMapping(value = "destroy")
	public ModelAndView destroy(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdServiceLinkStore", "destroy", request);
	}
}
