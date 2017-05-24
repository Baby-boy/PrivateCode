package com.glanway.ctrlhr.entity.vo;

/**
 * 说明 : 考勤群组精简列表接口返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月24日 上午9:09:25
 */
public class SimpleSignGroupVo {

	private Long id;// 考勤群组id

	private String name;// 考勤群组名称

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
