package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.d.seller.entity.YdSellerStoreGoodsClassify;


public interface YdSellerMapperStoreGoodsClassify {
	
	// 商家分类列表
	List<Object> index(HashMap<String, Object> request);
	
	// 商家一级分类
	List<Object> one(HashMap<String, Object> request);
	
	
	/**
	 * 单条信息
	 */
	YdSellerStoreGoodsClassify info(@Param("scId") Integer scId);
	
	
	/**
	 * 子分类数量
	 * @param sc_id
	 * @return
	 */
	Integer sonCount(@Param("sc_id") Integer sc_id);
	
	// 添加商家分类
	Integer store(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify);
	
	// 商家分类-修改
	Object edit(HashMap<String, Object> request);
	
	// 设为显示或隐藏
	Integer setShow(HashMap<String, Object> request);
	
	// 修改商家分类
	Integer update(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify);

	// 删除商家分类
	Integer destroy(HashMap<String, Object> request);
	
	List<Object> lists(HashMap<String, Object> request);
}