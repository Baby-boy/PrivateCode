package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysStation implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8645785341414755661L;

	private Integer stationId;

    private Integer regionId;

    private Integer imgId;

    private String stationTitle;

    private String stationSimple;

    private Integer isFictitious;

    private String regNumber;

    private String creditCode;

    private String stationCompany;

    private Date createTime;

    private Date updateTime;

    private Integer lossState;

    private Integer stationState;

    private String stationContent;
    
    private Integer topState;
    
    private Date topTime;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle == null ? null : stationTitle.trim();
    }

    public String getStationSimple() {
        return stationSimple;
    }

    public void setStationSimple(String stationSimple) {
        this.stationSimple = stationSimple == null ? null : stationSimple.trim();
    }

    public Integer getIsFictitious() {
        return isFictitious;
    }

    public void setIsFictitious(Integer isFictitious) {
        this.isFictitious = isFictitious;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber == null ? null : regNumber.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getStationCompany() {
        return stationCompany;
    }

    public void setStationCompany(String stationCompany) {
        this.stationCompany = stationCompany == null ? null : stationCompany.trim();
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


	public Integer getStationState() {
		return stationState;
	}

	public void setStationState(Integer stationState) {
		this.stationState = stationState;
	}

	public Integer getLossState() {
		return lossState;
	}

	public void setLossState(Integer lossState) {
		this.lossState = lossState;
	}

	public String getStationContent() {
		return stationContent;
	}

	public void setStationContent(String stationContent) {
		this.stationContent = stationContent;
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

}