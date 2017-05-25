package com.yd.dby.app.entity;

public class YdAuction {
    private Integer auctionId;

    private Integer userId;

    private Integer depotId;

    private Double auctionPrice;

    private Integer auctionCreatedTime;

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

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

    public Double getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(Double auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public Integer getAuctionCreatedTime() {
        return auctionCreatedTime;
    }

    public void setAuctionCreatedTime(Integer auctionCreatedTime) {
        this.auctionCreatedTime = auctionCreatedTime;
    }
}