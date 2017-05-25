package com.lingang.api.domain.para;

public class SysParkPara extends BasePara {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8236002024577576252L;

	private String parkNameKeywords;

	private String parkAddrKeywords;

	private Integer parkId;// 园区ID

	private String parkName;// 园区名称

	private String parkAddress;// 详细地址

	private String parkFax;// 传真

	private String parkLink;// 网址

	private Integer parkState;

	private String parkContent;// 简介

	private Integer minImgId;

	private Integer maxImgId;

	private Integer regionId;
	
	private Integer parkType;
	private Integer objId;
	
	private String detailLink;

	public String getParkNameKeywords() {
		return parkNameKeywords;
	}

	public void setParkNameKeywords(String parkNameKeywords) {
		this.parkNameKeywords = parkNameKeywords;
	}

	public String getParkAddrKeywords() {
		return parkAddrKeywords;
	}

	public void setParkAddrKeywords(String parkAddrKeywords) {
		this.parkAddrKeywords = parkAddrKeywords;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkAddress() {
		return parkAddress;
	}

	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}

	public String getParkFax() {
		return parkFax;
	}

	public void setParkFax(String parkFax) {
		this.parkFax = parkFax;
	}

	public String getParkLink() {
		return parkLink;
	}

	public void setParkLink(String parkLink) {
		this.parkLink = parkLink;
	}

	public Integer getParkState() {
		return parkState;
	}

	public void setParkState(Integer parkState) {
		this.parkState = parkState;
	}

	public String getParkContent() {
		return parkContent;
	}

	public void setParkContent(String parkContent) {
		this.parkContent = parkContent;
	}

	public Integer getMinImgId() {
		return minImgId;
	}

	public void setMinImgId(Integer minImgId) {
		this.minImgId = minImgId;
	}

	public Integer getMaxImgId() {
		return maxImgId;
	}

	public void setMaxImgId(Integer maxImgId) {
		this.maxImgId = maxImgId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getParkType() {
		return parkType;
	}

	public void setParkType(Integer parkType) {
		this.parkType = parkType;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getDetailLink() {
		return detailLink;
	}

	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}

}
