package com.yd.dby.app.entity;

public class YdBrand {
    private Integer brandId;

    private String brandName;

    private String brandInitial;

    private String brandClass;

    private String brandPic;

    private Byte brandSort;

    private Boolean brandRecommend;

    private Integer storeId;

    private Boolean brandApply;

    private Integer classId;

    private Boolean showType;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandInitial() {
        return brandInitial;
    }

    public void setBrandInitial(String brandInitial) {
        this.brandInitial = brandInitial == null ? null : brandInitial.trim();
    }

    public String getBrandClass() {
        return brandClass;
    }

    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass == null ? null : brandClass.trim();
    }

    public String getBrandPic() {
        return brandPic;
    }

    public void setBrandPic(String brandPic) {
        this.brandPic = brandPic == null ? null : brandPic.trim();
    }

    public Byte getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(Byte brandSort) {
        this.brandSort = brandSort;
    }

    public Boolean getBrandRecommend() {
        return brandRecommend;
    }

    public void setBrandRecommend(Boolean brandRecommend) {
        this.brandRecommend = brandRecommend;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Boolean getBrandApply() {
        return brandApply;
    }

    public void setBrandApply(Boolean brandApply) {
        this.brandApply = brandApply;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Boolean getShowType() {
        return showType;
    }

    public void setShowType(Boolean showType) {
        this.showType = showType;
    }
}