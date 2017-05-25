package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopServiceGoods {
	// 搜索列表
	Object search(HashMap<String, Object> map);
	
	// 活动商品
	Object activityGoods(HashMap<String, Object> map);
	
	// 搜索条件
	Object searchWhere(Integer depot_classify1, Integer depot_classify2, Integer depot_classify3);
	
	// 商品信息
	Object info(@Param("depot_id") Integer depot_id);
	
	
	List<HashMap<String, Object>> get_many_recommend();

	List<HashMap<String, Object>> get_many_normal(Integer page_index, Integer store_id);

	List<HashMap<String, Object>> get_many_maintenance(Integer page_index, Integer store_id);

	List<HashMap<String, Object>> get_many_auction(Integer page_index, Integer store_id, Integer activity_id);

	List<HashMap<String, Object>> get_many_groupon(Integer page_index, Integer store_id, Integer activity_id);

	List<HashMap<String, Object>> get_many_seckill(Integer page_index, Integer store_id, Integer activity_id);

	HashMap<String, Object> get_only_normal(Integer user_id, Integer depot_id);

	HashMap<String, Object> get_only_maintenance(Integer user_id, Integer depot_id);

	HashMap<String, Object> get_only_auction(Integer user_id, Integer depot_id);

	HashMap<String, Object> get_only_groupon(Integer user_id, Integer depot_id);

	HashMap<String, Object> get_only_seckill(Integer user_id, Integer depot_id);
	
	
	/**
	 * 店铺排行榜
	 */
	Object shopRankingList(Integer store_id);
	
	
	/**
	 * 养护首页
	 */
	Object maintenanceIndex();
	
	
	/**
	 * @Title: get_every_normal 
	 * @Description: TODO(相关商品查询) [暂时没有分页,取前5条]
	 * @author lgl1012dream@foxmail.com
	 * @throws
	 */
	List<HashMap<String, Object>> get_every_normal(Integer depot_type, Integer depot_classify3);
	
	
	/**
	 * @Title: get_some_normal 
	 * @Description: TODO(相关商品查询) [暂时没有分页,取前5条]
	 * @author lgl1012dream@foxmail.com
	 * @throws
	 */
	List<HashMap<String, Object>> get_some_normal(Integer depot_type, Integer depot_classify3);

}
