package com.glanway.ctrlhr.entity.para;

public class DevicePara extends BasePara {

	private String keyword;// 搜索关键字

	private Integer state;// 设备状态

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
