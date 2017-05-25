package com.yd.dby.app.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class ActivityVo {

	private String activityTitle;// 活动标题

	private String activityAppBg;// 活动背景图

	private List<GoodsListVo> goodsListVoList = new ArrayList<>();

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityAppBg() {
		return activityAppBg;
	}

	public void setActivityAppBg(String activityAppBg) {
		this.activityAppBg = activityAppBg;
	}

	public List<GoodsListVo> getGoodsListVoList() {
		return goodsListVoList;
	}

	public void setGoodsListVoList(List<GoodsListVo> goodsListVoList) {
		this.goodsListVoList = goodsListVoList;
	}

}
