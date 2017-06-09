package com.glanway.ctrlhr.entity.vo;

public class SimpleCompanyVo {
	private Long id; //ID
	private String name; //公司名称
	private String simpleName; //公司简称
	private Integer attr; //公司属性（1.子公司；2.母公司）
	private Integer depth; //存储节点深度
	private String isLeaf; //是否叶子节点(0.否，1.是)
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
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public Integer getAttr() {
		return attr;
	}
	public void setAttr(Integer attr) {
		this.attr = attr;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
