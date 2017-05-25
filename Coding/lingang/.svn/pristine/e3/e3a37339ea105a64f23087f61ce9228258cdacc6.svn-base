package com.lingang.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysFile;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysParkLabel;
import com.lingang.api.domain.entity.SysParkUmanager;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.entity.SysWay;
import com.lingang.api.domain.para.SysParkPara;
import com.lingang.api.domain.pfvo.SysParkPfVo;
import com.lingang.api.domain.vo.SysFileAdImgVo;
import com.lingang.api.domain.vo.SysFileVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysParkRegionVo;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.domain.vo.SysParkVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysParkService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.core.persistence.reader.SysCollectReaderMapper;
import com.lingang.core.persistence.reader.SysFileReaderMapper;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysParkReaderMapper;
import com.lingang.core.persistence.reader.SysParkUmanagerReaderMapper;
import com.lingang.core.persistence.writer.SysFileWriterMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysParkIndustryWriterMapper;
import com.lingang.core.persistence.writer.SysParkLabelWriterMapper;
import com.lingang.core.persistence.writer.SysParkUmanagerWriterMapper;
import com.lingang.core.persistence.writer.SysParkWriterMapper;
import com.lingang.core.persistence.writer.SysWayWriterMapper;

@Service("sysParkService")
public class SysParkServiceImpl implements SysParkService {

	@Resource
	private SysParkReaderMapper sysParkReaderMapper;

	@Resource
	private SysParkWriterMapper sysParkWriterMapper;

	@Resource
	private SysFileReaderMapper sysFileReaderMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	// @Resource
	// private SysParkLabelReaderMapper sysParkLabelReaderMapper;

	@Resource
	private SysParkLabelWriterMapper sysParkLabelWriterMapper;

	@Resource
	private SysParkIndustryWriterMapper sysParkIndustryWriterMapper;

	@Resource
	private SysFileWriterMapper sysFileWriterMapper;

	@Resource
	private SysCollectReaderMapper sysCollectReaderMapper;

	@Resource
	private SysWayWriterMapper sysWayWriterMapper;

	@Resource
	private SysParkUmanagerReaderMapper sysParkUmanagerReaderMapper;

	@Resource
	private SysParkUmanagerWriterMapper sysParkUmanagerWriterMapper;

	@Override
	public ServiceResult<Page<SysParkVo>> selectSysParkVoPageListByRegionIdAndIndustryIds(Map<String, Object> map) {
		ServiceResult<Page<SysParkVo>> result = new ServiceResult<Page<SysParkVo>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("regionTypeId", regionTypeId);
		// map.put("regionId", regionId);
		// map.put("industryIds", industryIds);
		// map.put("beninTime", beninTime);
		// map.put("endTime", endTime);
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysParkReaderMapper.selectSysParkVoCountByRegionIdAndIndustryIds(map);
		Page<SysParkVo> page = new Page<SysParkVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysParkVo> list = sysParkReaderMapper.selectSysParkVoPageListByRegionIdAndIndustryIds(map);
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysParkVo> selectSysParkDetailsByStationId(Integer parkId) {
		ServiceResult<SysParkVo> result = new ServiceResult<SysParkVo>();
		SysParkVo sysParkVo = sysParkReaderMapper.selectSysParkDetailsByStationId(parkId);
		// String shareurl = sysParkVo.getFiles().get().getFileAddress();
		if (null != sysParkVo.getFiles() && sysParkVo.getFiles().size() > 0) {
			for (SysFileAdImgVo sysFileAdImgVo : sysParkVo.getFiles()) {
				String shareurl = sysFileAdImgVo.getFileAddress();
				sysFileAdImgVo.setShareUrl(shareurl);
			}
			//AD域信息
			List<SysUmanager> ums = new ArrayList<SysUmanager>();
			for(SysParkUmanager spu:sysParkVo.getPumans()){
				String resultStr=HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
				resultStr=resultStr.replace("CN=", "");
				SysUserData data=new SysUserData();
				if(!resultStr.equals("")){
					data =JSONObject.parseObject(resultStr, SysUserData.class);
					SysUser user=data.getData().get(0);
					SysUmanager sysUmanager=new SysUmanager();
					sysUmanager.setUserAccount(spu.getUserAccount());
					sysUmanager.setUmanagerId(spu.getParkUmanagerId());
					sysUmanager.setUmanagerName(user.getUserName());
					sysUmanager.setUmanagerCompany(user.getUserCompany());
					sysUmanager.setUmanagerDepartment(user.getUserDepartment());
					sysUmanager.setUmanagerEmail(user.getUserEmail());
					sysUmanager.setUmanagerTel(user.getUserTel());
					sysUmanager.setUmanagerMobile(user.getUserMobile());
					ums.add(sysUmanager);
				}
		}
		sysParkVo.setPumans(null);
		sysParkVo.setUmanagers(ums);
		}
		result.setData(sysParkVo);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<SysFileVo> selectClickPopup(Integer fileId, Integer userId) {
		ServiceResult<SysFileVo> result = new ServiceResult<SysFileVo>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", fileId);
		map.put("userId", userId);
		SysFileVo fileVo = sysFileReaderMapper.selectFileByFileId(map);
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(fileVo);
		return result;
	}

	@Override
	public ServiceResult<Page<SysParkPfVo>> selectSysParkPfVoPageList(SysParkPara para) {
		ServiceResult<Page<SysParkPfVo>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parkNameKeywords", para.getParkNameKeywords());
		map.put("parkAddrKeywords", para.getParkAddrKeywords());
		map.put("parkState", para.getParkState());
		int countRecord = sysParkReaderMapper.selectSysParkPfVoCount(map);
		Page<SysParkPfVo> page = new Page<SysParkPfVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysParkPfVo> list = sysParkReaderMapper.selectSysParkPfVoPageList(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> updateParkDetails(SysPark park) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysParkWriterMapper.updateByPrimaryKeySelective(park);
		if (park.getMaxImgId() != null) {
			SysImages maxImg = sysImagesReaderMapper.selectByPrimaryKey(park.getMaxImgId());
			if (maxImg.getImgState() != 1) {
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		}
		if (park.getMinImgId() != null) {
			SysImages minImg = sysImagesReaderMapper.selectByPrimaryKey(park.getMinImgId());
			if (minImg.getImgState() != 1) {
				minImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(minImg);
			}
		}

		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addParkDetails(SysPark park) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysParkWriterMapper.insertSelective(park);
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			result.setData(park);
			if (park.getMaxImgId() != null) {
				SysImages maxImg = new SysImages();
				maxImg.setImgId(park.getMaxImgId());
				maxImg.setObjId(park.getParkId());
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
			if (park.getMinImgId() != null) {
				SysImages minImg = new SysImages();
				minImg.setImgId(park.getMinImgId());
				minImg.setObjId(park.getParkId());
				minImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(minImg);
			}
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Page<SysParkStatisticsVo>> selectParkStationStatisticsList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysParkStatisticsVo>> result = new ServiceResult<Page<SysParkStatisticsVo>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> numMap = new HashMap<String, Object>();
		int maxNum = 0;
		int countRecord = sysParkReaderMapper.selectParkStationStatisticsCount(map);
		Page<SysParkStatisticsVo> page = new Page<SysParkStatisticsVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysParkStatisticsVo> list = sysParkReaderMapper.selectParkStationStatisticsPageList(map);
			page.setList(list);
			// 最大数查询
			maxNum = sysParkReaderMapper.selectParkStationStatisticsCountMax(map);
		}
		numMap.put("maxNum", maxNum);
		result.setDataMap(numMap);
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Page<SysParkStatisticsVo>> selectParkServiceStatisticsList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysParkStatisticsVo>> result = new ServiceResult<Page<SysParkStatisticsVo>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> numMap = new HashMap<String, Object>();
		int maxNum = 0;
		int countRecord = sysParkReaderMapper.selectParkServiceStatisticsCount(map);
		Page<SysParkStatisticsVo> page = new Page<SysParkStatisticsVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysParkStatisticsVo> list = sysParkReaderMapper.selectParkServiceStatisticsPageList(map);
			page.setList(list);
			// 最大数查询
			maxNum = sysParkReaderMapper.selectParkServiceStatisticsCountMax(map);
		}
		numMap.put("maxNum", maxNum);
		result.setDataMap(numMap);
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysNewAddStatisticsVo>> selectNewParkStatisticsList() {
		ServiceResult<List<SysNewAddStatisticsVo>> result = new ServiceResult<List<SysNewAddStatisticsVo>>();
		// Map<String, Object> numMap = new HashMap<String, Object>();
		List<SysNewAddStatisticsVo> newAddStatisticsVoList = new ArrayList<SysNewAddStatisticsVo>();
		List<SysNewAddMonthStatisticsVo> sysNewAddMonthStatisticsVoList = sysParkReaderMapper.selectNewAddMonth();
		List<SysNewAddQuarterStatisticsVo> sysNewAddQuarterStatisticsVoList = sysParkReaderMapper.selectNewAddQuarter();
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
		currentMap.put("maxMonth", sysParkReaderMapper.selectNewAddMonthMax());
		currentMap.put("maxQuarter", sysParkReaderMapper.selectNewAddQuarterMax());
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(newAddStatisticsVoList);
		result.setDataMap(currentMap);
		return result;
	}

	@Override
	public ServiceResult<Page<SysParkStatisticsVo>> selectParkIndustryStatisticsList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysParkStatisticsVo>> result = new ServiceResult<Page<SysParkStatisticsVo>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> numMap = new HashMap<String, Object>();
		int maxNum = 0;
		int countRecord = sysParkReaderMapper.selectParkIndustryStatisticsCount(map);
		Page<SysParkStatisticsVo> page = new Page<SysParkStatisticsVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysParkStatisticsVo> list = sysParkReaderMapper.selectParkIndustryStatisticsPageList(map);
			page.setList(list);
			// 最大数查询
			maxNum = sysParkReaderMapper.selectParkIndustryStatisticsCountMax(map);
		}
		numMap.put("maxNum", maxNum);
		result.setDataMap(numMap);
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Page<SysParkStatisticsVo>> selectParkPublicStatisticsList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysParkStatisticsVo>> result = new ServiceResult<Page<SysParkStatisticsVo>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> numMap = new HashMap<String, Object>();
		int maxNum = 0;
		int countRecord = sysParkReaderMapper.selectParkPublicStatisticsCount(map);
		Page<SysParkStatisticsVo> page = new Page<SysParkStatisticsVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysParkStatisticsVo> list = sysParkReaderMapper.selectParkPublicStatisticsPageList(map);
			page.setList(list);
			// 最大数查询
			maxNum = sysParkReaderMapper.selectParkPublicStatisticsCountMax(map);
		}
		numMap.put("maxNum", maxNum);
		result.setDataMap(numMap);
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> delLab(Integer objectId, Integer labelId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysParkLabelWriterMapper.deleteByObjectIdAndLabelId(objectId, labelId);
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
		int i = sysParkIndustryWriterMapper.deleteByObjectIdAndIndustryId(objectId, industryId);
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
	public ServiceResult<Object> delFile(Integer fileId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysFileWriterMapper.deleteByPrimaryKey(fileId);
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
	public ServiceResult<Object> addLabelCascade(SysParkLabel parkLabel) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysParkLabelWriterMapper.insertSelective(parkLabel);
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
	public SysCollect selectSysCollect(Integer userId, Integer collectType, Integer parkId) {
		return sysCollectReaderMapper.selectByUserIdAndCollectTypeAndObjId(userId, collectType, parkId);
	}

	@Override
	public ServiceResult<Object> selectParkAllList() {
		ServiceResult<Object> result = new ServiceResult<Object>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<SysParkRegionVo> allRegion = new ArrayList<SysParkRegionVo>();
		List<SysParkRegionVo> shRegion = new ArrayList<SysParkRegionVo>();
		List<SysParkRegionVo> otherRegion = new ArrayList<SysParkRegionVo>();
		allRegion = sysParkReaderMapper.selectParkAllList();
		// 上海、域外提取
		for (SysParkRegionVo region : allRegion) {
			if (region.getRegionType() != null && region.getRegionType() == 1) {
				shRegion.add(region);
			} else if (region.getRegionType() != null && region.getRegionType() == 2) {
				otherRegion.add(region);
			}
		}
		map.put("全部", allRegion);
		map.put("上海", shRegion);
		map.put("域外", otherRegion);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setDataMap(map);
		return result;
	}

	@Override
	public ServiceResult<SysWay> addWay(SysWay sysWay) {
		ServiceResult<SysWay> result = new ServiceResult<SysWay>();
		int i = sysWayWriterMapper.insertSelective(sysWay);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			result.setData(sysWay);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> delWay(Integer wayId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysWayWriterMapper.deleteByPrimaryKey(wayId);
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
	public ServiceResult<Object> uploadFileParkVideoImage(SysFile file) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysFileWriterMapper.updateByPrimaryKeySelective(file);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			SysImages images = new SysImages();
			images.setImgId(file.getImgId());
			images.setImgState(1);
			sysImagesWriterMapper.updateByPrimaryKeySelective(images);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE_DEL);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> delUman(Integer parkUmanagerId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysParkUmanagerWriterMapper.deleteByPrimaryKey(parkUmanagerId);
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
	public ServiceResult<Object> addUManager(SysParkUmanager parkUmanager) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysParkUmanager sysParkUmanager = sysParkUmanagerReaderMapper.selectByParkIdAndUserAccount(parkUmanager.getParkId(), parkUmanager.getUserAccount());
		if (sysParkUmanager != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加");
			return result;
		}
		int i = sysParkUmanagerWriterMapper.insertSelective(parkUmanager);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			String resultStr=HttpURLConnectionResult.resultStrCheckUser(parkUmanager.getUserAccount());
			resultStr=resultStr.replace("CN=", "");
			SysUserData data=new SysUserData();
			resultStr = resultStr.replace("CN=", "");
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(parkUmanager.getUserAccount());
				sysUmanager.setUmanagerId(parkUmanager.getParkUmanagerId());
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
