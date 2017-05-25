package com.yd.dby.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class YdCtc {
    private Integer ctcId;

    private Integer userId;

    private String ctcName;

    private Integer ctcIsAvailable;

    private String ctcCover;

    private String ctcPics;

    private Integer ctcClassifyId1;

    private Integer ctcClassifyId2;

    private Integer ctcTotalMessage;

    private Integer ctcTotalFav;

    private BigDecimal ctcPrice;

    private BigDecimal ctcLogisticsPrice;

    private Integer ctcProvinceId;

    private Integer ctcCityId;

    private Integer ctcAreaId;

    private String ctcProvinceValue;

    private String ctcCityValue;

    private String ctcAreaValue;

    private Date ctcCreatedTime;

    private Integer ctcQualityId;

    private String ctcQualityText;

    private String ctcPca;

    private String ctcSummary;

    public Integer getCtcId() {
        return ctcId;
    }

    public void setCtcId(Integer ctcId) {
        this.ctcId = ctcId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCtcName() {
        return ctcName;
    }

    public void setCtcName(String ctcName) {
        this.ctcName = ctcName == null ? null : ctcName.trim();
    }

    public Integer getCtcIsAvailable() {
        return ctcIsAvailable;
    }

    public void setCtcIsAvailable(Integer ctcIsAvailable) {
        this.ctcIsAvailable = ctcIsAvailable;
    }

    public String getCtcCover() {
        return ctcCover;
    }

    public void setCtcCover(String ctcCover) {
        this.ctcCover = ctcCover == null ? null : ctcCover.trim();
    }

    public String getCtcPics() {
        return ctcPics;
    }

    public void setCtcPics(String ctcPics) {
        this.ctcPics = ctcPics == null ? null : ctcPics.trim();
    }

    public Integer getCtcClassifyId1() {
        return ctcClassifyId1;
    }

    public void setCtcClassifyId1(Integer ctcClassifyId1) {
        this.ctcClassifyId1 = ctcClassifyId1;
    }

    public Integer getCtcClassifyId2() {
        return ctcClassifyId2;
    }

    public void setCtcClassifyId2(Integer ctcClassifyId2) {
        this.ctcClassifyId2 = ctcClassifyId2;
    }

    public Integer getCtcTotalMessage() {
        return ctcTotalMessage;
    }

    public void setCtcTotalMessage(Integer ctcTotalMessage) {
        this.ctcTotalMessage = ctcTotalMessage;
    }

    public Integer getCtcTotalFav() {
        return ctcTotalFav;
    }

    public void setCtcTotalFav(Integer ctcTotalFav) {
        this.ctcTotalFav = ctcTotalFav;
    }

    public BigDecimal getCtcPrice() {
        return ctcPrice;
    }

    public void setCtcPrice(BigDecimal ctcPrice) {
        this.ctcPrice = ctcPrice;
    }

    public BigDecimal getCtcLogisticsPrice() {
        return ctcLogisticsPrice;
    }

    public void setCtcLogisticsPrice(BigDecimal ctcLogisticsPrice) {
        this.ctcLogisticsPrice = ctcLogisticsPrice;
    }

    public Integer getCtcProvinceId() {
        return ctcProvinceId;
    }

    public void setCtcProvinceId(Integer ctcProvinceId) {
        this.ctcProvinceId = ctcProvinceId;
    }

    public Integer getCtcCityId() {
        return ctcCityId;
    }

    public void setCtcCityId(Integer ctcCityId) {
        this.ctcCityId = ctcCityId;
    }

    public Integer getCtcAreaId() {
        return ctcAreaId;
    }

    public void setCtcAreaId(Integer ctcAreaId) {
        this.ctcAreaId = ctcAreaId;
    }

    public String getCtcProvinceValue() {
        return ctcProvinceValue;
    }

    public void setCtcProvinceValue(String ctcProvinceValue) {
        this.ctcProvinceValue = ctcProvinceValue == null ? null : ctcProvinceValue.trim();
    }

    public String getCtcCityValue() {
        return ctcCityValue;
    }

    public void setCtcCityValue(String ctcCityValue) {
        this.ctcCityValue = ctcCityValue == null ? null : ctcCityValue.trim();
    }

    public String getCtcAreaValue() {
        return ctcAreaValue;
    }

    public void setCtcAreaValue(String ctcAreaValue) {
        this.ctcAreaValue = ctcAreaValue == null ? null : ctcAreaValue.trim();
    }

    public Date getCtcCreatedTime() {
        return ctcCreatedTime;
    }

    public void setCtcCreatedTime(Date ctcCreatedTime) {
        this.ctcCreatedTime = ctcCreatedTime;
    }

    public Integer getCtcQualityId() {
        return ctcQualityId;
    }

    public void setCtcQualityId(Integer ctcQualityId) {
        this.ctcQualityId = ctcQualityId;
    }

    public String getCtcQualityText() {
        return ctcQualityText;
    }

    public void setCtcQualityText(String ctcQualityText) {
        this.ctcQualityText = ctcQualityText == null ? null : ctcQualityText.trim();
    }

    public String getCtcPca() {
        return ctcPca;
    }

    public void setCtcPca(String ctcPca) {
        this.ctcPca = ctcPca == null ? null : ctcPca.trim();
    }

    public String getCtcSummary() {
        return ctcSummary;
    }

    public void setCtcSummary(String ctcSummary) {
        this.ctcSummary = ctcSummary == null ? null : ctcSummary.trim();
    }
}