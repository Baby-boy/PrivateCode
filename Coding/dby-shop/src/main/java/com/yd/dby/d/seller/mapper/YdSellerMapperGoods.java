package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.d.seller.entity.YdSellerDepot;
import com.yd.dby.d.seller.entity.YdSellerGoods;


public interface YdSellerMapperGoods {
	
	List<Object> index(HashMap<String, Object> request);
	
	Integer total(HashMap<String, Object> request);
	
	// 删除商品
	Integer destroy(HashMap<String, Object> request);

	// 创建商品
	List<Object> create(HashMap<String, Object> request);
	
	// 创建商品-保存
	Integer storeGoods(YdSellerGoods goods);
	
	// 创建商品-保存
	Integer storeDepot(YdSellerDepot depot);
	
	// 更新商品-保存
	Integer updateGoods(HashMap<String, Object> request);
	
	// 更新商品-保存
	Integer updateDepot(HashMap<String, Object> request);
	
	// 更新商品活动信息
	Integer updateDepotActivity(YdSellerDepot ydSellerDepot);
	
	// 获取单条商品信息-连 depot 表
	Object find(HashMap<String, Object> request);
	
	
	/**
	 * 商品上下架
	 */
	Integer showhide(@Param("depot_id") Integer depot_id, @Param("up")Integer up);
	
}