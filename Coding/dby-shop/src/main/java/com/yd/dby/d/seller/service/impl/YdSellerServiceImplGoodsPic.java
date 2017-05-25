package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperGoodsPic;
import com.yd.dby.d.seller.service.YdSellerServiceGoodsPic;


@Service("_YdSellerServiceGoodsPic")
public class YdSellerServiceImplGoodsPic implements YdSellerServiceGoodsPic {

	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperGoodsPic ydWebMapperSellerGoodsPic;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 15;
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", ( Integer.valueOf( request.get("p").toString() ) -1 ) * perPage );
		request.put("perPage", perPage);
		request.put("store_id", session.getAttribute("store_id") );
	
		Integer total = ydWebMapperSellerGoodsPic.total(request);
		
		map.put("data", ydWebMapperSellerGoodsPic.index(request));
		map.put("p", request.get("p") );
		
		if ( total % perPage == 0 ) {
			map.put("totalPage", Math.ceil( total / perPage ));
		} else {
			map.put("totalPage", Math.ceil( total / perPage )+1);
		}
		
		return map;
	}

}