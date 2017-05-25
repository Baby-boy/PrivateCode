package com.yd.dby.app.entity;

public class YdBanner {
    private Integer bannerId;

    private String bannerType;

    private String bannerTitle;

    private String bannerCover;

    private Boolean bannerOpenType;

    private Integer bannerKeyId;

    private String bannerUrl;

    private Boolean bannerIsShow;

    private Byte bannerSort;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType == null ? null : bannerType.trim();
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle == null ? null : bannerTitle.trim();
    }

    public String getBannerCover() {
        return bannerCover;
    }

    public void setBannerCover(String bannerCover) {
        this.bannerCover = bannerCover == null ? null : bannerCover.trim();
    }

    public Boolean getBannerOpenType() {
        return bannerOpenType;
    }

    public void setBannerOpenType(Boolean bannerOpenType) {
        this.bannerOpenType = bannerOpenType;
    }

    public Integer getBannerKeyId() {
        return bannerKeyId;
    }

    public void setBannerKeyId(Integer bannerKeyId) {
        this.bannerKeyId = bannerKeyId;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public Boolean getBannerIsShow() {
        return bannerIsShow;
    }

    public void setBannerIsShow(Boolean bannerIsShow) {
        this.bannerIsShow = bannerIsShow;
    }

    public Byte getBannerSort() {
        return bannerSort;
    }

    public void setBannerSort(Byte bannerSort) {
        this.bannerSort = bannerSort;
    }
}