package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperAddress;
import com.yd.dby.b.shop.service.YdShopServiceAddress;

@Service("_Address")
public class YdShopServiceImplAddress implements YdShopServiceAddress {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdShopMapperAddress ydShopMapperAddress;

	@Override
	public Object only(HashMap<String, Object> v) {
		return ydShopMapperAddress.only(1, 1);
	}

	@Override
	public Object select(HashMap<String, Object> v) {
		return ydShopMapperAddress.select( Integer.parseInt( session.getAttribute("user_id").toString() ) );
		// return ydShopMapperAddress.select( 50 );
	}

	@Override
	public Object insert(HashMap<String, Object> model) {
		return ydShopMapperAddress.insert(model);
	}

	@Override
	public Object update(HashMap<String, Object> model) {
		return ydShopMapperAddress.insert(model);
	}

	@Override
	public Object default0(HashMap<String, Object> v) {
		return ydShopMapperAddress.default0(1, 1);
	}

	@Override
	public Object delete(HashMap<String, Object> v) {
		return ydShopMapperAddress.delete(1, 1);
	}

}