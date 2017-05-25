package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperReceiveCoupon;
import com.yd.dby.c.member.service.YdMemberServiceReceiveCoupon;


@Service("_YdWebServiceMemberReceiveCoupon")
public class YdMemberServiceImplReceiveCoupon implements YdMemberServiceReceiveCoupon {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdMemberMapperReceiveCoupon ydWebMapperMemberReceiveCoupon;
	
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", ( Integer.valueOf( request.get("p").toString() ) -1 ) * perPage );
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id") );

		map.put("data", ydWebMapperMemberReceiveCoupon.index(request));
		Integer total = ydWebMapperMemberReceiveCoupon.total(request);
				
		map.put("p", request.get("p") );
		
		if ( total % perPage == 0 ) {
			map.put("totalPage", Math.ceil( total / perPage ));
		} else {
			map.put("totalPage", Math.ceil( total / perPage )+1);
		}
		
		return map;
	}
}