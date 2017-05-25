package com.yd.dby.app.entity.vo;

import java.util.Date;

/**
 * 说明: 懒鱼商品详情
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月16日 上午10:53:50
 * @Version:1.0
 */
public class CtcGoodsDetailsVo {

	private Integer favId;// 收藏ID(不为null 则是收藏)

	private Integer ctcId;// 商品ID

	private String ctcPics;// 商品轮播图

	private Integer userId;// 用户ID

	private String userAvatar;// 用户头像

	private String userNickname;// 用户昵称

	private String userPca;// 用户所在省市区名称

	private Integer userGrade;// 用户等级

	private Date ctcCreatedTime;// 商品创建时间

	private String ctcName;// 商品名称

	private Double ctcPrice;// 商品价格

	private String ctcSummary;// 商品简介

	private Integer ctcTotalFav;// 收藏总数

	private String ctcCover;// 商品首图

	public Integer getFavId() {
		return favId;
	}

	public void setFavId(Integer favId) {
		if (favId == null) {
			this.favId = 0;
		} else {
			this.favId = favId;
		}
	}

	public Integer getCtcId() {
		return ctcId;
	}

	public void setCtcId(Integer ctcId) {
		this.ctcId = ctcId;
	}

	public String getCtcPics() {
		return ctcPics;
	}

	public void setCtcPics(String ctcPics) {
		this.ctcPics = ctcPics;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserPca() {
		return userPca;
	}

	public void setUserPca(String userPca) {
		this.userPca = userPca;
	}

	public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}

	public Date getCtcCreatedTime() {
		return ctcCreatedTime;
	}

	public void setCtcCreatedTime(Date ctcCreatedTime) {
		this.ctcCreatedTime = ctcCreatedTime;
	}

	public String getCtcName() {
		return ctcName;
	}

	public void setCtcName(String ctcName) {
		this.ctcName = ctcName;
	}

	public Double getCtcPrice() {
		return ctcPrice;
	}

	public void setCtcPrice(Double ctcPrice) {
		this.ctcPrice = ctcPrice;
	}

	public String getCtcSummary() {
		return ctcSummary;
	}

	public void setCtcSummary(String ctcSummary) {
		this.ctcSummary = ctcSummary;
	}

	public Integer getCtcTotalFav() {
		return ctcTotalFav;
	}

	public void setCtcTotalFav(Integer ctcTotalFav) {
		this.ctcTotalFav = ctcTotalFav;
	}

	public String getCtcCover() {
		return ctcCover;
	}

	public void setCtcCover(String ctcCover) {
		this.ctcCover = ctcCover;
	}

}
