package com.lingang.api.service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysHome;

public interface SysHomeService {
//	/**
//	* @Description: (首页链接“已废弃”)
//	* @param pageIndex
//	* @param pageSize
//	* @return    
//	* @author gsh(15136390655@163.com)
//	* @date: 2016年12月6日 上午2:51:00
//	 */
//	ServiceResult<Page<SysHomeVo>> selectSysNewsAll(Integer pageIndex,Integer pageSize);

	/**
	* @Description: (首页链接)
	* @param userId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月7日 下午11:28:24
	 */
	ServiceResult<SysHome> selectSysHomeByUserId(Integer userId);
	
	/**
	* @Description: (首页链接调整顺序)
	* @param home
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月7日 下午11:58:26
	 */
	ServiceResult<SysHome> updateSysHome(SysHome home);
}
