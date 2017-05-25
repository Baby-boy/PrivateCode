package com.yd.dby.app.entity.vo;

import java.util.Date;

/**
 * 说明: 懒鱼商品
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月15日 下午3:22:20
 * @Version:1.0
 */
public class CtcGoodsListVo {

	private Integer ctcId;// 商品ID

	private Integer userId;// 用户ID

	private String userAvatar;// 用户头像

	private String userNickname;// 用户昵称

	private String ctcPics;// 商品图片

	private String ctcSummary;// 商品简介

	private String ctcPca;// 地址

	private Double ctcPrice;// 商品价格

	private Date ctcCreatedTime;// 创建时间(用于他人主页发布列表显示)

	// 用于我的发布的参数
	private String ctcName;// 商品名称
	private String ctcCover;// 商品封面图
	private Integer ctcIsAvailable;// 状态 1:出售中 2:下架 3:违规 4:已出售
	private Integer classifyOne;// 一级分类
	private Integer classifyTwo;// 二级分类
	private String ccNameTwo;// 二级分类名称
	private Integer dictId;// 字典ID
	private String dictValue;// 几层新

	public Integer getCtcId() {
		return ctcId;
	}

	public void setCtcId(Integer ctcId) {
		this.ctcId = ctcId;
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

	public String getCtcPics() {
		return ctcPics;
	}

	public void setCtcPics(String ctcPics) {
		this.ctcPics = ctcPics;
	}

	public String getCtcSummary() {
		return ctcSummary;
	}

	public void setCtcSummary(String ctcSummary) {
		this.ctcSummary = ctcSummary;
	}

	public String getCtcPca() {
		return ctcPca;
	}

	public void setCtcPca(String ctcPca) {
		this.ctcPca = ctcPca;
	}

	public Double getCtcPrice() {
		return ctcPrice;
	}

	public void setCtcPrice(Double ctcPrice) {
		if (null == ctcPrice) {
			this.ctcPrice = 0.00;
		} else {
			this.ctcPrice = ctcPrice;
		}
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

	public String getCtcCover() {
		return ctcCover;
	}

	public void setCtcCover(String ctcCover) {
		this.ctcCover = ctcCover;
	}

	public Integer getCtcIsAvailable() {
		return ctcIsAvailable;
	}

	public void setCtcIsAvailable(Integer ctcIsAvailable) {
		this.ctcIsAvailable = ctcIsAvailable;
	}

	public Integer getClassifyOne() {
		return classifyOne;
	}

	public void setClassifyOne(Integer classifyOne) {
		this.classifyOne = classifyOne;
	}

	public Integer getClassifyTwo() {
		return classifyTwo;
	}

	public void setClassifyTwo(Integer classifyTwo) {
		this.classifyTwo = classifyTwo;
	}

	public String getCcNameTwo() {
		return ccNameTwo;
	}

	public void setCcNameTwo(String ccNameTwo) {
		this.ccNameTwo = ccNameTwo;
	}

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

}
