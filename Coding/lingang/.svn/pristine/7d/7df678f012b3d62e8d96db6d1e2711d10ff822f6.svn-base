package com.lingang.api.service;

import java.util.List;
import java.util.Map;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysPublic;
import com.lingang.api.domain.entity.SysPublicLabel;
import com.lingang.api.domain.entity.SysPublicUmanager;
import com.lingang.api.domain.para.SysPublicPara;
import com.lingang.api.domain.pfvo.SysPublicPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysPublicVo;

public interface SysPublicService {

	/**
	 * @Description: (查询公共平台列表)
	 * @param map
	 * @return 参数注释 
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午2:58:03
	 */
	public ServiceResult<Page<SysPublicVo>> selectSysPublicVoPageListByRegionIdAndBasicsId(Map<String, Object> map);

	/**
	 * @Description: (查询公共平台详情)
	 * @param publicId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午5:01:04
	 */
	public ServiceResult<SysPublicVo> selectSysPublicDetailsByPublicId(Integer publicId);

	/**
	 * @Description: (查询公共平台列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 上午2:47:53
	 */
	public ServiceResult<Page<SysPublicPfVo>> selectSysPublicPfVoPageList(SysPublicPara para);

	/**
	 * @Description: (更新公共平台信息_后台)
	 * @param sysPublic
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午3:27:53
	 */
	public ServiceResult<Object> updatePublicDetails(SysPublic sysPublic);

	/**
	 * @Description: (添加公共平台_后台)
	 * @param sysPublic
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午3:33:13
	 */
	public ServiceResult<Object> addPublicDetails(SysPublic sysPublic);

	/**
	 * @Description: (公共平台统计_新增企业)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:15:46
	 */
	ServiceResult<List<SysNewAddStatisticsVo>> selectNewPublicStatisticsList();

	/**
	 * @Description: (查询公共平台详情_后台)
	 * @param publicId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午7:57:57
	 */
	public ServiceResult<SysPublicVo> selectSysPublicDetails(Integer publicId);

	/**
	 * @Description: (查询当前用户是否收藏对应的公共平台详情)
	 * @param userId
	 * @param publicId
	 * @param collectType
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午7:47:29
	 */
	public SysCollect selectSysCollect(Integer userId, Integer publicId, Integer collectType);

	/**
	 * @Description: (添加级联标签)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午7:18:35
	 */
	ServiceResult<Object> addLabelCascade(SysPublicLabel publicLabel);

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
	ServiceResult<Object> delPark(Integer publicId);

	/**
	 * @Description: (公共平台_创建单位_统计)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:15:46
	 */
	ServiceResult<List<SysCompanyVo>> selectPublicCompany();
	
	/**
	* @Description: (删除客户经理)
	* @param publicUmanagerId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:09:27
	 */
	ServiceResult<Object> delUman(Integer publicUmanagerId);
	
	/**
	* @Description: (添加客户经理)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:37:55
	 */
	ServiceResult<Object> addUManager(SysPublicUmanager publicUmanager);
}
