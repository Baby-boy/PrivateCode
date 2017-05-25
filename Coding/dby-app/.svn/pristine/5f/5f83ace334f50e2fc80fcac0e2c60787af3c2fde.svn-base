package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdArticle;

public interface YdArticleMapper {
	public int deleteByPrimaryKey(Integer articleId);

	public int insert(YdArticle record);

	public int insertSelective(YdArticle record);

	public YdArticle selectByPrimaryKey(Integer articleId);

	public int updateByPrimaryKeySelective(YdArticle record);

	public int updateByPrimaryKeyWithBLOBs(YdArticle record);

	public int updateByPrimaryKey(YdArticle record);

	/** 查询流通置换规则文章 */
	public List<YdArticle> selectRuleArticleByAcCode(@Param("acCode") String acCode);
}