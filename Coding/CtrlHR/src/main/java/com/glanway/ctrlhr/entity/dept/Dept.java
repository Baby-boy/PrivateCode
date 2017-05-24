package com.glanway.ctrlhr.entity.dept;

import com.glanway.ctrlhr.entity.BaseEntity;

public class Dept extends BaseEntity {

	private static final long serialVersionUID = -2269015194344660845L;

	private Long companyId;//公司id

    private String companyName;//公司名字

    private String name;//部门名称

    private String code;//部门代码

    private String duty;//部门职责

    private String isIndependent;//是否支持独立架构(0:否,1:是)

    private String hasSupervisePower;//监督权(0:否,1:是)

    private String supervised;//被监督(0:否,1:是)

    private Integer position;//排序

    private Integer state;//状态

    private Long parentId;//上级部门id

    private String path;//所有祖先(包含当前节点)的路径

    private String pathNames;//所有祖先(包含当前节点)的路径名称

    private Integer depth;//存储节点深度

    private String isLeaf;//是否叶子节点(0.否，1.是)

    private String deleted;//是否删除(0.否，1.是)


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getIsIndependent() {
        return isIndependent;
    }

    public void setIsIndependent(String isIndependent) {
        this.isIndependent = isIndependent == null ? null : isIndependent.trim();
    }

    public String getHasSupervisePower() {
        return hasSupervisePower;
    }

    public void setHasSupervisePower(String hasSupervisePower) {
        this.hasSupervisePower = hasSupervisePower == null ? null : hasSupervisePower.trim();
    }

    public String getSupervised() {
        return supervised;
    }

    public void setSupervised(String supervised) {
        this.supervised = supervised == null ? null : supervised.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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
        this.path = path == null ? null : path.trim();
    }

    public String getPathNames() {
        return pathNames;
    }

    public void setPathNames(String pathNames) {
        this.pathNames = pathNames == null ? null : pathNames.trim();
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
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

}