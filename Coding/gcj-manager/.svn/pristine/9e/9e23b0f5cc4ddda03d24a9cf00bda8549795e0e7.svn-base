package com.yd.gcj.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface YdMangerServiceBanner {

	/***
	 * 查询所有图片信息
	 */
	Map<String, Object> $queryAll();
	
	/***
	 * 根据分页信息查询图片所有信息
	 */
	Map<String, Object> $queryAllByPage(HashMap<String, Object> map);
	
	/***
	 * 根据图片信息id集合查询相应的数据
	 */
	Map<String, Object> $queryByIds(HashMap<String, Object> map);
	
	/***
	 * 根据id查询单个图片信息
	 */
	Map<String, Object> $queryById(HashMap<String, Object> map);
	
	/***
	 * 添加新图片信息 
	 */
	@Transactional
	Map<String, Object> $insert(HashMap<String, Object> map);
	
	/***
	 * 跟新图片信息
	 */
	@Transactional
	Map<String, Object> $update(HashMap<String, Object> map);
	
	/***
	 * 删除指定id图片信息
	 */
	@Transactional
	Map<String, Object> $delete(HashMap<String, Object> map);
	
}
