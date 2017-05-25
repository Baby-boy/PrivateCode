package com.lingang.api.domain.pfvo;

import com.lingang.api.domain.entity.SysStation;

public class SysStationPfVo extends SysStation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1682653659869385821L;

	private String regionName;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
