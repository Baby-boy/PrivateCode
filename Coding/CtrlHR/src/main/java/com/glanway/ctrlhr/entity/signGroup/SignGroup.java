package com.glanway.ctrlhr.entity.signGroup;

import com.glanway.ctrlhr.entity.BaseEntity;

public class SignGroup extends BaseEntity {

	private static final long serialVersionUID = 5764109766152716809L;

	private String name;// 考勤群组名称

	private Integer state;// 状态(1:开启, 2:关闭)

	private String remark;// 备注

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}