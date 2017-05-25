package com.yd.dby.b.shop.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yd.dby.b.shop.service.YdShopServiceGoods;

@Controller
@RequestMapping(value = "")
public class YdShopControllerSearch {

	@Autowired
	private YdShopServiceGoods ydShopServiceGoods;

	/**
	 * @Title: search
	 * @Description: TODO(搜索)
	 * @author lgl1012dream@foxmail.com
	 * @throws
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public Object search(
			HttpServletRequest _request,
			Model model,
			Map<String, Object> data,
			@RequestParam(value = "gname", required = false, defaultValue = "") String gname,
			@RequestParam(value = "depot_classify1", required = false, defaultValue = "") Integer depot_classify1,
			@RequestParam(value = "depot_classify2", required = false, defaultValue = "") Integer depot_classify2,
			@RequestParam(value = "depot_classify3", required = false, defaultValue = "") Integer depot_classify3,
			@RequestParam(value = "brand_id", required = false, defaultValue = "") Integer brand_id,
			@RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "priceStart", required = false, defaultValue = "") String priceStart,
			@RequestParam(value = "priceEnd", required = false, defaultValue = "") String priceEnd,
			Integer p) {

		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("goodsName", gname);
		whereMap.put("p", p);
		whereMap.put("depot_classify1", depot_classify1);
		whereMap.put("depot_classify2", depot_classify2);
		whereMap.put("depot_classify3", depot_classify3);
		whereMap.put("brand_id", brand_id);
		whereMap.put("orderBy", orderBy);
		whereMap.put("priceStart", priceStart);
		whereMap.put("priceEnd", priceEnd);
		
		
		data.put("dataWhere", ydShopServiceGoods.searchWhere(depot_classify1, depot_classify2, depot_classify3));
		data.put("data", ydShopServiceGoods.search(whereMap));
		model.addAttribute("goodsName", gname);
		model.addAttribute("where", _request.getQueryString());
		model.addAttribute("depot_classify3", depot_classify3);
		model.addAttribute("brand_id", brand_id);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("priceStart", priceStart);
		model.addAttribute("priceEnd", priceEnd);
		model.addAttribute("dataSearchWhere", getUrl(_request));
		return "shop/search/goods";
	}

	/**
	 * 处理url
	 * 
	 * @param _request
	 * @return
	 */
	private Object getUrl(HttpServletRequest _request) {
		Enumeration enu = _request.getParameterNames();

		HashMap<String, Object> whereAll = new HashMap<String, Object>();
		HashMap<String, Object> classifyHashMap = new HashMap<String, Object>();;
		HashMap<String, Object> brandHashMap = new HashMap<String, Object>();
		HashMap<String, Object> orderByHashMap = new HashMap<String, Object>();
		HashMap<String, Object> priceHashMap = new HashMap<String, Object>();
		
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			
			whereAll.put(paraName, _request.getParameter(paraName));
			classifyHashMap.put(paraName, _request.getParameter(paraName));
			brandHashMap.put(paraName, _request.getParameter(paraName));
			orderByHashMap.put(paraName, _request.getParameter(paraName));
			priceHashMap.put(paraName, _request.getParameter(paraName));
		}

		HashMap<String, Object> returnMap = new HashMap<String, Object>();

		classifyHashMap.remove("depot_classify3");
		classifyHashMap.remove("p");
		brandHashMap.remove("brand_id");
		brandHashMap.remove("p");
		orderByHashMap.remove("orderBy");
		orderByHashMap.remove("p");
		priceHashMap.remove("priceEnd");
		priceHashMap.remove("priceStart");
		priceHashMap.remove("p");
		whereAll.remove("p");

		returnMap.put("whereAll", hashMapToString(whereAll));
		returnMap.put("classify", hashMapToString(classifyHashMap));
		returnMap.put("brand", hashMapToString(brandHashMap));
		returnMap.put("orderBy", hashMapToString(orderByHashMap));
		returnMap.put("price", hashMapToString(priceHashMap));
		return returnMap;
	}

	
	private String hashMapToString(HashMap<String, Object> map) {
		Set set = map.entrySet();
		Iterator it = set.iterator();
		String string = "";
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			string += me.getKey() + "=" + me.getValue() + "&";
		}
		return string;
	}
}
