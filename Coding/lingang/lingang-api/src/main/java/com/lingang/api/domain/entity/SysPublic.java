package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysPublic implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 329731188767272593L;

	private Integer publicId;

    private Integer regionId;

    private Integer minImgId;

    private Integer parkId;

    private Integer maxImgId;

    private Integer sysRegionId;

    private Integer basicsId;

    private String publicTitle;

    private String publicAddress;

    private String publicFax;

    private String publicLink;

    private String publicCompany;

    private Date createTime;

    private Date updateTime;

    private Integer publicState;

    private String publicContent;

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getMinImgId() {
        return minImgId;
    }

    public void setMinImgId(Integer minImgId) {
        this.minImgId = minImgId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getMaxImgId() {
        return maxImgId;
    }

    public void setMaxImgId(Integer maxImgId) {
        this.maxImgId = maxImgId;
    }

    public Integer getSysRegionId() {
        return sysRegionId;
    }

    public void setSysRegionId(Integer sysRegionId) {
        this.sysRegionId = sysRegionId;
    }

    public Integer getBasicsId() {
        return basicsId;
    }

    public void setBasicsId(Integer basicsId) {
        this.basicsId = basicsId;
    }

    public String getPublicTitle() {
        return publicTitle;
    }

    public void setPublicTitle(String publicTitle) {
        this.publicTitle = publicTitle == null ? null : publicTitle.trim();
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress == null ? null : publicAddress.trim();
    }

    public String getPublicFax() {
        return publicFax;
    }

    public void setPublicFax(String publicFax) {
        this.publicFax = publicFax == null ? null : publicFax.trim();
    }

    public String getPublicLink() {
        return publicLink;
    }

    public void setPublicLink(String publicLink) {
        this.publicLink = publicLink == null ? null : publicLink.trim();
    }

    public String getPublicCompany() {
        return publicCompany;
    }

    public void setPublicCompany(String publicCompany) {
        this.publicCompany = publicCompany == null ? null : publicCompany.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPublicState() {
        return publicState;
    }

    public void setPublicState(Integer publicState) {
        this.publicState = publicState;
    }

    public String getPublicContent() {
        return publicContent;
    }

    public void setPublicContent(String publicContent) {
        this.publicContent = publicContent == null ? null : publicContent.trim();
    }
}