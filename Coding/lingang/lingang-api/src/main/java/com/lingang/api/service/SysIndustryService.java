package com.lingang.api.service;

import java.util.Map;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysIndustry;
import com.lingang.api.domain.entity.SysIndustryLabel;
import com.lingang.api.domain.entity.SysIndustryUmanager;
import com.lingang.api.domain.entity.SysParkIndustry;
import com.lingang.api.domain.para.SysIndustryPara;
import com.lingang.api.domain.pfvo.SysIndustryPfVo;
import com.lingang.api.domain.vo.SysIndustryStatisticsVo;
import com.lingang.api.domain.vo.SysIndustryVo;

public interface SysIndustryService {

	/**
	 * @Description: (产业集群列表)
	 * @param map
	 * @return 参数注释
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月4日 下午4:34:01
	 */
	ServiceResult<Page<SysIndustryVo>> selectSysIndustryList(Map<String, Object> map);

	/**
	 * @Description: (产业集群详情)
	 * @param industryId
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月4日 下午4:43:09
	 */
	ServiceResult<SysIndustryVo> selectByPrimaryKey(Integer industryId);

	/**
	 * @Description: (产业列表)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 上午10:31:37
	 */
	ServiceResult<Page<SysIndustry>> selectIndustryList(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: (查询产业集群列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午6:52:45
	 */
	ServiceResult<Page<SysIndustryPfVo>> selectIndustryPfVoPageList(SysIndustryPara para);

	/**
	 * @Description: (入驻企业统计_产业分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<Page<SysIndustryStatisticsVo>> selectIndustryStatisticsList(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: (更新产业集群详情_后台)
	 * @param industry
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午2:31:55
	 */
	ServiceResult<Object> updateIndustryDetails(SysIndustry industry);

	/**
	 * @Description: (添加产业集群_后台)
	 * @param industry
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午2:41:04
	 */
	ServiceResult<Object> addIndustryDetails(SysIndustry industry);

	/**
	 * @Description: (添加级联产业)
	 * @param parkIndustry
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午8:46:05
	 */
	ServiceResult<Object> addParkIndustryCascade(SysParkIndustry parkIndustry);

	/**
	 * @Description: (添加级联标签)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午7:18:35
	 */
	ServiceResult<Object> addLabelCascade(SysIndustryLabel industryLabel);

	/**
	 * @Description: (删除标签)
	 * @param objectId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:33:30
	 */
	ServiceResult<Object> delLab(Integer objectId, Integer labelId);
	
	/**
	* @Description: (删除客户经理)
	* @param partnerUmanagerId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:09:27
	 */
	ServiceResult<Object> delUman(Integer industryUmanagerId);
	
	/**
	* @Description: (添加客户经理)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:37:55
	 */
	ServiceResult<Object> addUManager(SysIndustryUmanager industryUmanager);
}
