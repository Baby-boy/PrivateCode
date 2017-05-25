package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperGoods {

	HashMap<String, Object> info(Integer depot_id);

	/**
	 * 确认订单页。商品-及店铺信息
	 */
	List<HashMap<String, Object>> confirmOrderGoods(@Param("depot_id") Integer depot_id);

	/**
	 * 
	 */
	List<HashMap<String, Object>> confirmOrderCart(@Param("ids") List<String> ids);

	/**
	 * 店铺排行榜
	 * 
	 * @param depotids
	 * @return
	 */
	List<Object> shopRankingList(@Param("store_id") Integer store_id);

	/***
	 * 养护首页
	 * 
	 * @param depotids
	 * @return
	 */
	List<Object> maintenanceIndex();

	List<Object> listDepotId(@Param("depotids") List<Integer> depotids);

	List<HashMap<String, Object>> search(HashMap<String, Object> map);

	Integer total(HashMap<String, Object> map);
	
	List<HashMap<String, Object>> activityGoods(HashMap<String, Object> map);
	
	Integer activityGoodsTotal(HashMap<String, Object> map);
	

	List<HashMap<String, Object>> get_many_recommend();

	List<HashMap<String, Object>> get_many_normal(@Param("page_start") Integer page_start, @Param("store_id") Integer store_id);

	List<HashMap<String, Object>> get_many_maintenance(@Param("page_start") Integer page_start, @Param("store_id") Integer store_id);

	List<HashMap<String, Object>> get_many_auction(@Param("page_start") Integer page_start, @Param("store_id") Integer store_id, @Param("activity_id") Integer activity_id);

	List<HashMap<String, Object>> get_many_groupon(@Param("page_start") Integer page_start, @Param("store_id") Integer store_id, @Param("activity_id") Integer activity_id);

	List<HashMap<String, Object>> get_many_seckill(@Param("page_start") Integer page_start, @Param("store_id") Integer store_id, @Param("activity_id") Integer activity_id);

	HashMap<String, Object> get_only_normal(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	HashMap<String, Object> get_only_maintenance(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	HashMap<String, Object> get_only_auction(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	HashMap<String, Object> get_only_groupon(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	HashMap<String, Object> get_only_seckill(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	List<HashMap<String, Object>> get_every_normal(@Param("depot_type") Integer depot_type, @Param("depot_classify3") Integer depot_classify3);

	List<HashMap<String, Object>> get_some_normal(@Param("depot_type") Integer depot_type, @Param("depot_classify3") Integer depot_classify3);

	// @Select("select image, good, general, poor from yd_goods_comments_total
	// where goods_id = #{goods_id}")
	// HashMap<String, Object> get_comments_total(@Param("goods_id") Integer
	// goods_id);
	//
	// @Update("update yd_goods set goods_total1 = goods_total1 + #{num} where
	// goods_id = #{goods_id}")
	// Integer update_fav_count(@Param("goods_id") Integer goods_id,
	// @Param("num") Integer num);

	List<HashMap<String, Object>> goodsListByBrand_id(HashMap<String, Object> map);

	
	Integer updateStockMinus(@Param("depotId") Integer depotId, @Param("num") Integer num);

	Integer updatePreStockMinus(@Param("depotId") Integer depotId, @Param("num") Integer num);
	
	Integer updateStockAdd(@Param("depotId") Integer depotId, @Param("num") Integer num);

	Integer updatePreStockAdd(@Param("depotId") Integer depotId, @Param("num") Integer num);


}
