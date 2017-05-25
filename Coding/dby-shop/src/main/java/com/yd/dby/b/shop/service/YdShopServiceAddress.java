package com.yd.dby.b.shop.service;

import java.util.HashMap;

public interface YdShopServiceAddress {

	Object only(HashMap<String, Object> user_id);

	Object select(HashMap<String, Object> user_id);

	Object insert(HashMap<String, Object> model);

	Object update(HashMap<String, Object> model);

	Object default0(HashMap<String, Object> user_id);

	Object delete(HashMap<String, Object> user_id);

}
