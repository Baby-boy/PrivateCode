package com.yd.dby.app.entity;

public class YdGoodsBuf {
    private Integer goodsId;

    private String type;

    private String goodsName;

    private String goodsImage;

    private Integer goodsNum;

    private Integer depotStock;

    private Integer storeId;

    private Long depotPrice;

    private Long depotOriginalPrice;

    private Integer goodsTotal;

    private Integer createdAt;

    private Integer updatedAt;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getDepotStock() {
        return depotStock;
    }

    public void setDepotStock(Integer depotStock) {
        this.depotStock = depotStock;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Long getDepotPrice() {
        return depotPrice;
    }

    public void setDepotPrice(Long depotPrice) {
        this.depotPrice = depotPrice;
    }

    public Long getDepotOriginalPrice() {
        return depotOriginalPrice;
    }

    public void setDepotOriginalPrice(Long depotOriginalPrice) {
        this.depotOriginalPrice = depotOriginalPrice;
    }

    public Integer getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(Integer goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }
}