package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysLabelsVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3798020059434976166L;
	/**
	 * 
	 */
	private  Integer  labelId;
	
	private  String  labelName;
	 
	private  Integer  countLabel;

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Integer getCountLabel() {
		return countLabel;
	}

	public void setCountLabel(Integer countLabel) {
		this.countLabel = countLabel;
	}






}
