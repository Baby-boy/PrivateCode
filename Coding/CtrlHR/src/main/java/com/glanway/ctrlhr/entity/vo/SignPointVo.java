package com.glanway.ctrlhr.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明 : 考勤点返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午2:30:08
 */
public class SignPointVo {

	private Long id;// ID

	private String name;// 考勤点名称

	private List<SignPointDeptVo> depts = new ArrayList<>();// 部门级联

	private List<SignPointDeviceVo> devices = new ArrayList<>();// 考勤设备级联

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SignPointDeptVo> getDepts() {
		return depts;
	}

	public void setDepts(List<SignPointDeptVo> depts) {
		this.depts = depts;
	}

	public List<SignPointDeviceVo> getDevices() {
		return devices;
	}

	public void setDevices(List<SignPointDeviceVo> devices) {
		this.devices = devices;
	}

}
