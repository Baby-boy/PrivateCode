package com.glanway.ctrlhr.entity.dept;

import java.util.List;

import com.glanway.ctrlhr.entity.vo.ChildDetpORGVO;

/**
 * 
 * @author 王晨
 * 说明 仅限用于部门下的 职位 管理的部门
 *
 */
public class ORGChildDetp  {
	
	private String id; //职位ID
	private String name; //职位名称
	 
	private List<ChildDetpORGVO> childDetpORGVOs; //编制管辖的自部门
	
	public List<ChildDetpORGVO> getChildDetpORGVOs() {
		return childDetpORGVOs;
	}
	public void setChildDetpORGVOs(List<ChildDetpORGVO> childDetpORGVOs) {
		this.childDetpORGVOs = childDetpORGVOs;
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
	

	
}