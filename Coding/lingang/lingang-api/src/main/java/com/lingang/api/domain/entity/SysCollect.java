package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysCollect implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6993216092812714535L;

	private Integer collectId; // 1:收藏 , 0:未收藏

    private Integer userId;

    private Integer collectType;

    private Date createTime;

    private Integer objId;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollectType() {
        return collectType;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }
}