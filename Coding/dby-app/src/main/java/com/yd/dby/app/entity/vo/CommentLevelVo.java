package com.yd.dby.app.entity.vo;

/**
 * 说明: 评论级别统计
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月8日 下午4:39:15
 * @Version:1.0
 */
public class CommentLevelVo {

	// 全部评论
	private Integer allNum;

	// 好评
	private Integer goodNum;

	// 中评
	private Integer justNum;

	// 差评
	private Integer badNum;

	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Integer getJustNum() {
		return justNum;
	}

	public void setJustNum(Integer justNum) {
		this.justNum = justNum;
	}

	public Integer getBadNum() {
		return badNum;
	}

	public void setBadNum(Integer badNum) {
		this.badNum = badNum;
	}

}
