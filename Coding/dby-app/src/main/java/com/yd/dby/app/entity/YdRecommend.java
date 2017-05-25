package com.yd.dby.app.entity;

import java.math.BigDecimal;

public class YdRecommend {
    private Integer recId;

    private Integer recFid;

    private String recTitle;

    private String recCover;

    private BigDecimal recPrice;

    private String recCode;

    private Byte recSort;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getRecFid() {
        return recFid;
    }

    public void setRecFid(Integer recFid) {
        this.recFid = recFid;
    }

    public String getRecTitle() {
        return recTitle;
    }

    public void setRecTitle(String recTitle) {
        this.recTitle = recTitle == null ? null : recTitle.trim();
    }

    public String getRecCover() {
        return recCover;
    }

    public void setRecCover(String recCover) {
        this.recCover = recCover == null ? null : recCover.trim();
    }

    public BigDecimal getRecPrice() {
        return recPrice;
    }

    public void setRecPrice(BigDecimal recPrice) {
        this.recPrice = recPrice;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode == null ? null : recCode.trim();
    }

    public Byte getRecSort() {
        return recSort;
    }

    public void setRecSort(Byte recSort) {
        this.recSort = recSort;
    }
}