/*
 * Copyright (c) 2005, 2014 vacoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.glanway.hr.app.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共字段.
 *
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1775772660674482854L;

    protected Long id; // ID

    protected Date batchDate;// Batch执行日

    protected Long creProId;// 创建程序ID

    protected Long createdBy; // 创建人ID

    protected Date createdDate; // 创建时间

    protected Long modProId; // 更新程序ID

    protected Long lastModifiedBy; // 最后修改人ID

    protected Date lastModifiedDate; // 最后修改时间

    private String deleted;// 是否删除(0:否, 1:是)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public Long getCreProId() {
        return creProId;
    }

    public void setCreProId(Long creProId) {
        this.creProId = creProId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModProId() {
        return modProId;
    }

    public void setModProId(Long modProId) {
        this.modProId = modProId;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
