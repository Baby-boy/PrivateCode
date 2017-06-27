package com.glanway.ctrlhr.service.api;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.api.ApiDeptPara;
import com.glanway.ctrlhr.entity.para.api.ApiEmployeePara;
import com.glanway.ctrlhr.entity.para.api.ApiJobPara;
import com.glanway.ctrlhr.entity.vo.api.ApiDeptVo;
import com.glanway.ctrlhr.entity.vo.api.ApiEmployeeVo;
import com.glanway.ctrlhr.entity.vo.api.ApiJobVo;

/**
 * 说明 : 接口服务Service层
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午4:50:56
 */
public interface ApiService {

	/**
	 * 说明 : 接口服务-员工列表
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月13日 下午4:53:23
	 */
	public Page<ApiEmployeeVo> findEmployeeList(ApiEmployeePara para);

	/**
	 * 说明 : 接口服务-部门列表
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月13日 下午5:39:47
	 */
	public Page<ApiDeptVo> findDeptList(ApiDeptPara para);

	/**
	 * 说明 : 接口服务-职位列表
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月13日 下午5:56:42
	 */
	public Page<ApiJobVo> findJobList(ApiJobPara para);

}
