package com.glanway.ctrlhr.entity.signPoint;

import com.glanway.ctrlhr.entity.BaseEntity;

public class SignPointDept extends BaseEntity {

	private static final long serialVersionUID = -5770142423590479380L;

	private Long signPointId;// 考勤点ID

	private Long deptId;// 部门ID

	private String deleted;// 是否删除(0.否，1.是)

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