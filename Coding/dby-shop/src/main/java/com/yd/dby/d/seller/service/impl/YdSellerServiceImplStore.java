package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperStore;
import com.yd.dby.d.seller.service.YdSellerServiceStore;


@Service("_YdSellerServiceStore")
public class YdSellerServiceImplStore implements YdSellerServiceStore {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperStore ydWebMapperSellerStore;

	@Override
	public Map<String, Object> setting(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydWebMapperSellerStore.info( Integer.parseInt( session.getAttribute("store_id").toString() ) ));
		return map;
	}
	
	/**
	 * 店铺信息更新
	 */
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		ydWebMapperSellerStore.update(request);
		return setting(request);
	}
	
	/**
	 * 店铺banner更新
	 */
	@Override
	public Map<String, Object> slideUpdate(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydWebMapperSellerStore.slideUpdate(request));
		return map;
	}
	
}