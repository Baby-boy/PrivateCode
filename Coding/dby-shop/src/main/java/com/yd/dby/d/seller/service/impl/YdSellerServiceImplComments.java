package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperComments;
import com.yd.dby.d.seller.service.YdSellerServiceComments;


@Service("_YdSellerServiceComments")
public class YdSellerServiceImplComments implements YdSellerServiceComments {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperComments ydWebMapperComments;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydWebMapperComments.index(request));
		return map;
	}

}