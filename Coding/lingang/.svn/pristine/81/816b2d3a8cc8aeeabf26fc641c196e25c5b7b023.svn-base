package com.lingang.api.service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysAssort;
import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.para.CompanyPara;

public interface SysCompanyService {
	
	/**
	* @Description: (公司列表分页)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月30日 上午10:15:15
	 */
	ServiceResult<Page<SysCompany>> selectCompanyPageList(CompanyPara para);
	
	/**
	* @Description: (修改公司详情)
	* @param company
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月30日 上午11:22:48
	 */
	ServiceResult<SysCompany> updateCompanyDetails(SysCompany company);
	
	/**
	* @Description: (添加公司详情)
	* @param company
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月30日 上午11:22:48
	 */
	ServiceResult<SysCompany> addCompanyDetails(SysCompany company);

	/**
	* @Description: (添加公司关联信息)
	* @param assort
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月30日 下午5:57:52
	 */
	ServiceResult<SysAssort> addAssort(SysAssort assort);
	
	/**
	* @Description: (级联公司的删除)
	* @param assortType
	* @param objId
	* @param companyId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月3日 上午9:49:14
	 */
	ServiceResult<Object> delCompanyCascade(Integer assortType,Integer objId,Integer companyId);
}
