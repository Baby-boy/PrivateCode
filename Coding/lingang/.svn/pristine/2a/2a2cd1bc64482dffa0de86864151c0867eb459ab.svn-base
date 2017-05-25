package com.lingang.api.service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.para.SysPartnerTypePara;

public interface SysPartnerTypeService {

	public ServiceResult<Page<SysPartnerType>> selectPartnerTypeList(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: (获取合作类型列表)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午3:49:21
	 */
	public ServiceResult<Page<SysPartnerType>> selectPartnerTypePageList(SysPartnerTypePara para);

	/**
	 * @Description: (修改合作类型)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午3:49:05
	 */
	public ServiceResult<Object> updatePartnerType(SysPartnerType para);

	/**
	 * @Description: (添加合作类型)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午3:59:54
	 */
	public ServiceResult<Object> addPartnerType(SysPartnerType para);
}
