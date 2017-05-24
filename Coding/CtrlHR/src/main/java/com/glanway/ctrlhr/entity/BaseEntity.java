/*
 * Copyright (c) 2005, 2014 vacoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.glanway.ctrlhr.entity;

import java.io.Serializable;
import java.util.Date;

import org.ponly.webbase.entity.Auditable;
import org.ponly.webbase.entity.Persistable;

/**
 * @author vacoor
 */
public abstract class BaseEntity implements Persistable<Long>, Auditable<Long, Long>, Serializable {

	private static final long serialVersionUID = -1775772660674482854L;

	/**
	 * @Id
	 * @Column
	 * @ViewField text=ID position=-9999
	 */
	protected Long id; // ID

	/**
	 * @Column
	 * @ViewField text=Batch执行日 position=993
	 */
	protected Date batchDate;// Batch执行日

	/**
	 * @Column
	 * @ViewField text=创建程序ID position=994
	 */

	protected Long creProId;// 创建程序ID

	/**
	 * @Column
	 * @ViewField text=创建人ID position=995
	 */

	protected Long createdBy; // 创建人ID
	/**
	 * @Column
	 * @ViewField text=创建时间 position=996
	 */
	protected Date createdDate; // 创建时间

	/**
	 * @Column
	 * @ViewField text=更新程序ID position=997
	 */

	protected Long modProId;// 更新程序ID
	/**
	 * @Column
	 * @ViewField text=最后修改人ID position=998
	 */

	protected Long lastModifiedBy; // 最后修改人ID
	/**
	 * @Column
	 * @ViewField text=最后修改时间 position=999
	 */
	protected Date lastModifiedDate; // 最后修改时间

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	// @Override
	// public String getCreatedBy() {
	// return createdBy;
	// }
	//
	// @Override
	// public void setCreatedBy(String createdBy) {
	// this.createdBy = createdBy;
	// }

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public Long getModProId() {
		return modProId;
	}

	public void setModProId(Long modProId) {
		this.modProId = modProId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreProId() {
		return creProId;
	}

	public void setCreProId(Long creProId) {
		this.creProId = creProId;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	// @Override
	// public String getLastModifiedBy() {
	// return lastModifiedBy;
	// }
	//
	// @Override
	// public void setLastModifiedBy(String lastModifiedBy) {
	// this.lastModifiedBy = lastModifiedBy;
	// }

	@Override
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (null != other && this.getClass() == other.getClass()) {
			BaseEntity that = (BaseEntity) other;
			Serializable id = this.getId();
			Serializable thatId = that.getId();
			return null != id && null != thatId && (id == thatId || id.equals(thatId));
		} else {
			return false;
		}
	}

	public int hashCode() {
		byte result = 1;
		Long id = this.getId();
		return 31 * result + (id == null ? 0 : id.hashCode());
	}

	protected Object clone() throws CloneNotSupportedException {
		BaseEntity entity = BaseEntity.class.cast(super.clone());
		entity.setId(null);
		return entity;
	}
}
