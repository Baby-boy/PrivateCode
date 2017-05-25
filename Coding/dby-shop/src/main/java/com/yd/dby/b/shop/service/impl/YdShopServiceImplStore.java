package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperStore;
import com.yd.dby.b.shop.service.YdShopServiceStore;
import com.yd.dby.d.seller.mapper.YdSellerMapperStoreLink;
import com.yd.dby.utils.YdUtilMapBaidu;
import com.yd.dby.utils.YdUtilTree2;

@Service("_Store")
public class YdShopServiceImplStore implements YdShopServiceStore {
	@Autowired
	private YdShopMapperStore ydShopMapperStore;

	@Autowired
	private YdSellerMapperStoreLink ydShopMapperLinkStore;

	@Autowired
	private YdShopMapperGoods ydShopMapperGoods;
	@Autowired
	private HttpSession session;

	@Override
	public List<HashMap<String, Object>> select(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return ydShopMapperStore.select();
	}

	@Override
	public List<HashMap<String, Object>> link(Integer store_id) {
		// TODO Auto-generated method stub
		return ydShopMapperStore.link();
	}

	@Override
	public List<HashMap<String, Object>> classfiy(Integer store_id) {
		// TODO Auto-generated method stub
		return YdUtilTree2.toList(ydShopMapperStore.classfiy());
	}

	@Override
	public List<HashMap<String, Object>> top_sell_goods(Integer store_id) {
		// TODO Auto-generated method stub
		return ydShopMapperStore.top_sell_goods();
	}

	@Override
	public List<HashMap<String, Object>> recommend_goods(Integer store_id) {
		// TODO Auto-generated method stub
		return ydShopMapperStore.recommend_goods();
	}

	/**
	 * 获取店铺商品
	 */
	@Override
	public Object goodsAll(HashMap<String, Object> request) {
		Integer perPage = 16;
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (request.get("p") == null || request.get("p").equals(0)) {
			request.put("p", 1);
		}

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);

		map.put("dataGoods", ydShopMapperStore.goodsAll(request));
		// 查询已知store_id对应的友情店铺
		map.put("dataStores", ydShopMapperLinkStore.stores(request));
		Integer total = ydShopMapperStore.goodsAllTotal(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	/**
	 * 获取店铺信息
	 */
	@Override
	public Object info(Integer store_id) {
		return ydShopMapperStore.info(store_id);
	}

	/**
	 * 获取店铺banner
	 */
	@Override
	public Map<String, Object> banner(Integer store_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydShopMapperStore.banner(store_id));
		return map;
	}

	// 获取店铺列表(每页显示12条)
	@Override
	public Object list(HashMap<String, Object> map) {

		Integer to = 12;

		if (map.get("p") == null || map.get("p").equals(0)) {
			map.put("p", 1);
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		map.put("to", (Integer.valueOf(map.get("p").toString()) - 1) * to);
		map.put("perPage", to);
		Integer total = null;
		hashMap.put("data", ydShopMapperStore.get_many_brand(map));
		total = ydShopMapperStore.total(map);
		hashMap.put("dataGoods", "");
		hashMap.put("p", map.get("p"));
		if (total % to == 0) {
			hashMap.put("totalPage", Math.ceil(total / to));
		} else {
			hashMap.put("totalPage", Math.ceil(total / to) + 1);
		}

		return hashMap;
	}

	/**
	 * 流通
	 */
	@Override
	public Object nearbyStore(HashMap<String, Object> map) {

//		HashMap<String, Double> lngLat = YdUtilMapBaidu.getLngAndLat("上海市宝山区华滋奔腾大厦");

		map.put("latitude", map.get("lat"));
		map.put("longitude", map.get("lng"));

		Integer to = 12;

		if (map.get("p") == null || map.get("p").equals(0)) {
			map.put("p", 1);
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		map.put("to", (Integer.valueOf(map.get("p").toString()) - 1) * to);
		map.put("perPage", to);

		Integer total = ydShopMapperStore.nearbyTotal(map);

		hashMap.put("data", ydShopMapperStore.nearbyStore(map));

		hashMap.put("p", map.get("p"));
		if (total % to == 0) {
			hashMap.put("totalPage", Math.ceil(total / to));
		} else {
			hashMap.put("totalPage", Math.ceil(total / to) + 1);
		}

		return hashMap;
	}
}