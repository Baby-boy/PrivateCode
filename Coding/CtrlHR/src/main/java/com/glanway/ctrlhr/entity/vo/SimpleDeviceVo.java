package com.glanway.ctrlhr.entity.vo;

/**
 * 说明 : 设备精简列表接口返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午9:24:01
 */
public class SimpleDeviceVo {

	private Long id;// 设备id

	private String name;// 设备名称

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

}
