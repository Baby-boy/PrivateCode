package com.lingang.api.domain.pfvo;

import com.lingang.api.domain.entity.SysPublic;

public class SysPublicPfVo extends SysPublic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3217167050750325670L;

	private String parkName;
	private String regionName;
	private String basicsName;

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getBasicsName() {
		return basicsName;
	}

	public void setBasicsName(String basicsName) {
		this.basicsName = basicsName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
