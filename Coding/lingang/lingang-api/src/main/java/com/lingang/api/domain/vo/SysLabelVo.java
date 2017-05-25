package com.lingang.api.domain.vo;

import com.lingang.api.domain.entity.SysLabel;

public class SysLabelVo extends SysLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4879592669347713630L;
	private String LabelTypeName;
	public String getLabelTypeName() {
		return LabelTypeName;
	}
	public void setLabelTypeName(String labelTypeName) {
		LabelTypeName = labelTypeName;
	}
	

}
