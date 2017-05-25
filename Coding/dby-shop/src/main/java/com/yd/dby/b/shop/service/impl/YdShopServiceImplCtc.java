package com.yd.dby.b.shop.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperCtc;
import com.yd.dby.b.shop.service.YdShopServiceCtc;

@Service("_YdShopServiceCtc")
public class YdShopServiceImplCtc implements YdShopServiceCtc {
	@Autowired
	private YdShopMapperCtc ydShopMapperCtc;


	@Override
	public HashMap<String, Object> Release(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		request.put("ctc_created_time", sdf.format(new Date()));
		
		
		map.put("data", ydShopMapperCtc.Release(request));
		
		return map;
	}


	/**
	 * 获取懒鱼
	 */
	@Override
	public List<HashMap<String, Object>> get_ctc() {
		return ydShopMapperCtc.get_ctc();
	}
	
	
	/**
	 * 懒鱼
	 */
	@Override
	public List<Object> listByClassId2(Integer ctc_classify_id2) {
		return ydShopMapperCtc.listByClassId2(ctc_classify_id2);
	}


	/**
	 * 获取懒鱼一级分类
	 */
	@Override
	public List<HashMap<String, Object>> get_ctc_type() {
		return ydShopMapperCtc.get_ctc_type();
	}


	/**
	 * 获取懒鱼二级分类
	 */
	@Override
	public List<HashMap<String, Object>> get_ctc_type_two() {
		return ydShopMapperCtc.get_ctc_type_two();
	}
	
	
}