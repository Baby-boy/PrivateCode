package com.lingang.api.service;

import java.util.List;
import java.util.Map;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysStation;
import com.lingang.api.domain.entity.SysStationIndustry;
import com.lingang.api.domain.entity.SysStationLabel;
import com.lingang.api.domain.entity.SysStationPark;
import com.lingang.api.domain.entity.SysStationUmanager;
import com.lingang.api.domain.para.SysStationPara;
import com.lingang.api.domain.pfvo.SysStationPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysStationVo;

public interface SysStationService {
	
	/**
	 * @Description: (入驻列表)
	 * @param map
	 * @return 参数注释 
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月5日 下午10:16:22
	 */
	ServiceResult<Page<SysStationVo>> selectSysStationAll(Map<String, Object> map);

	/**
	 * @Description: (入驻详情)
	 * @param stationId
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月6日 上午1:06:38
	 */
	ServiceResult<SysStationVo> selectByPrimaryKey(Integer stationId);

	/****************** 后台 ******************/

	/**
	 * @Description: 根据条件查询最新入驻信息
	 * @param map
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午1:32:33
	 */
	JsonResult selectAll(Map<String, Object> map);

	/**
	 * @Description: 添加入驻信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午1:32:41
	 */
	int insert(SysStation station);

	/**
	 * @Description: 修改入驻信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午1:32:47
	 */
	int updateByPrimaryKey(SysStation station);

	/**
	 * @Description: 删除入驻信息
	 * @param stationId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午1:32:52
	 */
	int deleteByPrimaryKey(Integer station_id);

	/**
	 * @Description: (查询入驻企业列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 上午12:55:52
	 */
	ServiceResult<Page<SysStationPfVo>> selectSysStationPfVoPageList(SysStationPara para);

	/**
	 * @Description: (入驻企业统计_新增企业)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<List<SysNewAddStatisticsVo>> selectNewStationStatisticsList();

	/**
	 * @Description: (入驻企业统计_多点布局统计)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<Object> selectmoreStationStatisticsCount();

	/**
	 * @Description: (修改入驻企业_后台)
	 * @param station
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:40:52
	 */
	ServiceResult<Object> updateStationDetails(SysStation station);

	/**
	 * @Description: (添加入驻企业_后台)
	 * @param station
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:45:17
	 */
	ServiceResult<Object> addStationDetails(SysStation station);

	/**
	 * @Description: (入驻企业_后台)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:15:46
	 */
	ServiceResult<List<SysLabelsVo>> selectStationlabel();

	/**
	 * @Description: (查询当前用户是否收藏对应的入驻企业详情)
	 * @param userId
	 * @param stationId
	 * @param collectType
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午7:40:50
	 */
	SysCollect selectSysCollect(Integer userId, Integer stationId, Integer collectType);

	/**
	 * @Description: (添加级联标签)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午7:18:35
	 */
	ServiceResult<Object> addLabelCascade(SysStationLabel stationLabel);

	/**
	 * @Description: (添加级联园区)
	 * @param parkService
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午8:46:05
	 */
	ServiceResult<Object> addStationCascade(SysStationPark stationPark);

	/**
	 * @Description: (添加级联产业)
	 * @param parkIndustry
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午8:46:05
	 */
	ServiceResult<Object> addStationIndustryCascade(SysStationIndustry stationIndustry);

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
	ServiceResult<Object> delPark(Integer objectId, Integer stationId);

	/**
	 * @Description: (删除产业集群)
	 * @param objectId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:33:30
	 */
	ServiceResult<Object> delIndustry(Integer objectId, Integer industryId);

	/**
	 * @Description: (入驻企业_创建单位_统计)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:15:46
	 */
	ServiceResult<List<SysCompanyVo>> selectStationCompany();

	int updateByPrimaryKeySelective(SysStation sysStation);

	/**
	 * @Description: (最新合作商)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 下午2:05:02
	 */
	ServiceResult<Page<SysStationVo>> selectTopStationVoPageList(Integer pageIndex, Integer pageSize);
	
	/**
	* @Description: (删除客户经理)
	* @param stationUmanagerId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:09:27
	 */
	ServiceResult<Object> delUman(Integer stationUmanagerId);
	
	/**
	* @Description: (添加客户经理)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:37:55
	 */
	ServiceResult<Object> addUManager(SysStationUmanager stationUmanager);
}
