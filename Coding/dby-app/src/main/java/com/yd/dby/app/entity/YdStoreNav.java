package com.yd.dby.app.entity;

import java.util.Date;

public class YdStoreNav {
    private Integer navId;

    private Integer storeId;

    private String navTitle;

    private Integer navPid;

    private Integer navSort;

    private String navUrl;

    private Byte isShow;

    private Byte isOpen;

    private Date createdAt;

    private Date updatedAt;

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getNavTitle() {
        return navTitle;
    }

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle == null ? null : navTitle.trim();
    }

    public Integer getNavPid() {
        return navPid;
    }

    public void setNavPid(Integer navPid) {
        this.navPid = navPid;
    }

    public Integer getNavSort() {
        return navSort;
    }

    public void setNavSort(Integer navSort) {
        this.navSort = navSort;
    }

    public String getNavUrl() {
        return navUrl;
    }

    public void setNavUrl(String navUrl) {
        this.navUrl = navUrl == null ? null : navUrl.trim();
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public Byte getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Byte isOpen) {
        this.isOpen = isOpen;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}