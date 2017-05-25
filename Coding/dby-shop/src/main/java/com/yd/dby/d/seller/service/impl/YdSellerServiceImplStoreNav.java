package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperStoreNav;
import com.yd.dby.d.seller.service.YdSellerServiceStoreNav;


@Service("_YdSellerServiceStoreNav")
public class YdSellerServiceImplStoreNav implements YdSellerServiceStoreNav {

	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperStoreNav ydWebMapperSellerStoreNav;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydWebMapperSellerStoreNav.index(request));
		return map;
	}
	
	@Override
	public Map<String, Object> store(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("data", ydWebMapperSellerStoreNav.store(request));
		return index(request);
	}
	
	@Override
	public Map<String, Object> edit(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydWebMapperSellerStoreNav.edit(request));
		return map;
	}
	
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("data", ydWebMapperSellerStoreNav.update(request));
		return index(request);
	}
	
	
	@Override
	public Map<String, Object> setShow(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("data", ydWebMapperSellerStoreNav.setShow(request));
		return index(request);
	}
	
	
	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("delete", ydWebMapperSellerStoreNav.destroy(request));
		return index(request);
	}

}