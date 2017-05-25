package com.lingang.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysServiceLabel;
import com.lingang.api.domain.entity.SysServicePark;
import com.lingang.api.domain.entity.SysServiceUmanager;
import com.lingang.api.domain.entity.SysServiceWithBLOBs;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysServicePara;
import com.lingang.api.domain.pfvo.SysServicePfVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysServiceVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysServiceService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.core.persistence.reader.SysCollectReaderMapper;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysServiceLabelReaderMapper;
import com.lingang.core.persistence.reader.SysServiceReaderMapper;
import com.lingang.core.persistence.reader.SysServiceUmanagerReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysServiceLabelWriterMapper;
import com.lingang.core.persistence.writer.SysServiceParkWriterMapper;
import com.lingang.core.persistence.writer.SysServiceUmanagerWriterMapper;
import com.lingang.core.persistence.writer.SysServiceWriterMapper;

@Service("sysServiceService")
public class SysServiceServiceImpl implements SysServiceService {

	@Resource
	private SysServiceReaderMapper sysServiceReaderMapper;

	@Resource
	private SysServiceWriterMapper sysServiceWriterMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Resource
	private SysCollectReaderMapper sysCollectReaderMapper;

	@Resource
	private SysServiceLabelReaderMapper sysServiceLabelReaderMapper;

	@Resource
	private SysServiceLabelWriterMapper sysServiceLabelWriterMapper;

	@Resource
	private SysServiceParkWriterMapper sysServiceParkWriterMapper;

	@Resource
	private SysServiceUmanagerReaderMapper sysServiceUmanagerReaderMapper;

	@Resource
	private SysServiceUmanagerWriterMapper sysServiceUmanagerWriterMapper;

	@Override
	public ServiceResult<Page<SysServiceVo>> selectServicePageList(Map<String, Object> map) {
		ServiceResult<Page<SysServiceVo>> result = new ServiceResult<Page<SysServiceVo>>();
		// 查询条件
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("regionTypeId", regionTypeId);
		// map.put("parkId", parkId);
		// map.put("basicsId", basicsId);
		// map.put("beninTime", beninTime);
		// map.put("endTime", endTime);
		// map.put("labelIds", labelIds);
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysServiceReaderMapper.selectServiceCount(map);
		Page<SysServiceVo> page = new Page<SysServiceVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysServiceVo> list = sysServiceReaderMapper.selectServicePageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysServiceVo> selectServiceByServiceId(Integer serviceId) {
		ServiceResult<SysServiceVo> result = new ServiceResult<SysServiceVo>();
		SysServiceVo serviceVo = sysServiceReaderMapper.selectServiceVoByServiceId(serviceId);
		// AD域
		List<SysUmanager> ums = new ArrayList<SysUmanager>();
		for(SysServiceUmanager spu:serviceVo.getSumans()){
			String resultStr=HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
			resultStr=resultStr.replace("CN=", "");
			SysUserData data=new SysUserData();
			if(!resultStr.equals("")){
				data =JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user=data.getData().get(0);
				SysUmanager sysUmanager=new SysUmanager();
				sysUmanager.setUserAccount(spu.getUserAccount());
				sysUmanager.setUmanagerId(spu.getServiceUmanagerId());
				sysUmanager.setUmanagerName(user.getUserName());
				sysUmanager.setUmanagerCompany(user.getUserCompany());
				sysUmanager.setUmanagerDepartment(user.getUserDepartment());
				sysUmanager.setUmanagerEmail(user.getUserEmail());
				sysUmanager.setUmanagerTel(user.getUserTel());
				sysUmanager.setUmanagerMobile(user.getUserMobile());
				ums.add(sysUmanager);
			}
		}
		serviceVo.setSumans(null);
		serviceVo.setUmanagers(ums);
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(serviceVo);
		return result;
	}

	@Override
	public ServiceResult<Page<SysServicePfVo>> selectServicePfVoPageList(SysServicePara para) {
		ServiceResult<Page<SysServicePfVo>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceNameKeywords", para.getServiceNameKeywords());
		map.put("regionNameKeywords", para.getRegionNameKeywords());
		map.put("serviceTeamKeywords", para.getServiceTeamKeywords());
		map.put("serviceState", para.getServiceState());
		int countRecord = sysServiceReaderMapper.selectServicePfVoCount(map);
		Page<SysServicePfVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			List<SysServicePfVo> list = sysServiceReaderMapper.selectServicePfVoPageList(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysNewAddStatisticsVo>> selectNewServiceStatisticsList() {
		ServiceResult<List<SysNewAddStatisticsVo>> result = new ServiceResult<List<SysNewAddStatisticsVo>>();
		// Map<String, Object> numMap = new HashMap<String, Object>();
		List<SysNewAddStatisticsVo> newAddStatisticsVoList = new ArrayList<SysNewAddStatisticsVo>();
		List<SysNewAddMonthStatisticsVo> sysNewAddMonthStatisticsVoList = sysServiceReaderMapper.selectNewAddMonth();
		List<SysNewAddQuarterStatisticsVo> sysNewAddQuarterStatisticsVoList = sysServiceReaderMapper.selectNewAddQuarter();
		Map<String, Object> currentMap = new HashMap<String, Object>();
		String currentMonth = "0";
		String currentQuarter = "0";
		if (sysNewAddMonthStatisticsVoList != null && sysNewAddMonthStatisticsVoList.size() > 0) { // 当按月查询有数据时，按季度也有数据
			SysNewAddStatisticsVo SysNewAddStatisticsVo = new SysNewAddStatisticsVo();
			SysNewAddStatisticsVo.setMonthList(sysNewAddMonthStatisticsVoList);
			SysNewAddStatisticsVo.setQuarterList(sysNewAddQuarterStatisticsVoList);
			newAddStatisticsVoList.add(SysNewAddStatisticsVo);

			// 遍历 月、季度数据，提取出当前月。季度
			for (SysNewAddMonthStatisticsVo sysNewAddMonthStatisticsVo : sysNewAddMonthStatisticsVoList) {
				Calendar a = Calendar.getInstance();
				if ((a.get(Calendar.YEAR) + "").equals(sysNewAddMonthStatisticsVo.getCreateYear()) && (a.get(Calendar.MONTH) + 1 + "").equals(sysNewAddMonthStatisticsVo.getCreateMonth())) {
					currentMonth = sysNewAddMonthStatisticsVo.getCreateMonth();
				}
			}
			for (SysNewAddQuarterStatisticsVo sysNewAddQuarterStatisticsVo : sysNewAddQuarterStatisticsVoList) {
				Calendar a = Calendar.getInstance();
				if ((a.get(Calendar.YEAR) + "").equals(sysNewAddQuarterStatisticsVo.getCreateYear()) && ((a.get(Calendar.MONTH)) / 3 + 1 + "").equals(sysNewAddQuarterStatisticsVo.getCreateQuarter())) {
					currentQuarter = sysNewAddQuarterStatisticsVo.getCreateQuarter();
				}
			}
		}
		currentMap.put("currentMonth", currentMonth);
		currentMap.put("currentQuarter", currentQuarter);
		currentMap.put("maxMonth", sysServiceReaderMapper.selectNewAddMonthMax());
		currentMap.put("maxQuarter", sysServiceReaderMapper.selectNewAddQuarterMax());
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(newAddStatisticsVoList);
		result.setDataMap(currentMap);
		return result;
	}

	@Override
	public ServiceResult<Object> updateServiceDetails(SysServiceWithBLOBs service) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysServiceWriterMapper.updateByPrimaryKeySelective(service);

		if (service.getLogoImgId() != null) {
			SysImages maxImg = sysImagesReaderMapper.selectByPrimaryKey(service.getLogoImgId());
			if (maxImg.getImgState() != 1) {
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		}
		if (service.getMinImgId() != null) {
			SysImages minImg = sysImagesReaderMapper.selectByPrimaryKey(service.getMinImgId());
			if (minImg.getImgState() != 1) {
				minImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(minImg);
			}
		}
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("更新成功!");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("更新失败!");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addServiceDetails(SysServiceWithBLOBs service) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int info = sysServiceWriterMapper.insertSelective(service);

		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);

			if (service.getLogoImgId() != null) {
				SysImages maxImg = new SysImages();
				maxImg.setImgId(service.getLogoImgId());
				maxImg.setObjId(service.getServiceId());
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
			if (service.getMinImgId() != null) {
				SysImages minImg = new SysImages();
				minImg.setImgId(service.getMinImgId());
				minImg.setObjId(service.getServiceId());
				minImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(minImg);
			}
			result.setMessage("添加成功!");
			result.setData(service);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败!");
		}
		return result;
	}

	@Override
	public ServiceResult<List<SysLabelsVo>> selectServicelabel() {
		ServiceResult<List<SysLabelsVo>> result = new ServiceResult<List<SysLabelsVo>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		int totalCount = sysServiceLabelReaderMapper.selectServiceTotalCount();
		dataMap.put("totalCount", totalCount);

		List<SysLabelsVo> syslabelsVo = sysServiceLabelReaderMapper.selectServiceCountLabel();
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(syslabelsVo);
		result.setDataMap(dataMap);
		return result;
	}

	@Override
	public SysCollect selectSysCollect(Integer userId, Integer serviceId, Integer collectType) {
		return sysCollectReaderMapper.selectByUserIdAndCollectTypeAndObjId(userId, collectType, serviceId);
	}

	@Override
	public ServiceResult<Object> addLabelCascade(SysServiceLabel serviceLabel) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysServiceLabelWriterMapper.insertSelective(serviceLabel);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addServiceCascade(SysServicePark servicePark) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysServiceParkWriterMapper.insertSelective(servicePark);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> delLab(Integer objectId, Integer labelId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysServiceLabelWriterMapper.deleteByObjectIdAndLabelId(objectId, labelId);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("删除成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("删除失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> delPark(Integer objectId, Integer serviceId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysServiceParkWriterMapper.deleteByObjectIdAndServiceId(objectId, serviceId);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("删除成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("删除失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> delUman(Integer serviceUmanagerId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysServiceUmanagerWriterMapper.deleteByPrimaryKey(serviceUmanagerId);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("删除成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("删除失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addUManager(SysServiceUmanager serviceUmanager) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysServiceUmanager sysServiceUmanager = sysServiceUmanagerReaderMapper.selectByServiceIdAndUserAccount(serviceUmanager.getServiceId(), serviceUmanager.getUserAccount());
		if (sysServiceUmanager != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加");
			return result;
		}
		int i = sysServiceUmanagerWriterMapper.insertSelective(serviceUmanager);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			String resultStr=HttpURLConnectionResult.resultStrCheckUser(serviceUmanager.getUserAccount());
			resultStr=resultStr.replace("CN=", "");
			SysUserData data=new SysUserData();
			if(!resultStr.equals("")){
				data =JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user=data.getData().get(0);
				SysUmanager sysUmanager=new SysUmanager();
				sysUmanager.setUserAccount(serviceUmanager.getUserAccount());
				sysUmanager.setUmanagerId(serviceUmanager.getServiceUmanagerId());
				sysUmanager.setUmanagerName(user.getUserName());
				sysUmanager.setUmanagerCompany(user.getUserCompany());
				sysUmanager.setUmanagerDepartment(user.getUserDepartment());
				sysUmanager.setUmanagerEmail(user.getUserEmail());
				sysUmanager.setUmanagerTel(user.getUserTel());
				sysUmanager.setUmanagerMobile(user.getUserMobile());
				result.setData(sysUmanager);
			}
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("添加失败");
		}
		return result;
	}

}
