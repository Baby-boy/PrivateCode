package com.yd.dby.app.entity;

public class YdJson {
    private Integer ydId;

    private String ydKey;

    private String ydValue;

    public Integer getYdId() {
        return ydId;
    }

    public void setYdId(Integer ydId) {
        this.ydId = ydId;
    }

    public String getYdKey() {
        return ydKey;
    }

    public void setYdKey(String ydKey) {
        this.ydKey = ydKey == null ? null : ydKey.trim();
    }

    public String getYdValue() {
        return ydValue;
    }

    public void setYdValue(String ydValue) {
        this.ydValue = ydValue == null ? null : ydValue.trim();
    }
}