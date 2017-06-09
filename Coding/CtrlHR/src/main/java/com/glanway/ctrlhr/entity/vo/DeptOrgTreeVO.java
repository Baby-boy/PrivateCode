package com.glanway.ctrlhr.entity.vo;

import java.util.List;

/**部门一览数据返回
 * 
 * @author 王晨
 *
 */


public class DeptOrgTreeVO {
	
	private String type; //1部门 2职位 工作制
	
	private String id; //部门ID 或者职位ID
	
	private String name; //部门名称 或者职位名称
	
	private String orgId; //编制ID
	private String orgCount; //编制人数
	
	private String emplCount; //在职人数
	
	private String workSystemId; //工作制ID
	private String workSystem; //工作制
	
	private Integer jobGradeId; //级别 id越小 级别越大
	
	private String deptId ; //针对于type=2
	
	private List<DeptOrgTreeVO> child; //子节点可能是部门或者职位 

	
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getWorkSystemId() {
		return workSystemId;
	}

	public void setWorkSystemId(String workSystemId) {
		this.workSystemId = workSystemId;
	}

	public String getWorkSystem() {
		return workSystem;
	}

	public void setWorkSystem(String workSystem) {
		this.workSystem = workSystem;
	}

	public Integer getJobGradeId() {
		return jobGradeId;
	}

	public void setJobGradeId(Integer jobGradeId) {
		this.jobGradeId = jobGradeId;
	}

	public List<DeptOrgTreeVO> getChild() {
		return child;
	}

	public void setChild(List<DeptOrgTreeVO> child) {
		this.child = child;
	}
    
	
	
	
}
