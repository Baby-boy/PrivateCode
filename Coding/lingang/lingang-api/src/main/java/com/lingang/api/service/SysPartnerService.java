package com.lingang.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysPartner;
import com.lingang.api.domain.entity.SysPartnerLabel;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.entity.SysPartnerUmanager;
import com.lingang.api.domain.para.SysPartnerPara;
import com.lingang.api.domain.pfvo.SysPartnerPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerBasicsStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerTypeStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerVo;

public interface SysPartnerService {

	/**
	 * @Description: (合作伙伴列表)
	 * @param map
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午12:23:11
	 */
	ServiceResult<Page<SysPartnerVo>> selectPartnerPageList(Map<String, Object> map);

	/**
	 * @Description: (合作伙伴详情)
	 * @param partnerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午2:48:45
	 */
	ServiceResult<SysPartnerVo> selectPartnerVoByPartnerId(Integer partnerId);

	/**
	 * @Description: (查询合作伙伴列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午4:12:46
	 */
	ServiceResult<Page<SysPartnerPfVo>> selectSysPartnerPfVoPageList(SysPartnerPara para);

	/****** 后台 ******/

	/**
	 * @Description:查询指定条件合作伙伴信息
	 * @param map
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 上午2:10:51
	 */
	ServiceResult<Page<SysPartnerPfVo>> queryAllByPage(HashMap<String, Object> map);

	/**
	 * @Description: 查询指定的合作信息
	 * @param partnerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午10:59:48
	 */
	SysPartner selectByPrimaryKey(Integer partnerId);

	/**
	 * @Description:删除指定合作信息
	 * @param partnerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午11:13:45
	 */
	int deleteByPrimaryKey(Integer partnerId);

	/**
	 * @Description: 添加合作信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午11:14:03
	 */
	int insert(SysPartner record);

	/**
	 * @Description:添加合作信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午11:15:01
	 */
	int insertSelective(SysPartner record);

	/**
	 * @Description:修改合作信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午11:15:21
	 */
	int updateByPrimaryKeySelective(SysPartner record);

	/**
	 * @Description:修改合作信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午11:15:43
	 */
	int updateByPrimaryKeyWithBLOBs(SysPartner record);

	/**
	 * @Description:修改合作信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 上午11:16:16
	 */
	int updateByPrimaryKey(SysPartner record);

	/**
	 * @Description: (修改合作伙伴信息)
	 * @param partner
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 下午8:44:29
	 */
	ServiceResult<Object> updatePartnerDetails(SysPartner partner);

	/**
	 * @Description: (添加合作伙伴)
	 * @param partner
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 下午9:16:59
	 */
	ServiceResult<Object> addPartnerDetails(SysPartner partner);

	/**
	 * 合作伙伴-类型分布
	 * 
	 * @param map
	 * @return
	 */
	ServiceResult<List<SysPartnerTypeStatisticsVo>> selectPartnerType();

	/**
	 * 合作伙伴-层级分布
	 * 
	 * @param map
	 * @return
	 */
	ServiceResult<List<SysPartnerBasicsStatisticsVo>> selectPartnerLevel();

	/**
	 * 合作伙伴-新增机构
	 * 
	 * @param map
	 * @return
	 */
	ServiceResult<List<SysNewAddStatisticsVo>> selectNewCounts();

	/**
	 * @Description: (查询所有合作类型_后台)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 上午10:15:15
	 */
	ServiceResult<List<SysPartnerType>> selectSysPartnerType();

	/**
	 * @Description: (查询当前用户是否收藏对应的合作伙伴详情)
	 * @param userId
	 * @param partnerId
	 * @param collectType
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午7:12:53
	 */
	SysCollect selectSysCollect(Integer userId, Integer partnerId, Integer collectType);

	/**
	 * @Description: (添加级联标签)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午7:18:35
	 */
	ServiceResult<Object> addLabelCascade(SysPartnerLabel partnerLabel);

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
	 * @Description: (合作伙伴_统计)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:15:46
	 */
	ServiceResult<List<SysCompanyVo>> selectPartnerCompanys();

	/**
	 * @Description: (最新合作商)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 下午2:05:02
	 */
	ServiceResult<Page<SysPartnerVo>> selectTopPartnerVoPageList(Integer pageIndex, Integer pageSize);

	/**
	* @Description: (删除客户经理)
	* @param partnerUmanagerId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:09:27
	 */
	ServiceResult<Object> delUman(Integer partnerUmanagerId);
	
	/**
	* @Description: (添加客户经理)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月12日 下午2:37:55
	 */
	ServiceResult<Object> addUManager(SysPartnerUmanager partnerUmanager);
}
