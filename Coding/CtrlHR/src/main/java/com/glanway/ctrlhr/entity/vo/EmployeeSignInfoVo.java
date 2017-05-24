package com.glanway.ctrlhr.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明 : 员工考勤信息采集接口返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月24日 下午2:44:39
 */
public class EmployeeSignInfoVo {

	private Long id;// 员工ID

	private String code;// 员工代码

	private String name;// 员工姓名

	private Long hasFinger;// 指纹信息(0:未采集,1:采集)

	private Long hasFace;// 脸纹信息(0:未采集,1:采集)

	private List<EmployeeSignPointVo> signPoints = new ArrayList<>();

	private List<EmployeeSignGroupVo> signGroups = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getHasFinger() {
		if (null == this.hasFinger) {
			this.hasFinger = 0L;
		} else {
			this.hasFinger = 1L;
		}
		return hasFinger;
	}

	public void setHasFinger(Long hasFinger) {
		this.hasFinger = hasFinger;
	}

	public Long getHasFace() {
		if (null == this.hasFace) {
			this.hasFace = 0L;
		} else {
			this.hasFace = 1L;
		}
		return hasFace;
	}

	public void setHasFace(Long hasFace) {
		this.hasFace = hasFace;
	}

	public List<EmployeeSignPointVo> getSignPoints() {
		return signPoints;
	}

	public void setSignPoints(List<EmployeeSignPointVo> signPoints) {
		this.signPoints = signPoints;
	}

	public List<EmployeeSignGroupVo> getSignGroups() {
		return signGroups;
	}

	public void setSignGroups(List<EmployeeSignGroupVo> signGroups) {
		this.signGroups = signGroups;
	}

}
