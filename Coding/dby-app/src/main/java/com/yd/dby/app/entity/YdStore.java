package com.yd.dby.app.entity;

import java.util.Date;

public class YdStore {
    private Integer storeId;

    private Integer userId;

    private Boolean storeIsAvailable;

    private Boolean storeIsSelf;

    private Boolean storeIsBrand;

    private Boolean storeIsVerify;

    private Integer storeClassify1;

    private Integer storeClassify2;

    private Integer storeClassify3;

    private String storeName;

    private String storeSummary;

    private String storeLogo;

    private String storeCover;

    private String storeTelephone;

    private String storeMobile;

    private String storeQq;

    private String storeAddress;

    private Double storeLongitude;

    private Double storeLatitude;

    private Float storeAvgScore;

    private Integer storeTotalFav;

    private String storeKeywords;

    private String storeDescription;

    private Date storeOpenTime;

    private Date storeCreatedTime;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getStoreIsAvailable() {
        return storeIsAvailable;
    }

    public void setStoreIsAvailable(Boolean storeIsAvailable) {
        this.storeIsAvailable = storeIsAvailable;
    }

    public Boolean getStoreIsSelf() {
        return storeIsSelf;
    }

    public void setStoreIsSelf(Boolean storeIsSelf) {
        this.storeIsSelf = storeIsSelf;
    }

    public Boolean getStoreIsBrand() {
        return storeIsBrand;
    }

    public void setStoreIsBrand(Boolean storeIsBrand) {
        this.storeIsBrand = storeIsBrand;
    }

    public Boolean getStoreIsVerify() {
        return storeIsVerify;
    }

    public void setStoreIsVerify(Boolean storeIsVerify) {
        this.storeIsVerify = storeIsVerify;
    }

    public Integer getStoreClassify1() {
        return storeClassify1;
    }

    public void setStoreClassify1(Integer storeClassify1) {
        this.storeClassify1 = storeClassify1;
    }

    public Integer getStoreClassify2() {
        return storeClassify2;
    }

    public void setStoreClassify2(Integer storeClassify2) {
        this.storeClassify2 = storeClassify2;
    }

    public Integer getStoreClassify3() {
        return storeClassify3;
    }

    public void setStoreClassify3(Integer storeClassify3) {
        this.storeClassify3 = storeClassify3;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreSummary() {
        return storeSummary;
    }

    public void setStoreSummary(String storeSummary) {
        this.storeSummary = storeSummary == null ? null : storeSummary.trim();
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo == null ? null : storeLogo.trim();
    }

    public String getStoreCover() {
        return storeCover;
    }

    public void setStoreCover(String storeCover) {
        this.storeCover = storeCover == null ? null : storeCover.trim();
    }

    public String getStoreTelephone() {
        return storeTelephone;
    }

    public void setStoreTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone == null ? null : storeTelephone.trim();
    }

    public String getStoreMobile() {
        return storeMobile;
    }

    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile == null ? null : storeMobile.trim();
    }

    public String getStoreQq() {
        return storeQq;
    }

    public void setStoreQq(String storeQq) {
        this.storeQq = storeQq == null ? null : storeQq.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public Double getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(Double storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public Double getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(Double storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public Float getStoreAvgScore() {
        return storeAvgScore;
    }

    public void setStoreAvgScore(Float storeAvgScore) {
        this.storeAvgScore = storeAvgScore;
    }

    public Integer getStoreTotalFav() {
        return storeTotalFav;
    }

    public void setStoreTotalFav(Integer storeTotalFav) {
        this.storeTotalFav = storeTotalFav;
    }

    public String getStoreKeywords() {
        return storeKeywords;
    }

    public void setStoreKeywords(String storeKeywords) {
        this.storeKeywords = storeKeywords == null ? null : storeKeywords.trim();
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription == null ? null : storeDescription.trim();
    }

    public Date getStoreOpenTime() {
        return storeOpenTime;
    }

    public void setStoreOpenTime(Date storeOpenTime) {
        this.storeOpenTime = storeOpenTime;
    }

    public Date getStoreCreatedTime() {
        return storeCreatedTime;
    }

    public void setStoreCreatedTime(Date storeCreatedTime) {
        this.storeCreatedTime = storeCreatedTime;
    }
}