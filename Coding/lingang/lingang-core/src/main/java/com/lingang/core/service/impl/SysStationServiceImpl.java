package com.lingang.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysStation;
import com.lingang.api.domain.entity.SysStationIndustry;
import com.lingang.api.domain.entity.SysStationLabel;
import com.lingang.api.domain.entity.SysStationPark;
import com.lingang.api.domain.entity.SysStationUmanager;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysStationPara;
import com.lingang.api.domain.pfvo.SysStationPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysStationService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.common.constants.SystemConstants;
import com.lingang.core.persistence.reader.SysCollectReaderMapper;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysStationLabelReaderMapper;
import com.lingang.core.persistence.reader.SysStationReaderMapper;
import com.lingang.core.persistence.reader.SysStationUmanagerReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysStationIndustryWriterMapper;
import com.lingang.core.persistence.writer.SysStationLabelWriterMapper;
import com.lingang.core.persistence.writer.SysStationParkWriterMapper;
import com.lingang.core.persistence.writer.SysStationUmanagerWriterMapper;
import com.lingang.core.persistence.writer.SysStationWriterMapper;

@Service("sysStationService")
public class SysStationServiceImpl implements SysStationService {
	@Resource
	private SysStationReaderMapper sysStationReaderMapper;
	@Resource
	private SysStationWriterMapper sysStationWriterMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Resource
	private SysStationLabelReaderMapper sysStationLabelReaderMapper;

	@Resource
	private SysCollectReaderMapper sysCollectReaderMapper;

	@Resource
	private SysStationLabelWriterMapper sysStationLabelWriterMapper;

	@Resource
	private SysStationParkWriterMapper sysStationParkWriterMapper;

	@Resource
	private SysStationIndustryWriterMapper sysStationIndustryWriterMapper;

	@Resource
	private SysStationUmanagerReaderMapper sysStationUmanagerReaderMapper;

	@Resource
	private SysStationUmanagerWriterMapper sysStationUmanagerWriterMapper;

	/**
	 * 入驻列表
	 */
	@Override
	public ServiceResult<Page<SysStationVo>> selectSysStationAll(Map<String, Object> map) {
		ServiceResult<Page<SysStationVo>> result = new ServiceResult<Page<SysStationVo>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("regionTypeId", regionTypeId);
		// map.put("industryIds", industryIds);
		// map.put("labelIds", labelIds);
		// map.put("parkId", parkId);
		// map.put("beninTime", beninTime);
		// map.put("endTime", endTime);
		// map.put("companyId", companyId);
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		String layout = (String) map.get("layout");
		int countRecord = sysStationReaderMapper.selectSysStationCount(map);

		// 多点分布统计列表
		if (StringUtils.isNotEmpty(layout) && "1".equals(layout)) {
			countRecord = sysStationReaderMapper.selectStationCount(SystemConstants.SYS_NUM_ONE);
		}
		if (StringUtils.isNotEmpty(layout) && "2".equals(layout)) {
			countRecord = sysStationReaderMapper.selectStationCount(SystemConstants.SYS_NUM_TWO);
		}

		Page<SysStationVo> page = new Page<SysStationVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);

			List<SysStationVo> list = null;
			// 多点布局列表
			if (StringUtils.isNotEmpty(layout) && "1".equals(layout)) {
				map.put("sysNum", SystemConstants.SYS_NUM_ONE);
				list = sysStationReaderMapper.selectMoreSysStationPageList(map);
			} else if (StringUtils.isNotEmpty(layout) && "2".equals(layout)) {
				map.put("sysNum", SystemConstants.SYS_NUM_TWO);
				list = sysStationReaderMapper.selectMoreSysStationPageList(map);
			} else {
				list = sysStationReaderMapper.selectSysStationPageList(map);
			}

			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 入驻详情
	 */
	@Override
	public ServiceResult<SysStationVo> selectByPrimaryKey(Integer stationId) {
		ServiceResult<SysStationVo> result = new ServiceResult<SysStationVo>();
		SysStationVo stationVo = sysStationReaderMapper.selectByPrimaryKey(stationId);
		// AD域
		List<SysUmanager> ums = new ArrayList<SysUmanager>();
		for (SysStationUmanager spu : stationVo.getSumans()) {
			String resultStr = HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
			resultStr = resultStr.replace("CN=", "");
			SysUserData data = new SysUserData();
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(spu.getUserAccount());
				sysUmanager.setUmanagerId(spu.getStationUmanagerId());
				sysUmanager.setUmanagerName(user.getUserName());
				sysUmanager.setUmanagerCompany(user.getUserCompany());
				sysUmanager.setUmanagerDepartment(user.getUserDepartment());
				sysUmanager.setUmanagerEmail(user.getUserEmail());
				sysUmanager.setUmanagerTel(user.getUserTel());
				sysUmanager.setUmanagerMobile(user.getUserMobile());
				ums.add(sysUmanager);
			}
		}
		stationVo.setSumans(null);
		stationVo.setUmanagers(ums);
		result.setData(stationVo);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	/********* 后台 ********/
	@Override
	public JsonResult selectAll(Map<String, Object> map) {
		int countRecord = sysStationReaderMapper.querySysStationCount(map);
		Integer pageIndex = (Integer) map.get("currentPage");
		Integer pageSize = (Integer) map.get("onePageCount");
		Page<SysStationVo> page = new Page<SysStationVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysStationVo> list = sysStationReaderMapper.queryAllByPage(map);
			page.setList(list);
		}
		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(page);
		jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return jsonResult;
	}

	@Override
	public int insert(SysStation station) {

		return sysStationWriterMapper.insert(station);
	}

	@Override
	public int updateByPrimaryKey(SysStation station) {

		return sysStationWriterMapper.updateByPrimaryKey(station);
	}

	@Override
	public int deleteByPrimaryKey(Integer station_id) {

		return sysStationWriterMapper.deleteByPrimaryKey(station_id);
	}

	@Override
	public ServiceResult<Page<SysStationPfVo>> selectSysStationPfVoPageList(SysStationPara para) {
		ServiceResult<Page<SysStationPfVo>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stationTitleKeywords", para.getStationTitleKeywords());
		map.put("regionNameKeywords", para.getRegionNameKeywords());
		map.put("stationState", para.getStationState());
		int countRecord = sysStationReaderMapper.selectSysStationPfVoCount(map);
		Page<SysStationPfVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			List<SysStationPfVo> list = sysStationReaderMapper.selectSysStationPfVoPageList(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysNewAddStatisticsVo>> selectNewStationStatisticsList() {
		ServiceResult<List<SysNewAddStatisticsVo>> result = new ServiceResult<List<SysNewAddStatisticsVo>>();
		// Map<String, Object> numMap = new HashMap<String, Object>();
		List<SysNewAddStatisticsVo> newAddStatisticsVoList = new ArrayList<SysNewAddStatisticsVo>();
		List<SysNewAddMonthStatisticsVo> sysNewAddMonthStatisticsVoList = sysStationReaderMapper.selectNewAddMonth();
		List<SysNewAddQuarterStatisticsVo> sysNewAddQuarterStatisticsVoList = sysStationReaderMapper.selectNewAddQuarter();
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
		currentMap.put("maxMonth", sysStationReaderMapper.selectNewAddMonthMax());
		currentMap.put("maxQuarter", sysStationReaderMapper.selectNewAddQuarterMax());
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(newAddStatisticsVoList);
		result.setDataMap(currentMap);
		return result;
	}

	@Override
	public ServiceResult<Object> selectmoreStationStatisticsCount() {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int singleNum = sysStationReaderMapper.selectStationCount(SystemConstants.SYS_NUM_ONE);
		int moreNum = sysStationReaderMapper.selectStationCount(SystemConstants.SYS_NUM_TWO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allNum", moreNum + singleNum);
		map.put("moreNum", moreNum);
		map.put("singleNum", singleNum);
		result.setDataMap(map);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> updateStationDetails(SysStation station) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysStationWriterMapper.updateByPrimaryKeySelective(station);
		if (station.getImgId() != null) {
			SysImages minImg = sysImagesReaderMapper.selectByPrimaryKey(station.getImgId());
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

	/**********************************************************************/
	@Override
	public ServiceResult<Object> addStationDetails(SysStation station) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysStationWriterMapper.insertSelective(station);
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功!");
			result.setData(station);
			if (station.getImgId() != null) {
				SysImages minImg = new SysImages();
				minImg.setImgId(station.getImgId());
				minImg.setObjId(station.getStationId());
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
	public ServiceResult<List<SysLabelsVo>> selectStationlabel() {

		ServiceResult<List<SysLabelsVo>> result = new ServiceResult<List<SysLabelsVo>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		int totalCount = sysStationLabelReaderMapper.selectStationTotalCount();
		dataMap.put("totalCount", totalCount);

		List<SysLabelsVo> syslabelsVo = sysStationLabelReaderMapper.selectStationCountLabel();
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(syslabelsVo);
		result.setDataMap(dataMap);
		return result;
	}

	@Override
	public SysCollect selectSysCollect(Integer userId, Integer stationId, Integer collectType) {
		return sysCollectReaderMapper.selectByUserIdAndCollectTypeAndObjId(userId, collectType, stationId);
	}

	@Override
	public ServiceResult<Object> addLabelCascade(SysStationLabel stationLabel) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysStationLabelWriterMapper.insertSelective(stationLabel);
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
	public ServiceResult<Object> addStationCascade(SysStationPark stationPark) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysStationParkWriterMapper.insertSelective(stationPark);
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
	public ServiceResult<Object> addStationIndustryCascade(SysStationIndustry stationIndustry) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysStationIndustryWriterMapper.insertSelective(stationIndustry);
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
		int i = sysStationLabelWriterMapper.deleteByObjectIdAndLabelId(objectId, labelId);
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
	public ServiceResult<Object> delPark(Integer objectId, Integer stationId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysStationParkWriterMapper.deleteByObjectIdAndStationId(objectId, stationId);
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
	public ServiceResult<Object> delIndustry(Integer objectId, Integer industryId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysStationIndustryWriterMapper.deleteByObjectIdAndIndustryId(objectId, industryId);
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
	public ServiceResult<List<SysCompanyVo>> selectStationCompany() {
		ServiceResult<List<SysCompanyVo>> result = new ServiceResult<List<SysCompanyVo>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		int totalCount = sysStationReaderMapper.selectStationCompanyTotalCount();
		dataMap.put("totalCount", totalCount);

		List<SysCompanyVo> syslabelsVo = sysStationReaderMapper.selectStationCompanyCount();
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(syslabelsVo);
		result.setDataMap(dataMap);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(SysStation sysStation) {
		return sysStationWriterMapper.updateByPrimaryKeySelective(sysStation);
	}

	@Override
	public ServiceResult<Page<SysStationVo>> selectTopStationVoPageList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysStationVo>> result = new ServiceResult<Page<SysStationVo>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		int countRecord = sysStationReaderMapper.selectTopStationCount(map);
		Page<SysStationVo> page = new Page<SysStationVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysStationVo> list = sysStationReaderMapper.selectTopStationVoPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> delUman(Integer stationUmanagerId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysStationUmanagerWriterMapper.deleteByPrimaryKey(stationUmanagerId);
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
	public ServiceResult<Object> addUManager(SysStationUmanager stationUmanager) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysStationUmanager sysStationUmanager = sysStationUmanagerReaderMapper.selectByStationIdAndUserAccount(stationUmanager.getStationId(), stationUmanager.getUserAccount());
		if (sysStationUmanager != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加");
			return result;
		}
		int i = sysStationUmanagerWriterMapper.insertSelective(stationUmanager);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			String resultStr = HttpURLConnectionResult.resultStrCheckUser(stationUmanager.getUserAccount());
			resultStr = resultStr.replace("CN=", "");
			SysUserData data = new SysUserData();
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(stationUmanager.getUserAccount());
				sysUmanager.setUmanagerId(stationUmanager.getStationUmanagerId());
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
