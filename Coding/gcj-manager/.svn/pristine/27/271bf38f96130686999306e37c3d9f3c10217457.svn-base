package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerArticle;

public interface YdMangerServiceArticle {
	

	
	/***
	 * 添加文章信息
	 * @param article
	 * @return
	 */
	@Transactional
	Object $insert(HashMap<String, Object> map);
	
	/***
	 * 根据id删除指定文章
	 * @param article_id
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
	/***
	 * 修改文章信息
	 * @param article
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/***
	 * 查询文章信息
	 * @param type
	 * @param startPageNum
	 * @param endPageNum
	 * @return
	 */
	List<YdMangerArticle> $queryAll(Integer type,Integer startPageNum,Integer queryPageNum);
	
	/***
	 * 查询相应条件的相应文章数量
	 * @param type
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	Integer queryCountAll(Integer type);
	
	/***
	 * 根据id查询指定的文章细腻
	 * @param article_id
	 * @return
	 */
	Object $queryById(HashMap<String, Object> map);
	
	/***
	 * 查询文章信息数量，主要用于分页
	 * @return
	 */
	Object $queryCountNum();
	
	
}
