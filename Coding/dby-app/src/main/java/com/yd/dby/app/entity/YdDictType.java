package com.yd.dby.app.entity;

public class YdDictType {
    private Integer dtId;

    private String dtName;

    private String dtCode;

    private Boolean dtIsDelete;

    public Integer getDtId() {
        return dtId;
    }

    public void setDtId(Integer dtId) {
        this.dtId = dtId;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName == null ? null : dtName.trim();
    }

    public String getDtCode() {
        return dtCode;
    }

    public void setDtCode(String dtCode) {
        this.dtCode = dtCode == null ? null : dtCode.trim();
    }

    public Boolean getDtIsDelete() {
        return dtIsDelete;
    }

    public void setDtIsDelete(Boolean dtIsDelete) {
        this.dtIsDelete = dtIsDelete;
    }
}