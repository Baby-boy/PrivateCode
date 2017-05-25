package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerArticle;
import com.yd.gcj.entity.vo.YdMangerArticleVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemArticle;
import com.yd.gcj.system.service.YdMangerServiceSystemArticle;
@Service("ydMangerServiceSystemArticle")
public class YdMangerServiceImplSystemArticle implements YdMangerServiceSystemArticle {

	@Autowired
	private YdMangerMapperSystemArticle ydMangerMapperSystemArticle; 
	
	//查询所有的文章信息
	@Override
	public List<YdMangerArticleVo> queryAllArticle(String article_title,Integer article_type) {

		List<YdMangerArticleVo> articleList =  ydMangerMapperSystemArticle.queryAllArticle(article_title,article_type);
		return articleList;
	}

	//删除文章信息
	@Override
	public Integer deleteArticleByArticleId(Integer article_id) {

		Integer delNum = ydMangerMapperSystemArticle.deleteArticleByArticleId(article_id);
		return delNum;
	}

	//添加文章信息
	@Override
	public Integer addArticle(YdMangerArticle ydMangerArticle) {
		Integer addNum = ydMangerMapperSystemArticle.addArticle(ydMangerArticle);
		
		return addNum;
	}
	
	//查到当前文章信息
	@Override
	public YdMangerArticleVo queryArticleByArticleId(Integer article_id) {
		YdMangerArticleVo ydMangerArticle = ydMangerMapperSystemArticle.queryArticleByArticleId(article_id);
		return ydMangerArticle;
	}

	//修改文章信息
	@Override
	public Integer updateArticleByArticleId(YdMangerArticle ydMangerArticle) {
		Integer updateNum = ydMangerMapperSystemArticle.updateArticleByArticleId(ydMangerArticle);
		return updateNum;
	}

	//根据指定的ac_id查询文章信息的总数
	@Override
	public Integer queryArticleByArticleType(Integer ac_id) {
		Integer arCount = ydMangerMapperSystemArticle.queryArticleByArticleType(ac_id);
		return arCount;
	}

	//查询文章内容
	@Override
	public YdMangerArticle queryArticleContentByArticleId(Integer article_id) {
		YdMangerArticle ydMangerArticle = ydMangerMapperSystemArticle.queryArticleContentByArticleId(article_id);
		return ydMangerArticle;
	}

}
