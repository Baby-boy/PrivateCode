package com.yd.dby.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class YdOrderGoods {
    private Integer ogId;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private Integer ogState;

    private Integer goodsNum;

    private Short refundNum;

    private BigDecimal goodsPrice;

    private BigDecimal goodsPayPrice;

    private BigDecimal discountPrice;

    private Integer storeId;

    private Integer buyerId;

    private String goodsSummary;

    private String goodsCover;

    private BigDecimal goodsOriginalPrice;

    private Integer ogNum;

    private Integer ogReturnState;

    private Integer ogPrice;

    private Date createdAt;

    public Integer getOgId() {
        return ogId;
    }

    public void setOgId(Integer ogId) {
        this.ogId = ogId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getOgState() {
        return ogState;
    }

    public void setOgState(Integer ogState) {
        this.ogState = ogState;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Short getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Short refundNum) {
        this.refundNum = refundNum;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsPayPrice() {
        return goodsPayPrice;
    }

    public void setGoodsPayPrice(BigDecimal goodsPayPrice) {
        this.goodsPayPrice = goodsPayPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getGoodsSummary() {
        return goodsSummary;
    }

    public void setGoodsSummary(String goodsSummary) {
        this.goodsSummary = goodsSummary == null ? null : goodsSummary.trim();
    }

    public String getGoodsCover() {
        return goodsCover;
    }

    public void setGoodsCover(String goodsCover) {
        this.goodsCover = goodsCover == null ? null : goodsCover.trim();
    }

    public BigDecimal getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(BigDecimal goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public Integer getOgNum() {
        return ogNum;
    }

    public void setOgNum(Integer ogNum) {
        this.ogNum = ogNum;
    }

    public Integer getOgReturnState() {
        return ogReturnState;
    }

    public void setOgReturnState(Integer ogReturnState) {
        this.ogReturnState = ogReturnState;
    }

    public Integer getOgPrice() {
        return ogPrice;
    }

    public void setOgPrice(Integer ogPrice) {
        this.ogPrice = ogPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}