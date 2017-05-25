package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperBrand;
import com.yd.dby.b.shop.mapper.YdShopMapperClassify;
import com.yd.dby.b.shop.mapper.YdShopMapperGoods;
import com.yd.dby.b.shop.service.YdShopServiceGoods;

@Service("_YdShopServiceGoods")
public class YdShopServiceImplGoods implements YdShopServiceGoods {

	@Autowired
	private YdShopMapperGoods ydShopMapperGoods;
	
	@Autowired
	private YdShopMapperClassify ydShopMapperClassify;
	
	@Autowired
	private YdShopMapperBrand ydShopMapperBrand;

	private void String2ListMap(HashMap<String, Object> obj) {
		String picsString = obj.get("goods_pics").toString();
		obj.put("goods_pics", picsString.split(","));
	}
	
	
	/**
	 * 商品信息
	 * @param depot_id
	 * @return
	 */
	public Object info(Integer depot_id) {
		return ydShopMapperGoods.info(depot_id);
	}
	
	
	/**
	 * 搜索列表页
	 * @param map
	 * @return
	 */
	public Object search(HashMap<String, Object> map) {
		
		if ( map.get("p") == null || map.get("p").equals(0) ) {
			map.put("p", 1);
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		map.put("to", ( Integer.valueOf( map.get("p").toString() ) - 1 ) * 40);
		map.put("perPage", 40);

		Integer total = ydShopMapperGoods.total(map);

		hashMap.put("total", total);
		hashMap.put("data", ydShopMapperGoods.search(map));
		
		hashMap.put("p", map.get("p"));
		if (total % 40 == 0) {
			hashMap.put("totalPage", Math.ceil(total / 40));
		} else {
			hashMap.put("totalPage", Math.ceil(total / 40) + 1);
		}

		return hashMap;
	}
	
	
	/**
	 * 活动商品
	 * @param map
	 * @return
	 */
	public Object activityGoods(HashMap<String, Object> map) {
		
		if ( map.get("p") == null || map.get("p").equals(0) ) {
			map.put("p", 1);
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		map.put("to", ( Integer.valueOf( map.get("p").toString() ) - 1 ) * 40);
		map.put("perPage", 40);

		Integer total = ydShopMapperGoods.activityGoodsTotal(map);

		hashMap.put("total", total);
		hashMap.put("data", ydShopMapperGoods.activityGoods(map));
		
		hashMap.put("p", map.get("p"));
		if (total % 40 == 0) {
			hashMap.put("totalPage", Math.ceil(total / 40));
		} else {
			hashMap.put("totalPage", Math.ceil(total / 40) + 1);
		}

		return hashMap;
	}
	
	
	
	/**
	 * 搜索条件
	 */
	@Override
	public Object searchWhere(Integer depot_classify1, Integer depot_classify2, Integer depot_classify3) {
		HashMap<String, Object> classMap = new HashMap<String, Object>();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("classifyKey", "depot_classify3");
		
		if ( depot_classify3 != null ) {
			classMap = ydShopMapperClassify.info(depot_classify3);
			if (classMap != null) {
				dataMap.put("dataClass", ydShopMapperClassify.son( (Integer)classMap.get("cly_pid") ));
				dataMap.put("dataBrand", ydShopMapperBrand.getTypeBrand((Integer)classMap.get("type_id")));
			} else {
				dataMap.put("dataClass", null);
				dataMap.put("dataBrand", null);
			}
		} else if ( depot_classify2 != null ) {
			dataMap.put("dataClass", ydShopMapperClassify.son( depot_classify2 ));
			dataMap.put("dataBrand", null);
			
		} else if ( depot_classify1 != null ) {
			dataMap.put("dataClass", ydShopMapperClassify.son( depot_classify1 ));
			dataMap.put("dataBrand", null);
			dataMap.put("classifyKey", "depot_classify2");
		} else {
			dataMap.put("dataClass", null);
			dataMap.put("dataBrand", null);
		}
		
		return dataMap;
	}
	
	
	/**
	 * 店铺排行榜
	 */
	@Override
	public Object shopRankingList(Integer store_id) {
		return ydShopMapperGoods.shopRankingList(store_id);
	}
	
	
	/**
	 * 养护首页
	 */
	@Override
	public Object maintenanceIndex() {
		return ydShopMapperGoods.maintenanceIndex();
	}
	

	@Override
	public List<HashMap<String, Object>> get_many_recommend() {
		return ydShopMapperGoods.get_many_recommend();
	}

	@Override
	public List<HashMap<String, Object>> get_many_normal(Integer page_index, Integer store_id) {
		return ydShopMapperGoods.get_many_normal((page_index - 1) * 15, store_id);
	}

	@Override
	public List<HashMap<String, Object>> get_many_maintenance(Integer page_index, Integer store_id) {
		return ydShopMapperGoods.get_many_maintenance((page_index - 1) * 15, store_id);
	}

	@Override
	public List<HashMap<String, Object>> get_many_auction(Integer page_index, Integer store_id, Integer activity_id) {
		return ydShopMapperGoods.get_many_auction((page_index - 1) * 15, store_id, activity_id);
	}

	@Override
	public List<HashMap<String, Object>> get_many_groupon(Integer page_index, Integer store_id, Integer activity_id) {
		return ydShopMapperGoods.get_many_groupon((page_index - 1) * 15, store_id, activity_id);
	}

	@Override
	public List<HashMap<String, Object>> get_many_seckill(Integer page_index, Integer store_id, Integer activity_id) {
		return ydShopMapperGoods.get_many_seckill((page_index - 1) * 15, store_id, activity_id);
	}

	@Override
	public HashMap<String, Object> get_only_normal(Integer user_id, Integer depot_id) {
		HashMap<String, Object> obj = ydShopMapperGoods.get_only_normal(user_id, depot_id);
		String2ListMap(obj);
		return obj;
	}

	@Override
	public HashMap<String, Object> get_only_maintenance(Integer user_id, Integer depot_id) {
		HashMap<String, Object> obj = ydShopMapperGoods.get_only_maintenance(user_id, depot_id);
		String2ListMap(obj);
		return obj;
	}

	@Override
	public HashMap<String, Object> get_only_auction(Integer user_id, Integer depot_id) {
		HashMap<String, Object> obj = ydShopMapperGoods.get_only_auction(user_id, depot_id);
		String2ListMap(obj);
		return obj;
	}

	@Override
	public HashMap<String, Object> get_only_groupon(Integer user_id, Integer depot_id) {
		HashMap<String, Object> obj = ydShopMapperGoods.get_only_groupon(user_id, depot_id);
		String2ListMap(obj);
		return obj;
	}

	@Override
	public HashMap<String, Object> get_only_seckill(Integer user_id, Integer depot_id) {
		HashMap<String, Object> obj = ydShopMapperGoods.get_only_seckill(user_id, depot_id);
		String2ListMap(obj);
		return obj;
	}


	@Override
	public List<HashMap<String, Object>> get_every_normal(Integer depot_type, Integer depot_classify3) {
		return ydShopMapperGoods.get_every_normal(depot_type, depot_classify3);
	}


	@Override
	public List<HashMap<String, Object>> get_some_normal(Integer depot_type,
			Integer depot_classify3) {
		// TODO Auto-generated method stub
		return ydShopMapperGoods.get_some_normal(depot_type, depot_classify3);
	}

	

}
