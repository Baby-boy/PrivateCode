package com.yd.dby.b.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.b.shop.mapper.YdShopMapperAds;
import com.yd.dby.b.shop.mapper.YdShopMapperClassify;
import com.yd.dby.b.shop.service.YdShopServiceAds;
import com.yd.dby.b.shop.service.YdShopServiceClassify;

@Controller
@Order(4)
@RequestMapping(value = "/order")
public class YdShopControllerOrder {
	@Autowired
	private HttpServletRequest ydRequest;

	@Autowired
	private YdShopMapperClassify ydShopMapperClassify;

	@Autowired
	private YdShopServiceClassify ydShopServiceClassify;

	@Autowired
	private YdShopServiceAds ydShopServiceAds;

	@Autowired
	private YdShopMapperAds ydShopMapperAds;


	// springboot restful
	// 首页
	@RequestMapping(value = "/ck4", method = RequestMethod.GET)
	public String ck4() {
		return "ck4";
	}

/*	
	@RequestMapping(value = "/pingpp", method = RequestMethod.GET)
	public Object pingpp() {
		return "pingpp";
	}

	@ResponseBody
	@RequestMapping(value = "/pingpp2", method = RequestMethod.GET)
	public Object pingpp2() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add((int) (System.currentTimeMillis() / 1000));
		YdUtilPingppResult r;
		try {
			r = YdUtilPingpp.YdCharge(YdUtilPingppChannel.ALIPAY_PC_DIRECT,
					Long.valueOf(System.currentTimeMillis()).toString(), 111);
			return r.getCharge();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	@RequestMapping(value = "/buynow/{id}", method = RequestMethod.GET)
	public Object index() {
		return "shop/index/index";
	}

	@RequestMapping(value = "/Created", method = { RequestMethod.POST, RequestMethod.GET })
	// @ApiOperation(value = "立即支付", notes = "立即支付", code = 200)
	public ModelAndView Created(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Order", "Created", request);
	}

	
	@RequestMapping(value = "/PayNow", method = { RequestMethod.POST, RequestMethod.GET })
	// @ApiOperation(value = "立即支付", notes = "立即支付", code = 200)
	public ModelAndView PayNow(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Order", "PayNow", request);
	}

	@RequestMapping(value = "/PayCartChooses", method = RequestMethod.POST)
	// @ApiOperation(value = "立即支付", notes = "立即支付", code = 200)
	public ModelAndView PayCartChooses(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Order", "PayCartChooses", request);
	}

	@RequestMapping(value = "/PayNoPay", method = RequestMethod.POST)
	// @ApiOperation(value = "立即支付", notes = "立即支付", code = 200)
	public Object PayNoPay(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_Order", "PayNoPay", request);
	}

}
