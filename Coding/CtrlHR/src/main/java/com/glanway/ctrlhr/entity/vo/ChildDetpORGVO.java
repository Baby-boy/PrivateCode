package com.glanway.ctrlhr.entity.vo;
/**
 * 说明 : 子部门对应编制
 */
public class ChildDetpORGVO {
	private String name ; //职位名称
	private String orgCount; //编制人数
	private String emplCount; //在职人数
	private String workSystem; //工作制
	
	private String workSystemId; //工作制ID
	private String deptId;//部门ID
	
	public String getWorkSystemId() {
		return workSystemId;
	}
	public void setWorkSystemId(String workSystemId) {
		this.workSystemId = workSystemId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getWorkSystem() {
		return workSystem;
	}
	public void setWorkSystem(String workSystem) {
		this.workSystem = workSystem;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgCount() {
		return orgCount;
	}
	public void setOrgCount(String orgCount) {
		this.orgCount = orgCount;
	}
	public String getEmplCount() {
		return emplCount;
	}
	public void setEmplCount(String emplCount) {
		this.emplCount = emplCount;
	}
	
}
