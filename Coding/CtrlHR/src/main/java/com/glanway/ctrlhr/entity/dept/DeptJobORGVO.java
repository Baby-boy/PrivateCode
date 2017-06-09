package com.glanway.ctrlhr.entity.dept;

import java.util.List;

/**
 * 
 * @author 王晨
 * 说明 仅限用于部门下的 职位  编制人数 在职人数
 *
 */
public class DeptJobORGVO  {

	private static final long serialVersionUID = -2269015194344660845L;
	
	private String jobId; //职位ID
	private String jobName; //职位名称
	
	private String orgId; //编制ID
	private String orgCount; //编制人数
	
	private String emplCount; //在职人数
	
	private String workSystemId; //工作制ID
	private String workSystem; //工作制
	
	private String deptId;//部门ID
	
	private Integer jobGradeId; //级别 id越小 级别越大
	
	private List<ORGChildDetp> orgChildDetps; //管辖的部门
	
	private List<DeptJobORGVO> deptJobORGVOs; //下级职位 仅限 部门一览使用
	
	
	
	
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

	public List<DeptJobORGVO> getDeptJobORGVOs() {
		return deptJobORGVOs;
	}

	public void setDeptJobORGVOs(List<DeptJobORGVO> deptJobORGVOs) {
		this.deptJobORGVOs = deptJobORGVOs;
	}

	public Integer getJobGradeId() {
		return jobGradeId;
	}

	public void setJobGradeId(Integer jobGradeId) {
		this.jobGradeId = jobGradeId;
	}

	public String getWorkSystem() {
		return workSystem;
	}

	public void setWorkSystem(String workSystem) {
		this.workSystem = workSystem;
	}

	
	

	public List<ORGChildDetp> getOrgChildDetps() {
		return orgChildDetps;
	}

	public void setOrgChildDetps(List<ORGChildDetp> orgChildDetps) {
		this.orgChildDetps = orgChildDetps;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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
	
}