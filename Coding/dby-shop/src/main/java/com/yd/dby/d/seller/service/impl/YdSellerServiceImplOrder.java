package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperLogis;
import com.yd.dby.d.seller.mapper.YdSellerMapperOrder;
import com.yd.dby.d.seller.mapper.YdSellerMapperStoreAddress;
import com.yd.dby.d.seller.service.YdSellerServiceOrder;
import com.yd.dby.utils.YdUtilResponse;


@Service("_YdSellerServiceOrder")
public class YdSellerServiceImplOrder implements YdSellerServiceOrder {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperOrder ydSellerMapperOrder;
	
	@Autowired
	private YdSellerMapperLogis ydSellerMapperLogis;
	
	@Autowired
	private YdSellerMapperStoreAddress ydSellerMapperStoreAddress;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		
		Integer perPage = 10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", ( Integer.valueOf( request.get("p").toString() ) -1 ) * perPage );
		request.put("perPage", perPage);
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydSellerMapperOrder.index(request));
		Integer total = ydSellerMapperOrder.total(request);
		
		map.put("p", request.get("p") );
		
		if ( total % perPage == 0 ) {
			map.put("totalPage", Math.ceil( total / perPage ));
		} else {
			map.put("totalPage", Math.ceil( total / perPage )+1);
		}
		
		return map;
	}
	
	
	/**
	 * 发货页面
	 */
	@Override
	public Map<String, Object> delivery(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer store_id = Integer.parseInt( session.getAttribute("store_id").toString() );
		Integer order_id = Integer.parseInt( request.get("order_id").toString() );
		request.put("store_id", store_id);
		map.put("dataLogis", ydSellerMapperLogis.lists());
		map.put("dataAddress", ydSellerMapperStoreAddress.index(request));
		map.put("data", ydSellerMapperOrder.info(store_id, order_id));
		map.put("dataGoods", ydSellerMapperOrder.orderGoods( order_id ));
		return map;
	}
	
	
	/**
	 * 更新发货地址
	 * @param request
	 * @return
	 */
	@Override
	public Object updateTake(Integer order_id, String text) {
		try {
			ydSellerMapperOrder.updateTake(order_id, text, (Integer) session.getAttribute("store_id"));
			return YdUtilResponse.success(null, "更新收货地址成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("更新收货地址失败");
		}
	}
	
	
	/**
	 * 发货
	 * @param request
	 * @return
	 */
	@Override
	public Object deliver(Integer order_id, String shipping_express, String logis_code,
			String shipping_code, String deliver_explain,
			String shipping_address, Integer shipping_id) {
		try {
			ydSellerMapperOrder.deliver(order_id, shipping_express, logis_code, shipping_code, deliver_explain, shipping_address, shipping_id, (Integer) session.getAttribute("store_id"));
			return YdUtilResponse.success(null, "发货成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("发货失败");
		}
	}
	
	
	/**
	 * 订单详情
	 */
	@Override
	public Object info(Integer id) {
		Integer store_id = Integer.parseInt(session.getAttribute("store_id").toString());
		return ydSellerMapperOrder.infos(store_id, id);
	}

	
	/**
	 * 订单商品
	 */
	@Override
	public Object orderGoods(Integer id) {
		return ydSellerMapperOrder.orderGoods(id);
	}
}