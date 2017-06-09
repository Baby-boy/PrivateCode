package com.glanway.ctrlhr.entity.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TreeDeptVo {
	@JsonIgnore
	private Long id;
	private String label;
	private List<TreeDeptVo> children;
	
	

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return id.toString();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<TreeDeptVo> getChildren() {
		if(children==null || children.isEmpty()){
			return null;
		}
		return children;
	}
	public void setChildren(List<TreeDeptVo> children) {
		this.children = children;
	}
}
