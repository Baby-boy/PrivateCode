package com.yd.dby.b.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdCtcComment;

public interface YdShopMapperCtcComment {

	// 根据卖家uid查询出所有的评论
	List<YdCtcComment> queryAllCtcCommentBySellerId(@Param("uid") Integer uid);

	List<YdCtcComment> queryAllCtcCommentsBySellerId(@Param("uid") Integer uid, @Param("type") Integer type, @Param("to") Integer to);

	Integer goodCounts(@Param("uid") Integer uid, @Param("type") Integer type);

	// 添加评论
	Integer insertSelective(YdCtcComment ydCtcComment);

}