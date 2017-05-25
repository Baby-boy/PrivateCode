package com.yd.dby.app.entity;

public class YdAuctionTop {
    private Integer userId;

    private Integer depotId;

    private Integer topId;

    private Float topPrice;

    private Integer isPay;

    private Integer isReceipt;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Float getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(Float topPrice) {
        this.topPrice = topPrice;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(Integer isReceipt) {
        this.isReceipt = isReceipt;
    }
}