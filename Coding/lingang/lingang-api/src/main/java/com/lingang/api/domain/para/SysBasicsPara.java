package com.lingang.api.domain.para;

public class SysBasicsPara extends BasePara{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6807932863324540538L;
	
	//基础设置名称
	private Integer basicsType;
	
	//设置基础状态
	private Integer basicsState;

	public Integer getBasicsType() {
		return basicsType;
	}

	public void setBasicsType(Integer basicsType) {
		this.basicsType = basicsType;
	}

	public Integer getBasicsState() {
		return basicsState;
	}

	public void setBasicsState(Integer basicsState) {
		this.basicsState = basicsState;
	}
	
	
}
