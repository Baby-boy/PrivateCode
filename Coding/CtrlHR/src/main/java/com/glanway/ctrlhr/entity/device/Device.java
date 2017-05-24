package com.glanway.ctrlhr.entity.device;

import java.util.Date;

import com.glanway.ctrlhr.entity.BaseEntity;

public class Device extends BaseEntity {

	private static final long serialVersionUID = -2065370428941206724L;

	private Long signPointId;// 考勤点的id

	private String name;// 设备名称

	private String sn;// 设备序列号

	private String ip;// IP地址

	private Date firstConnectionTime;// 第一次连接时间

	private Date lastConnectionTime;// 最后一次连接时间

	private Long totalPeople;// 设备上总人数

	private Long unsyncPeople;// 未同步人数

	private Integer state;// 状态(1:未使用, 2:已使用, 3:异常)

	private String deleted;// 是否删除(0.否，1.是)


	public Long getSignPointId() {
		return signPointId;
	}

	public void setSignPointId(Long signPointId) {
		this.signPointId = signPointId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getFirstConnectionTime() {
		return firstConnectionTime;
	}

	public void setFirstConnectionTime(Date firstConnectionTime) {
		this.firstConnectionTime = firstConnectionTime;
	}

	public Date getLastConnectionTime() {
		return lastConnectionTime;
	}

	public void setLastConnectionTime(Date lastConnectionTime) {
		this.lastConnectionTime = lastConnectionTime;
	}

	public Long getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Long totalPeople) {
		this.totalPeople = totalPeople;
	}

	public Long getUnsyncPeople() {
		return unsyncPeople;
	}

	public void setUnsyncPeople(Long unsyncPeople) {
		this.unsyncPeople = unsyncPeople;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}