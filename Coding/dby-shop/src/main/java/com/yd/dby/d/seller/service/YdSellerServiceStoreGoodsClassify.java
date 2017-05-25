package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

import com.yd.dby.d.seller.entity.YdSellerStoreGoodsClassify;

public interface YdSellerServiceStoreGoodsClassify {
	
//	/*全部*/
//	Map<String, Object> lists(HashMap<String, Object> request);
	
	/*商家分类-创建*/
	Map<String, Object> create(HashMap<String, Object> request);
	
	/*商家分类-列表*/
	Map<String, Object> index(HashMap<String, Object> request);
	
	/*商家分类-添加*/
	Object store(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify);
	
	/*商家分类-修改*/
	Map<String, Object> edit(HashMap<String, Object> request);
	
	/*商家分类-修改提交*/
	Object update(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify);
	
	/*商家分类-设置显示-隐藏*/
	Map<String, Object> setShow(HashMap<String, Object> request);
	
	/*商家分类删除*/
	Map<String, Object> destroy(HashMap<String, Object> request);
}
