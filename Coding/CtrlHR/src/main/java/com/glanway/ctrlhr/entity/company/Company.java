package com.glanway.ctrlhr.entity.company;

import com.glanway.ctrlhr.entity.BaseEntity;

public class Company extends BaseEntity {
	private static final long serialVersionUID = 7307981570166578051L;
	
	private String name; //公司名称
	private String simpleName; //公司简称
	private Integer attr; //公司属性（1.子公司；2.母公司）
	private String isOwnership; //是否有控股权(0:没有, 1:有)
	private Integer state; //状态(1:开启, 2:关闭)
	private Long parentId; //上级公司ID
	private String path; //存储所有祖先(包含当前节点)的路径
	private String pathNames; //存储所有祖先(包含当前节点)的路径名称
	private Integer depth; //存储节点深度
	private String isLeaf; //是否叶子节点(0.否，1.是)
	private String deleted; //是否删除(0.否，1.是)
	
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
	public String getIsOwnership() {
		return isOwnership;
	}
	public void setIsOwnership(String isOwnership) {
		this.isOwnership = isOwnership;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPathNames() {
		return pathNames;
	}
	public void setPathNames(String pathNames) {
		this.pathNames = pathNames;
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
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
