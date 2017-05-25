package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

public interface YdShopServiceMessage {

	List<HashMap<String, Object>> get_many(Integer msg_type, Integer msg_fid, Integer page_index);

	List<HashMap<String, Object>> get_latest(Integer msg_type, Integer msg_fid, Integer limit);
}
