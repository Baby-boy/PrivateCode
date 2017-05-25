package com.yd.dby.app.entity;

import java.util.Date;

public class YdDict {
    private Integer dictId;

    private String dtCode;

    private String dictType;

    private String dictKey;

    private Date dictCreatedTime;

    private Integer dictPid;

    private Integer dictSort;

    private String dictValue;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDtCode() {
        return dtCode;
    }

    public void setDtCode(String dtCode) {
        this.dtCode = dtCode == null ? null : dtCode.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public Date getDictCreatedTime() {
        return dictCreatedTime;
    }

    public void setDictCreatedTime(Date dictCreatedTime) {
        this.dictCreatedTime = dictCreatedTime;
    }

    public Integer getDictPid() {
        return dictPid;
    }

    public void setDictPid(Integer dictPid) {
        this.dictPid = dictPid;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }
}