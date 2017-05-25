package com.lingang.api.domain.para;

public class SysIndustryPara extends BasePara {

	/**
	 * 
	 */
	private static final long serialVersionUID = 282833726816152374L;

	// 模糊查询关键字
	private String industryTitleKeywords;

	private Integer industryId;
	private String industryTitle;
	private String industryLink;
	private Integer industryState;
	private String industrySimple;
	private String industryContent;
	
	private Integer industryType;
	private Integer objId;
	
	private Integer minImgId;
	
	private Integer maxImgId;

	public String getIndustryTitleKeywords() {
		return industryTitleKeywords;
	}

	public void setIndustryTitleKeywords(String industryTitleKeywords) {
		this.industryTitleKeywords = industryTitleKeywords;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getIndustryTitle() {
		return industryTitle;
	}

	public void setIndustryTitle(String industryTitle) {
		this.industryTitle = industryTitle;
	}

	public String getIndustryLink() {
		return industryLink;
	}

	public void setIndustryLink(String industryLink) {
		this.industryLink = industryLink;
	}

	public Integer getIndustryState() {
		return industryState;
	}

	public void setIndustryState(Integer industryState) {
		this.industryState = industryState;
	}

	public String getIndustrySimple() {
		return industrySimple;
	}

	public void setIndustrySimple(String industrySimple) {
		this.industrySimple = industrySimple;
	}

	public String getIndustryContent() {
		return industryContent;
	}

	public void setIndustryContent(String industryContent) {
		this.industryContent = industryContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getIndustryType() {
		return industryType;
	}

	public void setIndustryType(Integer industryType) {
		this.industryType = industryType;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

}
