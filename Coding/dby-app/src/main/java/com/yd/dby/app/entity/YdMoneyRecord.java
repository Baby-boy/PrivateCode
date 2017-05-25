package com.yd.dby.app.entity;

import java.util.Date;

public class YdMoneyRecord {
    private Integer moneyId;

    private Integer userId;

    private Integer moneyFid;

    private String moneyPayNo;

    private Double moneyNum;

    private Byte moneyType;

    private String moneyContent;

    private Date moneyCreatedTime;

    public Integer getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMoneyFid() {
        return moneyFid;
    }

    public void setMoneyFid(Integer moneyFid) {
        this.moneyFid = moneyFid;
    }

    public String getMoneyPayNo() {
        return moneyPayNo;
    }

    public void setMoneyPayNo(String moneyPayNo) {
        this.moneyPayNo = moneyPayNo == null ? null : moneyPayNo.trim();
    }

    public Double getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(Double moneyNum) {
        this.moneyNum = moneyNum;
    }

    public Byte getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Byte moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoneyContent() {
        return moneyContent;
    }

    public void setMoneyContent(String moneyContent) {
        this.moneyContent = moneyContent == null ? null : moneyContent.trim();
    }

    public Date getMoneyCreatedTime() {
        return moneyCreatedTime;
    }

    public void setMoneyCreatedTime(Date moneyCreatedTime) {
        this.moneyCreatedTime = moneyCreatedTime;
    }
}