package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerArticle;
import com.yd.gcj.entity.vo.YdMangerArticleVo;

public interface YdMangerMapperSystemArticle {

	/**
	 * description(查询所有的文章信息)
	 * @param
	 * @return
	 */
	List<YdMangerArticleVo> queryAllArticle(@Param("article_title")String article_title,@Param("article_type")Integer article_type);

	/**
	 * description(根据指定的article_id删除文章信息)
	 * @param
	 * @param article_id
	 * @return
	 */
	Integer deleteArticleByArticleId(Integer article_id);

	/**
	 * description(添加文章信息)
	 * @param
	 * @param ydMangerArticle
	 * @return
	 */
	Integer addArticle(YdMangerArticle ydMangerArticle);

	/**
	 * description(修改之前先根据article_id查询到当前文章信息)
	 * @param
	 * @param article_id
	 * @return
	 */
	YdMangerArticleVo queryArticleByArticleId(Integer article_id);

	/**
	 * description(根据指定的article_id修改文章信息)
	 * @param
	 * @param ydMangerArticle
	 * @return
	 */
	Integer updateArticleByArticleId(YdMangerArticle ydMangerArticle);

	/**
	 * description(根据指定的ac_id查询文章信息的总数)
	 * @param
	 * @param ac_id
	 * @return
	 */
	Integer queryArticleByArticleType(Integer ac_id);

	/**
	 * description(根据article_id查询文章内容)
	 * @param
	 * @param article_id
	 * @return
	 */
	YdMangerArticle queryArticleContentByArticleId(Integer article_id);


}
