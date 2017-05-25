package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysIndustryLabel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8299107846066439441L;

	private Integer industryLabel;

    private Integer industryId;

    private Integer labelId;

    public Integer getIndustryLabel() {
        return industryLabel;
    }

    public void setIndustryLabel(Integer industryLabel) {
        this.industryLabel = industryLabel;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}