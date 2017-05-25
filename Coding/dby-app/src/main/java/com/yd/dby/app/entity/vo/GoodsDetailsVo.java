package com.yd.dby.app.entity.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明: 商品详情
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月8日 下午2:38:29
 * @Version:1.0
 */
public class GoodsDetailsVo {

	private Integer favId;// 收藏 0:未收藏,>0 收藏

	private Integer goodsId;// 商品ID

	private String goodsPics;// 商品轮播图

	private String goodsName;// 商品名称

	private BigDecimal goodsPrice;// 商品价格

	private Float goodsAvgScore;// 商品平均分

	private String goodsContentPics;// 商品详细内容图片

	private String goodsSummary;// 商品简介

	private String goodsAttrSelect;// 商品属性选择

	private String goodsAttrs;// 商品属性

	private Integer goodsTotalFav;// 商品总收藏

	private String goodsCover;// 商品封面图

	private BigDecimal goodsFreight;// 商品运费

	private Integer storeId;// 店铺ID

	private String storeName;// 店铺名称

	private String storeBanner;// 店铺banner

	private List<Object> goodsAttrsList = new ArrayList<>();// --

	private List<Object> goodsAttrSelectList = new ArrayList<>();// --

	private List<GoodsDetailsCommentVo> commentList = new ArrayList<>();// 评论列表

	public Integer getFavId() {
		return favId;
	}

	public void setFavId(Integer favId) {
		if (null == favId) {
			this.favId = 0;
		} else {
			this.favId = favId;
		}
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(String goodsPics) {
		this.goodsPics = goodsPics;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Float getGoodsAvgScore() {
		return goodsAvgScore;
	}

	public void setGoodsAvgScore(Float goodsAvgScore) {
		this.goodsAvgScore = goodsAvgScore;
	}

	public String getGoodsContentPics() {
		return goodsContentPics;
	}

	public void setGoodsContentPics(String goodsContentPics) {
		this.goodsContentPics = goodsContentPics;
	}

	public String getGoodsSummary() {
		return goodsSummary;
	}

	public void setGoodsSummary(String goodsSummary) {
		this.goodsSummary = goodsSummary;
	}

	public String getGoodsAttrSelect() {
		return goodsAttrSelect;
	}

	public void setGoodsAttrSelect(String goodsAttrSelect) {
		this.goodsAttrSelect = goodsAttrSelect;
	}

	public String getGoodsAttrs() {
		return goodsAttrs;
	}

	public void setGoodsAttrs(String goodsAttrs) {
		this.goodsAttrs = goodsAttrs;
	}

	public Integer getGoodsTotalFav() {
		return goodsTotalFav;
	}

	public void setGoodsTotalFav(Integer goodsTotalFav) {
		this.goodsTotalFav = goodsTotalFav;
	}

	public List<GoodsDetailsCommentVo> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<GoodsDetailsCommentVo> commentList) {
		this.commentList = commentList;
	}

	public String getGoodsCover() {
		return goodsCover;
	}

	public void setGoodsCover(String goodsCover) {
		this.goodsCover = goodsCover;
	}

	public BigDecimal getGoodsFreight() {
		return goodsFreight;
	}

	public void setGoodsFreight(BigDecimal goodsFreight) {
		this.goodsFreight = goodsFreight;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreBanner() {
		return storeBanner;
	}

	public void setStoreBanner(String storeBanner) {
		this.storeBanner = storeBanner;
	}

	public List<Object> getGoodsAttrsList() {
		return goodsAttrsList;
	}

	public void setGoodsAttrsList(List<Object> goodsAttrsList) {
		this.goodsAttrsList = goodsAttrsList;
	}

	public List<Object> getGoodsAttrSelectList() {
		return goodsAttrSelectList;
	}

	public void setGoodsAttrSelectList(List<Object> goodsAttrSelectList) {
		this.goodsAttrSelectList = goodsAttrSelectList;
	}

}
