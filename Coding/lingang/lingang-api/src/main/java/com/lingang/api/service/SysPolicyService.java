package com.lingang.api.service;

import java.util.Map;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysPolicy;
import com.lingang.api.domain.para.SysPolicyPara;
import com.lingang.api.domain.vo.SysPolicyVo;

public interface SysPolicyService {

	/**
	 * @Description: (查询政策列表)
	 * @param map
	 * @return 参数注释
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午3:33:11
	 */
	ServiceResult<Page<SysPolicy>> selectSysPolicyAll(Map<String, Object> map);

	/**
	 * @Description: (查询政策详情)
	 * @param 政策ID
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午3:34:57
	 */
	ServiceResult<SysPolicyVo> selectByPrimaryKey(Integer policyId);

	/************ 后台 *****************/

	/**
	 * @Description: 根据条件查询对应的政策信息
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午2:47:26
	 */
	JsonResult queryAll(SysPolicyPara para);

	/**
	 * @Description:查询指定的政策信息
	 * @param policy_id
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午2:47:57
	 */
	SysPolicy queryById(Integer policy_id);

	/**
	 * @Description:添加新的政策信息
	 * @param policy
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午2:48:19
	 */
	ServiceResult<Object> insert(SysPolicy policy);

	/**
	 * @Description: (修改政策详情)
	 * @param policy
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月13日 下午5:38:15
	 */
	ServiceResult<Object> updatePolicyDetails(SysPolicy policy);

	/**
	 * @Description: 删除指定的政策信息
	 * @param policy_id
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午2:49:00
	 */
	int del(Integer policy_id);
}
