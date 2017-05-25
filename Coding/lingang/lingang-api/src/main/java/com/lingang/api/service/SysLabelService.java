package com.lingang.api.service;

import java.util.Map;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.para.SysLabelPara;

public interface SysLabelService {
	/**
	* @Description: (标签：入驻企业筛选)
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月7日 上午11:28:06
	 */
	ServiceResult<Map<String,Object>> selectSysLabelByType();

	/**
	* @Description: (标签：查询标签的列表)
	* @param para
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午3:12:35
	 */
	ServiceResult<Page<SysLabel>> selectSysLabelPageList(SysLabelPara para);

	/**
	* @Description: (修改标签)
	* @param label
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月9日 下午7:25:30
	 */
	ServiceResult<SysLabel> updateSysLabel(SysLabel label);

	/**
	* @Description: (添加标签)
	* @param label
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月10日 上午11:49:49
	 */
	ServiceResult<SysLabel> sysLabelAdd(SysLabel label);

	

}
