package com.yd.dby.app.entity;

import java.util.Date;

public class YdAddress {
    private Integer adId;

    private Integer userId;

    private Integer adIsDefault;

    private String adLinkman;

    private String adLinktel;

    private Integer adSex;

    private Integer adProvinceId;

    private Integer adCityId;

    private Integer adAreaId;

    private String adPca;

    private String adStreet;

    private String adMore;

    private Date adCreatedTime;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdIsDefault() {
        return adIsDefault;
    }

    public void setAdIsDefault(Integer adIsDefault) {
        this.adIsDefault = adIsDefault;
    }

    public String getAdLinkman() {
        return adLinkman;
    }

    public void setAdLinkman(String adLinkman) {
        this.adLinkman = adLinkman == null ? null : adLinkman.trim();
    }

    public String getAdLinktel() {
        return adLinktel;
    }

    public void setAdLinktel(String adLinktel) {
        this.adLinktel = adLinktel == null ? null : adLinktel.trim();
    }

    public Integer getAdSex() {
        return adSex;
    }

    public void setAdSex(Integer adSex) {
        this.adSex = adSex;
    }

    public Integer getAdProvinceId() {
        return adProvinceId;
    }

    public void setAdProvinceId(Integer adProvinceId) {
        this.adProvinceId = adProvinceId;
    }

    public Integer getAdCityId() {
        return adCityId;
    }

    public void setAdCityId(Integer adCityId) {
        this.adCityId = adCityId;
    }

    public Integer getAdAreaId() {
        return adAreaId;
    }

    public void setAdAreaId(Integer adAreaId) {
        this.adAreaId = adAreaId;
    }

    public String getAdPca() {
        return adPca;
    }

    public void setAdPca(String adPca) {
        this.adPca = adPca == null ? null : adPca.trim();
    }

    public String getAdStreet() {
        return adStreet;
    }

    public void setAdStreet(String adStreet) {
        this.adStreet = adStreet == null ? null : adStreet.trim();
    }

    public String getAdMore() {
        return adMore;
    }

    public void setAdMore(String adMore) {
        this.adMore = adMore == null ? null : adMore.trim();
    }

    public Date getAdCreatedTime() {
        return adCreatedTime;
    }

    public void setAdCreatedTime(Date adCreatedTime) {
        this.adCreatedTime = adCreatedTime;
    }
}