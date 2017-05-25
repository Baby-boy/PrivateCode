package com.lingang.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysIndustry;
import com.lingang.api.domain.entity.SysIndustryLabel;
import com.lingang.api.domain.entity.SysIndustryUmanager;
import com.lingang.api.domain.entity.SysParkIndustry;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysIndustryPara;
import com.lingang.api.domain.pfvo.SysIndustryPfVo;
import com.lingang.api.domain.vo.SysIndustryStatisticsVo;
import com.lingang.api.domain.vo.SysIndustryVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysIndustryService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysIndustryReaderMapper;
import com.lingang.core.persistence.reader.SysIndustryUmanagerReaderMapper;
import com.lingang.core.persistence.reader.SysParkIndustryReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysIndustryLabelWriterMapper;
import com.lingang.core.persistence.writer.SysIndustryUmanagerWriterMapper;
import com.lingang.core.persistence.writer.SysIndustryWriterMapper;
import com.lingang.core.persistence.writer.SysParkIndustryWriterMapper;

@Service("sysIndustryService")
public class SysIndustryServiceImpl implements SysIndustryService {

	@Resource
	private SysIndustryReaderMapper sysIndustryReaderMapper;

	@Resource
	private SysIndustryWriterMapper sysIndustryWriterMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Resource
	private SysParkIndustryReaderMapper sysParkIndustryReaderMapper;

	@Resource
	private SysParkIndustryWriterMapper sysParkIndustryWriterMapper;

	@Resource
	private SysIndustryLabelWriterMapper sysIndustryLabelWriterMapper;

	@Resource
	private SysIndustryUmanagerReaderMapper sysIndustryUmanagerReaderMapper;

	@Resource
	private SysIndustryUmanagerWriterMapper sysIndustryUmanagerWriterMapper;

	/**
	 * 产业集群列表
	 */
	@Override
	public ServiceResult<Page<SysIndustryVo>> selectSysIndustryList(Map<String, Object> map) {
		ServiceResult<Page<SysIndustryVo>> result = new ServiceResult<Page<SysIndustryVo>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("parkId", parkId);
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysIndustryReaderMapper.selectSysIndustryCount(map);
		Page<SysIndustryVo> page = new Page<SysIndustryVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysIndustryVo> list = sysIndustryReaderMapper.selectSysIndustryVoPageList(map);
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 产业集群详情
	 */
	@Override
	public ServiceResult<SysIndustryVo> selectByPrimaryKey(Integer industryId) {
		ServiceResult<SysIndustryVo> result = new ServiceResult<SysIndustryVo>();
		SysIndustryVo industryVo = sysIndustryReaderMapper.selectSysIndustryVoById(industryId);
		// AD域
		List<SysUmanager> ums = new ArrayList<SysUmanager>();
		for (SysIndustryUmanager spu : industryVo.getIumans()) {
			String resultStr = HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
			resultStr = resultStr.replace("CN=", "");
			SysUserData data = new SysUserData();
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(spu.getUserAccount());
				sysUmanager.setUmanagerId(spu.getIndustryUmanagerId());
				sysUmanager.setUmanagerName(user.getUserName());
				sysUmanager.setUmanagerCompany(user.getUserCompany());
				sysUmanager.setUmanagerDepartment(user.getUserDepartment());
				sysUmanager.setUmanagerEmail(user.getUserEmail());
				sysUmanager.setUmanagerTel(user.getUserTel());
				sysUmanager.setUmanagerMobile(user.getUserMobile());
				ums.add(sysUmanager);
			}
		}
		industryVo.setIumans(null);
		industryVo.setUmanagers(ums);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(industryVo);
		return result;
	}

	@Override
	public ServiceResult<Page<SysIndustry>> selectIndustryList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysIndustry>> result = new ServiceResult<Page<SysIndustry>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		int countRecord = sysIndustryReaderMapper.selectCount(map);
		Page<SysIndustry> page = new Page<SysIndustry>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysIndustry> list = sysIndustryReaderMapper.selectIndustryList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Page<SysIndustryPfVo>> selectIndustryPfVoPageList(SysIndustryPara para) {
		ServiceResult<Page<SysIndustryPfVo>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("industryTitleKeywords", para.getIndustryTitleKeywords());
		map.put("industryState", para.getIndustryState());
		int countRecord = sysIndustryReaderMapper.selectIndustryPfVoCount(map);
		Page<SysIndustryPfVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			List<SysIndustryPfVo> list = sysIndustryReaderMapper.selectIndustryPfVoPageList(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);

		return result;
	}

	@Override
	public ServiceResult<Page<SysIndustryStatisticsVo>> selectIndustryStatisticsList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysIndustryStatisticsVo>> result = new ServiceResult<Page<SysIndustryStatisticsVo>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> numMap = new HashMap<String, Object>();
		int maxNum = 0;
		int countRecord = sysIndustryReaderMapper.selectIndustryStatisticsCount(map);
		Page<SysIndustryStatisticsVo> page = new Page<SysIndustryStatisticsVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysIndustryStatisticsVo> list = sysIndustryReaderMapper.selectIndustryStatisticsPageList(map);
			page.setList(list);
			// 最大数查询
			maxNum = sysIndustryReaderMapper.selectIndustryStatisticsCountMax(map);
		}
		numMap.put("maxNum", maxNum);
		result.setDataMap(numMap);
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Object> updateIndustryDetails(SysIndustry industry) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysIndustryWriterMapper.updateByPrimaryKeySelective(industry);

		if (industry.getMaxImgId() != null) {
			SysImages maxImg = sysImagesReaderMapper.selectByPrimaryKey(industry.getMaxImgId());
			if (maxImg.getImgState() != 1) {
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		}
		if (industry.getMinImgId() != null) {
			SysImages minImg = sysImagesReaderMapper.selectByPrimaryKey(industry.getMinImgId());
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
	public ServiceResult<Object> addIndustryDetails(SysIndustry industry) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysIndustryWriterMapper.insertSelective(industry);
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功!");
			result.setData(industry);
			if (industry.getMaxImgId() != null) {
				SysImages maxImg = new SysImages();
				maxImg.setImgId(industry.getMaxImgId());
				maxImg.setObjId(industry.getIndustryId());
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
			if (industry.getMinImgId() != null) {
				SysImages minImg = new SysImages();
				minImg.setImgId(industry.getMinImgId());
				minImg.setObjId(industry.getIndustryId());
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
	public ServiceResult<Object> addParkIndustryCascade(SysParkIndustry parkIndustry) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysParkIndustryWriterMapper.insertSelective(parkIndustry);
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
	public ServiceResult<Object> addLabelCascade(SysIndustryLabel industryLabel) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysIndustryLabelWriterMapper.insertSelective(industryLabel);
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
		int i = sysIndustryLabelWriterMapper.deleteByObjectIdAndLabelId(objectId, labelId);
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
	public ServiceResult<Object> delUman(Integer industryUmanagerId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysIndustryUmanagerWriterMapper.deleteByPrimaryKey(industryUmanagerId);
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
	public ServiceResult<Object> addUManager(SysIndustryUmanager industryUmanager) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysIndustryUmanager sysIndustryUmanager = sysIndustryUmanagerReaderMapper.selectByIndustryIdAndUserAccount(industryUmanager.getIndustryId(), industryUmanager.getUserAccount());
		if (sysIndustryUmanager != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加");
			return result;
		}
		int i = sysIndustryUmanagerWriterMapper.insertSelective(industryUmanager);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			String resultStr=HttpURLConnectionResult.resultStrCheckUser(industryUmanager.getUserAccount());
			resultStr=resultStr.replace("CN=", "");
			SysUserData data = new SysUserData();
			if (!resultStr.equals("")) {
				data = JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user = data.getData().get(0);
				SysUmanager sysUmanager = new SysUmanager();
				sysUmanager.setUserAccount(industryUmanager.getUserAccount());
				sysUmanager.setUmanagerId(industryUmanager.getIndustryUmanagerId());
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
