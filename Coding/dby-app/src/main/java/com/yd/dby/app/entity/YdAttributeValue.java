package com.yd.dby.app.entity;

public class YdAttributeValue {
    private Integer attrValueId;

    private String attrValueName;

    private Integer attrId;

    private Integer typeId;

    private Boolean attrValueSort;

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName == null ? null : attrValueName.trim();
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Boolean getAttrValueSort() {
        return attrValueSort;
    }

    public void setAttrValueSort(Boolean attrValueSort) {
        this.attrValueSort = attrValueSort;
    }
}