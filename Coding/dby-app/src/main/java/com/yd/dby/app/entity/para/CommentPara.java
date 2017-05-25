package com.yd.dby.app.entity.para;

/**
 * 说明:
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月8日 下午3:43:22
 * @Version:1.0
 */
public class CommentPara extends BasePara {

	private Integer goodsId;

	// 评论级别 (1:全部,2:好,3:中, 4:差)
	private Integer commentLevel;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}

}
