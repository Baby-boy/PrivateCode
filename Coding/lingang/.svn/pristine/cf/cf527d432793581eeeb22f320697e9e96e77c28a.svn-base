package com.lingang.api.service;

import java.util.List;
import java.util.Map;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysServiceLabel;
import com.lingang.api.domain.entity.SysServicePark;
import com.lingang.api.domain.entity.SysServiceUmanager;
import com.lingang.api.domain.entity.SysServiceWithBLOBs;
import com.lingang.api.domain.para.SysServicePara;
import com.lingang.api.domain.pfvo.SysServicePfVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysServiceVo;

public interface SysServiceService {

	/**
	 * @Description: (服务机构列表)
	 * @param map
	 * @return 参数注释 
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午6:33:44
	 */
	ServiceResult<Page<SysServiceVo>> selectServicePageList(Map<String, Object> map);

	/**
	 * @Description: (服务机构详情)
	 * @param serviceId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午8:27:28
	 */
	ServiceResult<SysServiceVo> selectServiceByServiceId(Integer serviceId);

	/**
	 * @Description: (查询服务机构列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午10:25:28
	 */
	ServiceResult<Page<SysServicePfVo>> selectServicePfVoPageList(SysServicePara para);

	/**
	 * @Description: (修改服务机构_后台)
	 * @param service
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:01:40
	 */
	ServiceResult<Object> updateServiceDetails(SysServiceWithBLOBs service);

	/**
	 * @Description: (添加服务机构_后台)
	 * @param service
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:06:51
	 */
	ServiceResult<Object> addServiceDetails(SysServiceWithBLOBs service);

	/**
	 * @Description: (服务机构统计_新增机构)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<List<SysNewAddStatisticsVo>> selectNewServiceStatisticsList();

	/**
	 * @Description: (查询当前用户是否收藏对应的服务机构详情)
	 * @param userId
	 * @param serviceId
	 * @param collectType
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午7:32:39
	 */
	SysCollect selectSysCollect(Integer userId, Integer serviceId, Integer collectType);

	/**
	 * @Description: (服务机构_后台)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:15:46
	 */
	ServiceResult<List<SysLabelsVo>> selectServicelabel();

	/**
	 * @Description: (添加级联标签)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午7:18:35
	 */
	ServiceResult<Object> addLabelCascade(SysServiceLabel serviceLabel);

	/**
	 * @Description: (添加级联产业)
	 * @param parkService
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午8:46:05
	 */
	ServiceResult<Object> addServiceCascade(SysServicePark servicePark);

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
	 * @Description: (删除园区)
	 * @param objectId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:33:30
	 */
	ServiceResult<Object> delPark(Integer objectId, Integer serviceId);
	
	/**
	* @Description: (删除客户经理)
	* @param serviceUmanagerId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:09:27
	 */
	ServiceResult<Object> delUman(Integer serviceUmanagerId);
	
	/**
	* @Description: (添加客户经理)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:37:55
	 */
	ServiceResult<Object> addUManager(SysServiceUmanager serviceUmanager);

}
