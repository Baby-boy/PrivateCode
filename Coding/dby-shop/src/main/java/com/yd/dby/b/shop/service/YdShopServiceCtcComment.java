package com.yd.dby.b.shop.service;

import java.util.List;

import com.yd.dby.b.shop.entity.YdCtcComment;

public interface YdShopServiceCtcComment {

	// 根据卖家uid查询出所有的评论
	List<YdCtcComment> queryAllCtcCommentBySellerId(Integer uid);

	// 分类查询所有的评价
	List<YdCtcComment> queryAllCtcCommentsBySellerId(Integer uid, Integer type, Integer to);

	// 查询评价数
	Integer goodCounts(Integer uid, Integer type);

	// 发表评价
	Object addComment(String data, Integer ctc_order_id, Integer single);

}
