package com.yd.dby.b.shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.yd.dby.b.shop.mapper.YdShopMapperOrder;
import com.yd.dby.b.shop.service.YdShopServiceOrder;

@Service("_Order")
public class YdShopServiceImplOrder implements YdShopServiceOrder {

	@Autowired
	YdShopMapperOrder ydShopMapperOrder;

	// 立即支付
	public Object Created(@RequestBody HashMap<String, Object> request) throws Exception {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		HashMap<String, Object> xx = new HashMap<String, Object>();
		xx.put("code", 200);
		xx.put("data", ids);
		xx.put("msg", "");
		return xx;

	}
/*
	// 立即支付
	public Object PayNow(@RequestBody HashMap<String, Object> request) throws Exception {

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add((int) (System.currentTimeMillis() / 1000));
		HashMap<String, Object> xx = new HashMap<String, Object>();
		xx.put("xx", YdUtilPingpp
				.YdCharge(YdUtilPingppChannel.ALIPAY_PC_DIRECT, Long.valueOf(System.currentTimeMillis()).toString(), 1)
				.getCharge());

		return xx;

	}

	
	// 支付-购物车选中
	public Object PayCartChooses(@RequestBody HashMap<String, Object> request) throws Exception {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add((int) (System.currentTimeMillis() / 1000));
		YdUtilPingppResult r = YdUtilPingpp.YdCharge(YdUtilPingppChannel.ALIPAY_PC_DIRECT,
				Long.valueOf(System.currentTimeMillis()).toString(), 111);
		return r.getCharge();
	}

	// 支付-未付款订单
	public Object PayNoPay(@RequestBody HashMap<String, Object> request) throws Exception {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add((int) (System.currentTimeMillis() / 1000));
		YdUtilPingppResult r = YdUtilPingpp.YdCharge(YdUtilPingppChannel.ALIPAY_PC_DIRECT,
				Long.valueOf(System.currentTimeMillis()).toString(), 111);
		return r.getCharge();
	}
*/
	public Object SelcetGoods(HashMap<String, Object> request) throws Exception {

		return ydShopMapperOrder.SelectGoods(null);

	}

	public Object SelectCoupon(HashMap<String, Object> request) throws Exception {

		return ydShopMapperOrder.SelectCoupon(1, null);
	}
}
