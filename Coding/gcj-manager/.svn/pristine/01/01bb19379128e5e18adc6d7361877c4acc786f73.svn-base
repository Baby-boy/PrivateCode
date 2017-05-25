package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerArticle;

public interface YdMangerMapperArticle {
	
	
	/***
	 * 添加文章信息
	 * @param article
	 * @return
	 */
	Integer $insert(YdMangerArticle article);
	
	/***
	 * 根据id删除指定文章
	 * @param article_id
	 * @return
	 */
	Integer $delete(@Param("article_id") Integer article_id);
	
	/***
	 * 修改文章信息
	 * @param article
	 * @return
	 */
	Integer $update(YdMangerArticle article);
	
	/***
	 * 查询错条文章信息
	 * @param startPageNum 开始条数
	 * @param queryPageNum 查询条数
	 * @return
	 */
	List<YdMangerArticle> $queryAll(@Param("type") Integer type,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 查询相应条件的相应文章数量
	 * @param type
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	Integer $queryCountAll(@Param("type") Integer type);
	
	/***
	 * 根据id查询指定的文章细腻
	 * @param article_id
	 * @return
	 */
	YdMangerArticle $queryById(@Param("article")Integer article_id);
	
	/***
	 * 查询文章信息数量，主要用于分页
	 * @return
	 */
	Integer $queryCountNum();
	
}
