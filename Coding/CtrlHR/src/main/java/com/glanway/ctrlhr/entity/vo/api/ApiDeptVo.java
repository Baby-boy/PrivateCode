package com.glanway.ctrlhr.entity.vo.api;

/**
 * 说明 : 接口服务部门封装数据
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午5:34:45
 */
public class ApiDeptVo {

	private Long id;// 部门ID

	private String name;// 部门名称

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
