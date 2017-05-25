package com.yd.dby.app.entity;

public class YdSeckillGoods {
    private Integer sgId;

    private Integer seckillId;

    private Integer depotId;

    private Integer storeId;

    private String storeName;

    private Byte sgSort;

    private String sgApplyTime;

    private Byte sgState;

    private String sgSeason;

    private String sgCover;

    public Integer getSgId() {
        return sgId;
    }

    public void setSgId(Integer sgId) {
        this.sgId = sgId;
    }

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
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
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Byte getSgSort() {
        return sgSort;
    }

    public void setSgSort(Byte sgSort) {
        this.sgSort = sgSort;
    }

    public String getSgApplyTime() {
        return sgApplyTime;
    }

    public void setSgApplyTime(String sgApplyTime) {
        this.sgApplyTime = sgApplyTime == null ? null : sgApplyTime.trim();
    }

    public Byte getSgState() {
        return sgState;
    }

    public void setSgState(Byte sgState) {
        this.sgState = sgState;
    }

    public String getSgSeason() {
        return sgSeason;
    }

    public void setSgSeason(String sgSeason) {
        this.sgSeason = sgSeason == null ? null : sgSeason.trim();
    }

    public String getSgCover() {
        return sgCover;
    }

    public void setSgCover(String sgCover) {
        this.sgCover = sgCover == null ? null : sgCover.trim();
    }
}