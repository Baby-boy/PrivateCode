package com.lingang.api.service;

import java.util.List;
import java.util.Map;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysFile;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysParkLabel;
import com.lingang.api.domain.entity.SysParkUmanager;
import com.lingang.api.domain.entity.SysWay;
import com.lingang.api.domain.para.SysParkPara;
import com.lingang.api.domain.pfvo.SysParkPfVo;
import com.lingang.api.domain.vo.SysFileVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.domain.vo.SysParkVo;

public interface SysParkService {

	
	/**
	 * @Description: (查询产业园区列表)
	 * @param map
	 * @return 参数注释 
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月4日 上午10:16:48
	 */
	ServiceResult<Page<SysParkVo>> selectSysParkVoPageListByRegionIdAndIndustryIds(Map<String, Object> map);

	/**
	 * @Description: (查询产业园区详情)
	 * @param parkId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 上午10:03:44
	 */
	ServiceResult<SysParkVo> selectSysParkDetailsByStationId(Integer parkId);

	/**
	 * @Description: (宣传册/视频 点击弹窗)
	 * @param fileId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午9:56:58
	 */
	ServiceResult<SysFileVo> selectClickPopup(Integer fileId, Integer userId);

	/**
	 * @Description: (查询产业园区列表_后台)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @param para
	 * @date: 2016年12月8日 下午12:58:43
	 */
	ServiceResult<Page<SysParkPfVo>> selectSysParkPfVoPageList(SysParkPara para);

	/**
	 * @Description: (修改园区信息)
	 * @param park
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 上午2:34:01
	 */
	ServiceResult<Object> updateParkDetails(SysPark park);

	/**
	 * @Description: (添加园区信息)
	 * @param park
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 上午2:34:01
	 */
	ServiceResult<Object> addParkDetails(SysPark park);

	/**
	 * @Description: (入驻企业统计_园区分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<Page<SysParkStatisticsVo>> selectParkStationStatisticsList(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: (服务机构统计_园区分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<Page<SysParkStatisticsVo>> selectParkServiceStatisticsList(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: (产业园区统计_新增园区)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<List<SysNewAddStatisticsVo>> selectNewParkStatisticsList();

	/**
	 * @Description: (产业集群统计_园区分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<Page<SysParkStatisticsVo>> selectParkIndustryStatisticsList(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: (公共平台统计_园区分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<Page<SysParkStatisticsVo>> selectParkPublicStatisticsList(Integer pageIndex, Integer pageSize);

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
	 * @Description: (删除覆盖产业)
	 * @param objectId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:33:30
	 */
	ServiceResult<Object> delIndustry(Integer objectId, Integer industryId);

	/**
	 * @Description: (删除宣传册/宣传片)
	 * @param objectId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:33:30
	 */
	ServiceResult<Object> delFile(Integer fileId);

	/**
	 * @Description: (添加级联标签)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午7:18:35
	 */
	ServiceResult<Object> addLabelCascade(SysParkLabel parkLabel);

	/**
	 * @Description: (查询当前用户是否收藏对应的产业园区详情)
	 * @param parkId
	 * @param userId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午6:48:03
	 */
	SysCollect selectSysCollect(Integer userId, Integer collectType, Integer parkId);

	/**
	 * @Description: (获取园区信息，下拉列表用)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 上午9:59:19
	 */
	ServiceResult<Object> selectParkAllList();

	/**
	 * @Description: (添加联系电话)
	 * @param sysWay
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月22日 下午3:16:17
	 */
	ServiceResult<SysWay> addWay(SysWay sysWay);

	/**
	 * @Description: (删除联系电话)
	 * @param wayId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月22日 下午3:40:45
	 */
	ServiceResult<Object> delWay(Integer wayId);
	
	/**
	* @Description: (修改宣传片封面)
	* @param file
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月9日 下午7:04:30
	 */
	ServiceResult<Object> uploadFileParkVideoImage(SysFile file);
	
	
	/**
	* @Description: (删除客户经理)
	* @param partnerUmanagerId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:09:27
	 */
	ServiceResult<Object> delUman(Integer parkUmanagerId);
	
	
	/**
	* @Description: (添加客户经理)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:37:55
	 */
	ServiceResult<Object> addUManager(SysParkUmanager parkUmanager);
}
