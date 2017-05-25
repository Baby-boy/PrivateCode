package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysPartner implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2057153971199194985L;

	private Integer partnerId;

    private Integer typeId;

    private Integer imgId;

    private Integer logoImgId;

    private Integer basicsId;

    private String partnerName;

    private String partnerSimple;

    private String partnerCompany;

    private Date createTime;

    private Date updateTime;

    private String partnerState;

    private String partnerContent;
    
    private Integer topState;
    
    private Date topTime;
    
    private Date signTime;

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getLogoImgId() {
        return logoImgId;
    }

    public void setLogoImgId(Integer logoImgId) {
        this.logoImgId = logoImgId;
    }

    public Integer getBasicsId() {
        return basicsId;
    }

    public void setBasicsId(Integer basicsId) {
        this.basicsId = basicsId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getPartnerSimple() {
        return partnerSimple;
    }

    public void setPartnerSimple(String partnerSimple) {
        this.partnerSimple = partnerSimple == null ? null : partnerSimple.trim();
    }

    public String getPartnerCompany() {
        return partnerCompany;
    }

    public void setPartnerCompany(String partnerCompany) {
        this.partnerCompany = partnerCompany == null ? null : partnerCompany.trim();
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

    public String getPartnerState() {
        return partnerState;
    }

    public void setPartnerState(String partnerState) {
        this.partnerState = partnerState == null ? null : partnerState.trim();
    }

    public String getPartnerContent() {
        return partnerContent;
    }

    public void setPartnerContent(String partnerContent) {
        this.partnerContent = partnerContent == null ? null : partnerContent.trim();
    }

	public Integer getTopState() {
		return topState;
	}

	public void setTopState(Integer topState) {
		this.topState = topState;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
    
}