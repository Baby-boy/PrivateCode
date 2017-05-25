package com.yd.dby.d.seller.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperStoreAddress;
import com.yd.dby.d.seller.service.YdSellerServiceStoreAddress;


@Service("_YdSellerServiceStoreAddress")
public class YdSellerServiceImplStoreAddress implements YdSellerServiceStoreAddress {

	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperStoreAddress ydWebMapperSellerStoreAddress;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydWebMapperSellerStoreAddress.index(request));
		return map;
	}
	
	@Override
	public Map<String, Object> store(HashMap<String, Object> request) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		request.put("store_id", session.getAttribute("store_id") );
		request.put("created_at", df.format(new Date()) );
		request.put("data", ydWebMapperSellerStoreAddress.store(request));
		return index(request);
	}
	
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("data", ydWebMapperSellerStoreAddress.update(request));
		return index(request);
	}
	
	@Override
	public Map<String, Object> setDefault(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("data", ydWebMapperSellerStoreAddress.setDefault(request));
		return index(request);
	}
	
	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		ydWebMapperSellerStoreAddress.destroy(request);
		return index(request);
	}
}