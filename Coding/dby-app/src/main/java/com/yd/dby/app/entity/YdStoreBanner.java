package com.yd.dby.app.entity;

import java.util.Date;

public class YdStoreBanner {
    private Integer adsId;

    private Integer tagId;

    private Integer adsSort;

    private String adsType;

    private Integer adsJumpType;

    private String adsJumpAddress;

    private Integer adsIsOpen;

    private String adsTitle;

    private String adsSubtitle;

    private String adsImgsrc;

    private Float adsPrice;

    private Float adsOriginalPrice;

    private Date adsCreatedTime;

    private Integer adsPid;

    private Date adsBeginTime;

    private Date adsEndTime;

    private String adsStoreName;

    private String adsActivityTitle;

    private String adsSubtitlySubtitle;

    private Integer adsTotalBid;

    public Integer getAdsId() {
        return adsId;
    }

    public void setAdsId(Integer adsId) {
        this.adsId = adsId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getAdsSort() {
        return adsSort;
    }

    public void setAdsSort(Integer adsSort) {
        this.adsSort = adsSort;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType == null ? null : adsType.trim();
    }

    public Integer getAdsJumpType() {
        return adsJumpType;
    }

    public void setAdsJumpType(Integer adsJumpType) {
        this.adsJumpType = adsJumpType;
    }

    public String getAdsJumpAddress() {
        return adsJumpAddress;
    }

    public void setAdsJumpAddress(String adsJumpAddress) {
        this.adsJumpAddress = adsJumpAddress == null ? null : adsJumpAddress.trim();
    }

    public Integer getAdsIsOpen() {
        return adsIsOpen;
    }

    public void setAdsIsOpen(Integer adsIsOpen) {
        this.adsIsOpen = adsIsOpen;
    }

    public String getAdsTitle() {
        return adsTitle;
    }

    public void setAdsTitle(String adsTitle) {
        this.adsTitle = adsTitle == null ? null : adsTitle.trim();
    }

    public String getAdsSubtitle() {
        return adsSubtitle;
    }

    public void setAdsSubtitle(String adsSubtitle) {
        this.adsSubtitle = adsSubtitle == null ? null : adsSubtitle.trim();
    }

    public String getAdsImgsrc() {
        return adsImgsrc;
    }

    public void setAdsImgsrc(String adsImgsrc) {
        this.adsImgsrc = adsImgsrc == null ? null : adsImgsrc.trim();
    }

    public Float getAdsPrice() {
        return adsPrice;
    }

    public void setAdsPrice(Float adsPrice) {
        this.adsPrice = adsPrice;
    }

    public Float getAdsOriginalPrice() {
        return adsOriginalPrice;
    }

    public void setAdsOriginalPrice(Float adsOriginalPrice) {
        this.adsOriginalPrice = adsOriginalPrice;
    }

    public Date getAdsCreatedTime() {
        return adsCreatedTime;
    }

    public void setAdsCreatedTime(Date adsCreatedTime) {
        this.adsCreatedTime = adsCreatedTime;
    }

    public Integer getAdsPid() {
        return adsPid;
    }

    public void setAdsPid(Integer adsPid) {
        this.adsPid = adsPid;
    }

    public Date getAdsBeginTime() {
        return adsBeginTime;
    }

    public void setAdsBeginTime(Date adsBeginTime) {
        this.adsBeginTime = adsBeginTime;
    }

    public Date getAdsEndTime() {
        return adsEndTime;
    }

    public void setAdsEndTime(Date adsEndTime) {
        this.adsEndTime = adsEndTime;
    }

    public String getAdsStoreName() {
        return adsStoreName;
    }

    public void setAdsStoreName(String adsStoreName) {
        this.adsStoreName = adsStoreName == null ? null : adsStoreName.trim();
    }

    public String getAdsActivityTitle() {
        return adsActivityTitle;
    }

    public void setAdsActivityTitle(String adsActivityTitle) {
        this.adsActivityTitle = adsActivityTitle == null ? null : adsActivityTitle.trim();
    }

    public String getAdsSubtitlySubtitle() {
        return adsSubtitlySubtitle;
    }

    public void setAdsSubtitlySubtitle(String adsSubtitlySubtitle) {
        this.adsSubtitlySubtitle = adsSubtitlySubtitle == null ? null : adsSubtitlySubtitle.trim();
    }

    public Integer getAdsTotalBid() {
        return adsTotalBid;
    }

    public void setAdsTotalBid(Integer adsTotalBid) {
        this.adsTotalBid = adsTotalBid;
    }
}