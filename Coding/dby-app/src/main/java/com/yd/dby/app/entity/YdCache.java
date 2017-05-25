package com.yd.dby.app.entity;

import java.util.Date;

public class YdCache {
    private Integer cacheId;

    private String cacheType;

    private Date cacheCreatedTime;

    private String cacheValue;

    public Integer getCacheId() {
        return cacheId;
    }

    public void setCacheId(Integer cacheId) {
        this.cacheId = cacheId;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType == null ? null : cacheType.trim();
    }

    public Date getCacheCreatedTime() {
        return cacheCreatedTime;
    }

    public void setCacheCreatedTime(Date cacheCreatedTime) {
        this.cacheCreatedTime = cacheCreatedTime;
    }

    public String getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue == null ? null : cacheValue.trim();
    }
}