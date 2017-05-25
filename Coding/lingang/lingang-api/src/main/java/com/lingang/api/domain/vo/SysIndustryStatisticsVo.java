package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysIndustryStatisticsVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4596626639742834970L;

	private Integer industryId;
	
	private String industryTitle;
	
	private Integer num;

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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}
