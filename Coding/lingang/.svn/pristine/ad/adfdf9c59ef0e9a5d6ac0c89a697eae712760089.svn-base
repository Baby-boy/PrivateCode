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
import com.lingang.api.domain.entity.SysPublic;
import com.lingang.api.domain.entity.SysPublicLabel;
import com.lingang.api.domain.entity.SysPublicUmanager;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysPublicPara;
import com.lingang.api.domain.pfvo.SysPublicPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysPublicVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysPublicService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.core.persistence.reader.SysCollectReaderMapper;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysPublicReaderMapper;
import com.lingang.core.persistence.reader.SysPublicUmanagerReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysPublicLabelWriterMapper;
import com.lingang.core.persistence.writer.SysPublicUmanagerWriterMapper;
import com.lingang.core.persistence.writer.SysPublicWriterMapper;

@Service("sysPublicService")
public class SysPublicServiceImpl implements SysPublicService {

	@Resource
	private SysPublicReaderMapper sysPublicReaderMapper;

	@Resource
	private SysPublicWriterMapper sysPublicWriterMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Resource
	private SysCollectReaderMapper sysCollectReaderMapper;

	@Resource
	private SysPublicLabelWriterMapper sysPublicLabelWriterMapper;

	@Resource
	private SysPublicUmanagerReaderMapper sysPublicUmanagerReaderMapper;

	@Resource
	private SysPublicUmanagerWriterMapper sysPublicUmanagerWriterMapper;

	@Override
	public ServiceResult<Page<SysPublicVo>> selectSysPublicVoPageListByRegionIdAndBasicsId(Map<String, Object> map) {
		ServiceResult<Page<SysPublicVo>> result = new ServiceResult<Page<SysPublicVo>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("regionTypeId", regionTypeId);
		// map.put("parkId", parkId);
		// map.put("basicsId", basicsId);
		// map.put("beninTime", beninTime);
		// map.put("endTime", endTime);
		// map.put("companyId", companyId);
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysPublicReaderMapper.selectSysPublicVoCountByRegionIdAndBasicsId(map);
		Page<SysPublicVo> page = new Page<SysPublicVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPublicVo> list = sysPublicReaderMapper.selectSysPublicVoPageListByRegionIdAndBasicsId(map);
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysPublicVo> selectSysPublicDetailsByPublicId(Integer publicId) {
		ServiceResult<SysPublicVo> result = new ServiceResult<SysPublicVo>();
		int countRecord = sysPublicReaderMapper.selectSysPublicDetailsCountByPublicId(publicId);
		if (countRecord == 1) {
			SysPublicVo publicVo = sysPublicReaderMapper.selectSysPublicDetailsByPublicId(publicId);
			// AD域
			List<SysUmanager> ums = new ArrayList<SysUmanager>();
			for (SysPublicUmanager spu : publicVo.getPumans()) {
				String resultStr = HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
				resultStr = resultStr.replace("CN=", "");
				SysUserData data = new SysUserData();
				if (!resultStr.equals("")) {
					data = JSONObject.parseObject(resultStr, SysUserData.class);
					SysUser user = data.getData().get(0);
					SysUmanager sysUmanager = new SysUmanager();
					sysUmanager.setUserAccount(spu.getUserAccount());
					sysUmanager.setUmanagerId(spu.getPublicUmanagerId());
					sysUmanager.setUmanagerName(user.getUserName());
					sysUmanager.setUmanagerCompany(user.getUserCompany());
					sysUmanager.setUmanagerDepartment(user.getUserDepartment());
					sysUmanager.setUmanagerEmail(user.getUserEmail());
					sysUmanager.setUmanagerTel(user.getUserTel());
					sysUmanager.setUmanagerMobile(user.getUserMobile());
					ums.add(sysUmanager);
				}
			}
			publicVo.setPumans(null);
			publicVo.setUmanagers(ums);
			result.setData(publicVo);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Page<SysPublicPfVo>> selectSysPublicPfVoPageList(SysPublicPara para) {
		ServiceResult<Page<SysPublicPfVo>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("publicTitleKeywords", para.getPublicTitleKeywords());
		map.put("regionNameKeywords", para.getRegionNameKeywords());
		map.put("parkNameKeywords", para.getParkNameKeywords());
		map.put("publicState", para.getPublicState());
		int countRecord = sysPublicReaderMapper.selectSysPublicPfVoCount(map);
		Page<SysPublicPfVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			List<SysPublicPfVo> list = sysPublicReaderMapper.selectSysPublicPfVoPageList(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> updatePublicDetails(SysPublic sysPublic) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysPublicWriterMapper.updateByPrimaryKeySelective(sysPublic);
		if (sysPublic.getMaxImgId() != null) {
			SysImages maxImg = sysImagesReaderMapper.selectByPrimaryKey(sysPublic.getMaxImgId());
			if (maxImg.getImgState() != 1) {
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		}
		if (sysPublic.getMinImgId() != null) {
			SysImages minImg = sysImagesReaderMapper.selectByPrimaryKey(sysPublic.getMinImgId());
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
	public ServiceResult<Object> addPublicDetails(SysPublic sysPublic) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysPublicWriterMapper.insertSelective(sysPublic);
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功!");
			result.setData(sysPublic);
			if (sysPublic.getMaxImgId() != null) {
				SysImages maxImg = new SysImages();
				maxImg.setImgId(sysPublic.getMaxImgId());
				maxImg.setObjId(sysPublic.getPublicId());
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
			if (sysPublic.getMinImgId() != null) {
				SysImages minImg = new SysImages();
				minImg.setImgId(sysPublic.getMinImgId());
				minImg.setObjId(sysPublic.getPublicId());
				minImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(minImg);
			}
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败!");
		}
		return result;
	}

	@Override
	public ServiceResult<List<SysNewAddStatisticsVo>> selectNewPublicStatisticsList() {
		ServiceResult<List<SysNewAddStatisticsVo>> result = new ServiceResult<List<SysNewAddStatisticsVo>>();
		List<SysNewAddStatisticsVo> newAddStatisticsVoList = new ArrayList<SysNewAddStatisticsVo>();
		List<SysNewAddMonthStatisticsVo> monthList = sysPublicReaderMapper.selectNewAddMonth();
		List<SysNewAddQuarterStatisticsVo> quarterList = sysPublicReaderMapper.selectNewAddQuarter();
		Map<String, Object> currentMap = new HashMap<String, Object>();
		String currentMonth = "0";
		String currentQuarter = "0";
		if (monthList != null && monthList.size() > 0) { // 当按月查询有数据时，按季度也有数据
			SysNewAddStatisticsVo sysPartnerNewAddStatisticsVo = new SysNewAddStatisticsVo();
			sysPartnerNewAddStatisticsVo.setMonthList(monthList);
			sysPartnerNewAddStatisticsVo.setQuarterList(quarterList);
			newAddStatisticsVoList.add(sysPartnerNewAddStatisticsVo);

			// 遍历 月、季度数据，提取出当前月。季度
			for (SysNewAddMonthStatisticsVo sysNewAddMonthStatisticsVo : monthList) {
				Calendar a = Calendar.getInstance();
				if ((a.get(Calendar.YEAR) + "").equals(sysNewAddMonthStatisticsVo.getCreateYear()) && (a.get(Calendar.MONTH) + 1 + "").equals(sysNewAddMonthStatisticsVo.getCreateMonth())) {
					currentMonth = sysNewAddMonthStatisticsVo.getCreateMonth();
				}
			}
			for (SysNewAddQuarterStatisticsVo sysNewAddQuarterStatisticsVo : quarterList) {
				Calendar a = Calendar.getInstance();
				if ((a.get(Calendar.YEAR) + "").equals(sysNewAddQuarterStatisticsVo.getCreateYear()) && ((a.get(Calendar.MONTH)) / 3 + 1 + "").equals(sysNewAddQuarterStatisticsVo.getCreateQuarter())) {
					currentQuarter = sysNewAddQuarterStatisticsVo.getCreateQuarter();
				}
			}
		}
		currentMap.put("currentMonth", currentMonth);
		currentMap.put("currentQuarter", currentQuarter);
		currentMap.put("maxMonth", sysPublicReaderMapper.selectNewAddMonthMax());
		currentMap.put("maxQuarter", sysPublicReaderMapper.selectNewAddQuarterMax());
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(newAddStatisticsVoList);
		result.setDataMap(currentMap);
		return result;
	}

	@Override
	public ServiceResult<SysPublicVo> selectSysPublicDetails(Integer publicId) {
		ServiceResult<SysPublicVo> result = new ServiceResult<SysPublicVo>();
		SysPublicVo publicVo = sysPublicReaderMapper.selectSysPublicDetails(publicId);
		// AD域
		List<SysUmanager> ums = new ArrayList<SysUmanager>();
		for (SysPublicUmanager spu : publicVo.getPumans()) {
			String resultStr = HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
			resultStr = resultStr.replace("CN=", "");
			SysUserData data = new SysUserData();
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(spu.getUserAccount());
				sysUmanager.setUmanagerId(spu.getPublicUmanagerId());
				sysUmanager.setUmanagerName(user.getUserName());
				sysUmanager.setUmanagerCompany(user.getUserCompany());
				sysUmanager.setUmanagerDepartment(user.getUserDepartment());
				sysUmanager.setUmanagerEmail(user.getUserEmail());
				sysUmanager.setUmanagerTel(user.getUserTel());
				sysUmanager.setUmanagerMobile(user.getUserMobile());
				ums.add(sysUmanager);
			}
		}
		publicVo.setPumans(null);
		publicVo.setUmanagers(ums);
		result.setData(publicVo);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public SysCollect selectSysCollect(Integer userId, Integer publicId, Integer collectType) {
		return sysCollectReaderMapper.selectByUserIdAndCollectTypeAndObjId(userId, collectType, publicId);
	}

	@Override
	public ServiceResult<Object> addLabelCascade(SysPublicLabel publicLabel) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPublicLabelWriterMapper.insertSelective(publicLabel);
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
		int i = sysPublicLabelWriterMapper.deleteByObjectIdAndLabelId(objectId, labelId);
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
	public ServiceResult<Object> delPark(Integer publicId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPublicWriterMapper.delPark(publicId);
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
	public ServiceResult<List<SysCompanyVo>> selectPublicCompany() {
		ServiceResult<List<SysCompanyVo>> result = new ServiceResult<List<SysCompanyVo>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		int totalCount = sysPublicReaderMapper.selectPublicCompanyTotalCount();
		dataMap.put("totalCount", totalCount);

		List<SysCompanyVo> syslabelsVo = sysPublicReaderMapper.selectPublicCompanyCount();
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(syslabelsVo);
		result.setDataMap(dataMap);
		return result;
	}

	@Override
	public ServiceResult<Object> delUman(Integer publicUmanagerId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPublicUmanagerWriterMapper.deleteByPrimaryKey(publicUmanagerId);
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
	public ServiceResult<Object> addUManager(SysPublicUmanager publicUmanager) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysPublicUmanager sysPublicUmanager = sysPublicUmanagerReaderMapper.selectByPublicIdAndUserAccount(publicUmanager.getPublicId(), publicUmanager.getUserAccount());
		if (sysPublicUmanager != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加");
			return result;
		}
		int i = sysPublicUmanagerWriterMapper.insertSelective(publicUmanager);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			String resultStr = HttpURLConnectionResult.resultStrCheckUser(publicUmanager.getUserAccount());
			resultStr = resultStr.replace("CN=", "");
			SysUserData data = new SysUserData();
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(publicUmanager.getUserAccount());
				sysUmanager.setUmanagerId(publicUmanager.getPublicUmanagerId());
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
