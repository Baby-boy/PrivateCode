package com.lingang.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysPolicy;
import com.lingang.api.domain.entity.SysSearchRecords;
import com.lingang.api.domain.vo.SysIndustryVo;
import com.lingang.api.domain.vo.SysNewsVo;
import com.lingang.api.domain.vo.SysParkVo;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.domain.vo.SysPublicVo;
import com.lingang.api.domain.vo.SysSearchResultTypeVo;
import com.lingang.api.domain.vo.SysServiceVo;
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.service.SysIndustryService;
import com.lingang.api.service.SysNewsService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysPartnerService;
import com.lingang.api.service.SysPolicyService;
import com.lingang.api.service.SysPublicService;
import com.lingang.api.service.SysSearchService;
import com.lingang.api.service.SysServiceService;
import com.lingang.api.service.SysStationService;
import com.lingang.common.constants.SystemConstants;
import com.lingang.core.persistence.reader.SysIndustryReaderMapper;
import com.lingang.core.persistence.reader.SysNewsReaderMapper;
import com.lingang.core.persistence.reader.SysParkReaderMapper;
import com.lingang.core.persistence.reader.SysPartnerReaderMapper;
import com.lingang.core.persistence.reader.SysPolicyReaderMapper;
import com.lingang.core.persistence.reader.SysPublicReaderMapper;
import com.lingang.core.persistence.reader.SysSearchRecordsReaderMapper;
import com.lingang.core.persistence.reader.SysServiceReaderMapper;
import com.lingang.core.persistence.reader.SysStationReaderMapper;
import com.lingang.core.persistence.writer.SysSearchRecordsWriterMapper;

@Service("sysSearchService")
public class SysSearchServiceImpl implements SysSearchService {

	@Resource
	private SysParkService sysParkService;
	@Resource
	private SysParkReaderMapper sysParkReaderMapper;

	@Resource
	private SysPartnerService sysPartnerService;
	@Resource
	private SysPartnerReaderMapper sysPartnerReaderMapper;

	@Resource
	private SysServiceService sysServiceService;
	@Resource
	private SysServiceReaderMapper sysServiceReaderMapper;

	@Resource
	private SysStationService sysStationService;
	@Resource
	private SysStationReaderMapper sysStationReaderMapper;

	@Resource
	private SysPublicService sysPublicService;
	@Resource
	private SysPublicReaderMapper sysPublicReaderMapper;

	@Resource
	private SysIndustryService sysIndustryService;
	@Resource
	private SysIndustryReaderMapper sysIndustryReaderMapper;

	@Resource
	private SysNewsService sysNewsService;
	@Resource
	private SysNewsReaderMapper sysNewsReaderMapper;

	@Resource
	private SysPolicyService sysPolicyService;
	@Resource
	private SysPolicyReaderMapper sysPolicyReaderMapper;

	@Resource
	private SysSearchRecordsReaderMapper sysSearchRecordsReaderMapper;

	@Resource
	private SysSearchRecordsWriterMapper sysSearchRecordsWriterMapper;

	@Override
	public JsonResult searchStatisticsPageList(String type, String keywords, Integer pageIndex, Integer pageSize) {
		JsonResult jsonResult = new JsonResult();
		// 条件
		Map<String, Object> map = new HashMap<>();

		map.put("keywords", keywords);
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);

		// 查询哪些模块需要显示
		List<SysSearchResultTypeVo> typeMap = showList(map);
		// 如果没有传入类型type使用了默认的值,需要展示查询存在的数据,
		if (StringUtils.isBlank(type) || type.equals(SystemConstants.SYS_TYPE_NEWS)) {
			for (SysSearchResultTypeVo sysSearchResultTypeVo : typeMap) {
				type = sysSearchResultTypeVo.getId()+"";
				break;
			}
		}
		
		if (type.equals(SystemConstants.SYS_TYPE_NEWS)) {
			// 新闻列表 -- 1
			ServiceResult<Page<SysNewsVo>> result = sysNewsService.selectSysNewsAll(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_POLICY)) {
			// 政策列表 -- 2
			ServiceResult<Page<SysPolicy>> result = sysPolicyService.selectSysPolicyAll(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_PARK)) {
			// 产业园区 -- 3
			ServiceResult<Page<SysParkVo>> result = sysParkService.selectSysParkVoPageListByRegionIdAndIndustryIds(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_PARTNER)) {
			// 合作伙伴 -- 4
			ServiceResult<Page<SysPartnerVo>> result = sysPartnerService.selectPartnerPageList(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_SERVICE)) {
			// 服务机构 -- 5
			ServiceResult<Page<SysServiceVo>> result = sysServiceService.selectServicePageList(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_STATION)) {
			// 入驻企业 -- 6
			ServiceResult<Page<SysStationVo>> result = sysStationService.selectSysStationAll(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_PUBLIC)) {
			// 公共平台 -- 7
			ServiceResult<Page<SysPublicVo>> result = sysPublicService
					.selectSysPublicVoPageListByRegionIdAndBasicsId(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		} else if (type.equals(SystemConstants.SYS_TYPE_INDUSTRY)) {
			// 产业集群 -- 8
			ServiceResult<Page<SysIndustryVo>> result = sysIndustryService.selectSysIndustryList(map);
			jsonResult.setData(result.getData());
			jsonResult.setStateCode(result.getStateCode());
		}
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("list", typeMap);
		jsonResult.setDataMap(listMap);

		try {
			SysSearchRecords sysSearchRecords = sysSearchRecordsReaderMapper.selectRecords(keywords);
			if (null != sysSearchRecords) {
				// 表示有这个热搜，数量加一
				sysSearchRecords.setKeywordsCount(sysSearchRecords.getKeywordsCount() + 1);
				sysSearchRecordsWriterMapper.updateByPrimaryKeySelective(sysSearchRecords);
			} else {
				// 没有这个热搜，数量变1，添加一个热搜。
				SysSearchRecords record = new SysSearchRecords();
				record.setSearchId(null);
				record.setKeywordsCount(1);
				record.setKeywords(keywords);
				sysSearchRecordsWriterMapper.insertSelective(record);
			}
		} catch (Exception e) {
		}

		return jsonResult;
	}

	private List<SysSearchResultTypeVo> showList(Map<String, Object> map) {
		// 进行列表展示,查询总数
		List<SysSearchResultTypeVo> resultCountList = new ArrayList<SysSearchResultTypeVo>();
		Integer newsCount = sysNewsReaderMapper.selectSysNewsCount(map);
		if (null != newsCount && 0 != newsCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_ONE);
			sysSearchResultTypeVo.setName("新闻");
			resultCountList.add(sysSearchResultTypeVo);
		}

		Integer policyCount = sysPolicyReaderMapper.selectSysPolicyCount(map);
		if (null != policyCount && 0 != policyCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_TWO);
			sysSearchResultTypeVo.setName("政策");
			resultCountList.add(sysSearchResultTypeVo);
		}

		Integer parkCount = sysParkReaderMapper.selectSysParkVoCountByRegionIdAndIndustryIds(map);
		if (null != parkCount && 0 != parkCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
		sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_THREE);
		sysSearchResultTypeVo.setName("产业园区");
		resultCountList.add(sysSearchResultTypeVo);
		}

		Integer partnerCount = sysPartnerReaderMapper.selectPartnerCount(map);
		if (null != partnerCount && 0 != partnerCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_FOUR);
			sysSearchResultTypeVo.setName("合作伙伴");
			resultCountList.add(sysSearchResultTypeVo);
		}

		Integer serviceCount = sysServiceReaderMapper.selectServiceCount(map);
		if (null != serviceCount && 0 != serviceCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_FIVE);
			sysSearchResultTypeVo.setName("服务机构");
			resultCountList.add(sysSearchResultTypeVo);
		}

		Integer stationCount = sysStationReaderMapper.selectSysStationCount(map);
		if (null != stationCount && 0 != stationCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_SIX);
			sysSearchResultTypeVo.setName("入驻企业");
			resultCountList.add(sysSearchResultTypeVo);
		}

		Integer publicCount = sysPublicReaderMapper.selectSysPublicVoCountByRegionIdAndBasicsId(map);
		if (null != publicCount && 0 != publicCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_SEVEN);
			sysSearchResultTypeVo.setName("公共平台");
			resultCountList.add(sysSearchResultTypeVo);
		}

		Integer industryCount = sysIndustryReaderMapper.selectSysIndustryCount(map);
		if (null != industryCount && 0 != industryCount) {
			SysSearchResultTypeVo sysSearchResultTypeVo = new SysSearchResultTypeVo();
			sysSearchResultTypeVo.setId(SystemConstants.SYS_NUM_EIGHT);
			sysSearchResultTypeVo.setName("产业集群");
			resultCountList.add(sysSearchResultTypeVo);
		}

		return resultCountList;
	}

	@Override
	public ServiceResult<List<SysSearchRecords>> selectByPrimaryKeys() {
		ServiceResult<List<SysSearchRecords>> result = new ServiceResult<List<SysSearchRecords>>();
		List<SysSearchRecords> list = sysSearchRecordsReaderMapper.selectHotSearch();
		// 定义一个list用于储存热搜词
		List<SysSearchRecords> hotList = new ArrayList<>();
		if (null != list && list.size() > 0) {
			// 存在热搜词
			if (list.size() < 6) {
				hotList.addAll(list);
			} else {
				for (int i = 0; i < 6; i++) {
					hotList.add(list.get(i));
				}
			}
		}

		result.setData(hotList);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

}
