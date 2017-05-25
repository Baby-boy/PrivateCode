package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperAuction;
import com.yd.dby.b.shop.service.YdShopServiceAuction;

@Service("_Auction")
public class YdShopServiceImplAuction implements YdShopServiceAuction {
	@Autowired
	private YdShopMapperAuction ydShopMapperAuction;

	@Override
	public Object select_history_bid(HashMap<String, Object> request) {
		return ydShopMapperAuction.select_history_bid(1);
	}

	@Override
	public Object count_history_bid(HashMap<String, Object> request) {
		return ydShopMapperAuction.count_history_bid(1);
	}

	@Override
	public Object select_latest_bid(HashMap<String, Object> request) {
		return ydShopMapperAuction.select_latest_bid(1);
	}

	@Override
	public Object check_is_bail(HashMap<String, Object> request) {
		return ydShopMapperAuction.check_is_bail(1, 1);
	}

	@Override
	public Object insert_bail(HashMap<String, Object> request) {
		return ydShopMapperAuction.insert_bail(1, 1, 1F, "");
	}

	@Override
	public Object insert_bid(HashMap<String, Object> request) {
		return ydShopMapperAuction.insert_bid(1, 1, 1F, "");
	}

	@Override
	public Object update_bid(HashMap<String, Object> request) {
		return ydShopMapperAuction.update_bid(1, 1, 1F);
	}

}