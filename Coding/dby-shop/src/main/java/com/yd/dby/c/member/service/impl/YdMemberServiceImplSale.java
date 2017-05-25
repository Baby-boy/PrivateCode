package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.c.member.mapper.YdMemberMapperSaleUserTop;
import com.yd.dby.c.member.service.YdMemberServiceSale;


@Service("_YdWebServiceMemberSale")
public class YdMemberServiceImplSale implements YdMemberServiceSale {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdMemberMapperSaleUserTop ydWebMapperMemberSaleUserTop;
	
	@Autowired
	private YdMemberMapperOrder ydWebMapperMemberOrder;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", ( Integer.valueOf( request.get("p").toString() ) -1 ) * perPage );
		request.put("perPage", perPage);
	
		request.put("user_id", session.getAttribute("user_id") );

		map.put("data", ydWebMapperMemberSaleUserTop.index(request));
		Integer total = ydWebMapperMemberSaleUserTop.total(request);
		
		map.put("p", request.get("p") );
		
		if ( total % perPage == 0 ) {
			map.put("totalPage", Math.ceil( total / perPage ));
		} else {
			map.put("totalPage", Math.ceil( total / perPage )+1);
		}
		
		return map;
	}
	
	
	@Override
	public Map<String, Object> order(HashMap<String, Object> request) {
		Integer perPage = 10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", ( Integer.valueOf( request.get("p").toString() ) -1 ) * perPage );
		request.put("perPage", perPage);
	
		request.put("user_id", session.getAttribute("user_id") );

		map.put("data", ydWebMapperMemberOrder.orderSale(request));
		Integer total = ydWebMapperMemberOrder.totalSale(request);
		
		map.put("p", request.get("p") );
		
		if ( total % perPage == 0 ) {
			map.put("totalPage", Math.ceil( total / perPage ));
		} else {
			map.put("totalPage", Math.ceil( total / perPage )+1);
		}
		
		return map;
	}
	

}