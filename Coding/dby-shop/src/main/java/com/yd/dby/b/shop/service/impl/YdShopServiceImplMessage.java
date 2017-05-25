package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperMessage;
import com.yd.dby.b.shop.service.YdShopServiceMessage;

@Service("_YdShopServiceMessage")
public class YdShopServiceImplMessage implements YdShopServiceMessage {

	@Autowired
	private YdShopMapperMessage ydAppMapperMessage;

	@Override
	public List<HashMap<String, Object>> get_many(Integer msg_type, Integer msg_fid, Integer page_index) {
		return ydAppMapperMessage.get_many(msg_type, msg_fid, (page_index - 1) * 15);
	}

	@Override
	public List<HashMap<String, Object>> get_latest(Integer msg_type, Integer msg_fid, Integer limit) {
		return ydAppMapperMessage.get_latest(msg_type, msg_fid, limit);
	}

}
