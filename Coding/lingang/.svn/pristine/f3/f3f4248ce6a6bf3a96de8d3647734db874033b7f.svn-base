package com.lingang.api.service;

import java.util.List;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysBasics;
import com.lingang.api.domain.para.SysBasicsPara;
import com.lingang.api.domain.vo.SysBasicsVo;

public interface SysBasicsService {

	/**
	* @Description: ()
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午2:36:11
	 */
	ServiceResult<List<SysBasics>> selectSysBasics();

	/**
	* @Description: (查询出所有的基础类型列表)
	* @param para
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午2:38:04
	 */
	ServiceResult<Page<SysBasics>> selectSysBasicsPageList(SysBasicsPara para);

	/**
	* @Description: (修改修改基础类型的方法)
	* @param basics
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午2:44:23
	 */
	ServiceResult<SysBasics> updateSysBasics(SysBasics basics);

	/**
	* @Description: (添加基础设置方法)
	* @param basics
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月9日 下午5:39:43
	 */
	ServiceResult<SysBasics> addSysBasics(SysBasics basics);
	
	/**
	* @Description: (服务机构统计_类别分布)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月10日 上午11:02:13
	 */
	ServiceResult<List<SysBasicsVo>> selectTypeServiceStatisticsList();
	
	/**
	* @Description: (公共平台统计_类别分布)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月10日 上午11:02:13
	 */
	ServiceResult<List<SysBasicsVo>> selectTypePublicStatisticsList();
	
	/**
	* @Description: (合作层级/服务信息)
	* @param basicsType
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月10日 上午11:29:09
	 */
	ServiceResult<List<SysBasics>> selectBasicsListByBasicsType(Integer basicsType);

}
