package com.glanway.ctrlhr.entity.signGroup;

import com.glanway.ctrlhr.entity.BaseEntity;

public class SignGroupSignPoint extends BaseEntity {

	private static final long serialVersionUID = 1167771858493545038L;

	private Long signGroupId;// 考勤群组ID

	private Integer type;// 类型(1:考勤点, 2:组织架构)

	private Long signPointId;// 考勤点ID

	private Long deptId;// 部门ID

	private String deleted;// 是否删除(0.否，1.是)

	public Long getSignGroupId() {
		return signGroupId;
	}

	public void setSignGroupId(Long signGroupId) {
		this.signGroupId = signGroupId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getSignPointId() {
		return signPointId;
	}

	public void setSignPointId(Long signPointId) {
		this.signPointId = signPointId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}