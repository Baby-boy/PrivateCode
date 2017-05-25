package com.lingang.api.domain.para;

public class SysLabelPara extends BasePara {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2382794666743670425L;

	private String labelType;
	
	private Integer labelState;
	
	private Integer objId;
	
	private Integer labelId;


	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public Integer getLabelState() {
		return labelState;
	}

	public void setLabelState(Integer labelState) {
		this.labelState = labelState;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

}
