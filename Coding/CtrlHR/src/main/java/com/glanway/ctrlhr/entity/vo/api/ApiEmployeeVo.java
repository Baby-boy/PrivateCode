package com.glanway.ctrlhr.entity.vo.api;

/**
 * 说明 : 接口员工封装
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午4:48:27
 */
public class ApiEmployeeVo {

	private Long id;// 员工ID

	private String name;// 员工名称

	private String code;// 职员代码

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
