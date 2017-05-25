package com.yd.dby.app.entity;

import java.util.Date;

public class YdStoreLink {
    private Integer id;

    private Integer storeId;

    private Integer slSort;

    private String slTitle;

    private String slUrl;

    private Date createdAt;

    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getSlSort() {
        return slSort;
    }

    public void setSlSort(Integer slSort) {
        this.slSort = slSort;
    }

    public String getSlTitle() {
        return slTitle;
    }

    public void setSlTitle(String slTitle) {
        this.slTitle = slTitle == null ? null : slTitle.trim();
    }

    public String getSlUrl() {
        return slUrl;
    }

    public void setSlUrl(String slUrl) {
        this.slUrl = slUrl == null ? null : slUrl.trim();
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