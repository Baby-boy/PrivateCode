package com.glanway.ctrlhr.entity.signPoint;

import com.glanway.ctrlhr.entity.BaseEntity;

public class SignPoint extends BaseEntity {

	private static final long serialVersionUID = 6278037720035137892L;

	private String name;// 考勤点名称

	private Integer state;// 状态(1:开启, 2:关闭)

	private String deleted;// 是否删除(0.否，1.是)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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