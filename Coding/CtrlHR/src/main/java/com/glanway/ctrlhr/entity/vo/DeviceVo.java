package com.glanway.ctrlhr.entity.vo;

/**
 * 说明 : 设备返回数据封装
 * 
 * @author gwn
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午6:04:30
 */
public class DeviceVo {

	private Long id;// id

	private String deviceName;// 设备名称

	private String sn;// 设备序列号

	private Integer state;// 状态

	private Integer syncState;// 同步状态

	private Long totalPeople;// 设备总人数

	private Long unsyncPeople;// 未同步人数

	private String signPointName;// 考勤点的名字

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceName() {
		if (null == deviceName) {
			this.deviceName = "";
		}
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSyncState() {
		return syncState;
	}

	public void setSyncState(Integer syncState) {
		this.syncState = syncState;
	}

	public Long getTotalPeople() {
		if (null == totalPeople) {
			this.totalPeople = 0L;
		}
		return totalPeople;
	}

	public void setTotalPeople(Long totalPeople) {
		this.totalPeople = totalPeople;
	}

	public Long getUnsyncPeople() {
		if (null == unsyncPeople) {
			this.unsyncPeople = 0L;
		}
		return unsyncPeople;
	}

	public void setUnsyncPeople(Long unsyncPeople) {
		this.unsyncPeople = unsyncPeople;
	}

	public String getSignPointName() {
		if (null == signPointName) {
			this.signPointName = "";
		}
		return signPointName;
	}

	public void setSignPointName(String signPointName) {
		this.signPointName = signPointName;
	}

}
