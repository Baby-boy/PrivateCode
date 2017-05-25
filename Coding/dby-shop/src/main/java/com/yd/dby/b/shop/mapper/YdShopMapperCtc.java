package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdCtc;

public interface YdShopMapperCtc {

	public Integer Release(HashMap<String, Object> request);
	//获取懒鱼列表
	List<HashMap<String, Object>> get_ctc();
	//获取懒鱼一级分类
	List<HashMap<String, Object>> get_ctc_type();
	//获取懒鱼二级分类
	List<HashMap<String, Object>> get_ctc_type_two();
	
	List<Object> listByClassId2(@Param("ctc_classify_id2") Integer ctc_classify_id2);
	
	HashMap<String, Object> info(@Param("ctc_id") Integer ctc_id);
	
	
	/**
	 * 更新
	 * @param ydCtc
	 * @return
	 */
	Integer updateState(YdCtc ydCtc);

}
