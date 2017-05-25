package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: (补充实体,实现合作伙伴/服务机构/入驻企业/公共平台四个模块可能会出现多家公司共同维护其中一个模块的现象)
 * @Author: fqh (qihao.fu@outlook.com)
 * @date:2016年12月15日 下午2:56:53
 * @Version:1.0
 */
public class SysAssort implements Serializable {

	private static final long serialVersionUID = 528808707026760703L;

	private Integer assortId;

	private Integer companyId;

	private Integer assortType;

	private Date createTime;

	private Integer objId;
	
	private Date signTime;

	public Integer getAssortId() {
		return assortId;
	}

	public void setAssortId(Integer assortId) {
		this.assortId = assortId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getAssortType() {
		return assortType;
	}

	public void setAssortType(Integer assortType) {
		this.assortType = assortType;
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

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

}
