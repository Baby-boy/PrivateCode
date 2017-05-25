package com.lingang.api.domain.pfvo;

import com.lingang.api.domain.entity.SysManager;

public class SysManagerPfVo extends SysManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4852024052800366338L;

	private String imgPath;
	
	private Integer powerId;
	
	private String powerModularStr;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public String getPowerModularStr() {
		return powerModularStr;
	}

	public void setPowerModularStr(String powerModularStr) {
		this.powerModularStr = powerModularStr;
	}
}
