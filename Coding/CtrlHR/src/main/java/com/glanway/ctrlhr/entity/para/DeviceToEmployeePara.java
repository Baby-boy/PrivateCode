package com.glanway.ctrlhr.entity.para;

public class DeviceToEmployeePara extends BasePara {

	private String keyword;// 搜索关键字

	private String sn;// 设备ID

	private String gatherMsgStates;// 员工信息采集状态

	private String[] gatherMsgStateList;// 员工信息采集数组

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getGatherMsgStates() {
		return gatherMsgStates;
	}

	public void setGatherMsgStates(String gatherMsgStates) {
		this.gatherMsgStates = gatherMsgStates;
	}

	public String[] getGatherMsgStateList() {
		return gatherMsgStateList;
	}

	public void setGatherMsgStateList(String[] gatherMsgStateList) {
		this.gatherMsgStateList = gatherMsgStateList;
	}

}
