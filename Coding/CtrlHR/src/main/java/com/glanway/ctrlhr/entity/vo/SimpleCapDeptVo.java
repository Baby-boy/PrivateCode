package com.glanway.ctrlhr.entity.vo;

public class SimpleCapDeptVo {
	private Long id;
	private String name;// 部门名称
	private String code;// 部门代码
	private Integer depth;// 存储节点深度
	private String isLeaf;// 是否叶子节点(0.否，1.是)

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
