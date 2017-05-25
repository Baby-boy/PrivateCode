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
import com.lingang.api.domain.entity.SysPartner;
import com.lingang.api.domain.entity.SysPartnerLabel;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.entity.SysPartnerUmanager;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysPartnerPara;
import com.lingang.api.domain.pfvo.SysPartnerPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerBasicsStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerTypeStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysPartnerService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.core.persistence.reader.SysCollectReaderMapper;
import com.lingang.core.persistence.reader.SysFileReaderMapper;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysPartnerReaderMapper;
import com.lingang.core.persistence.reader.SysPartnerTypeReaderMapper;
import com.lingang.core.persistence.reader.SysPartnerUmanagerReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysPartnerLabelWriterMapper;
import com.lingang.core.persistence.writer.SysPartnerUmanagerWriterMapper;
import com.lingang.core.persistence.writer.SysPartnerWriterMapper;

@Service("sysPartnerService")
public class SysPartnerServiceImpl implements SysPartnerService {

	@Resource
	private SysPartnerReaderMapper sysPartnerReaderMapper;

	@Resource
	private SysPartnerWriterMapper sysPartnerWriterMapper;

	@Resource
	private SysFileReaderMapper sysFileReaderMapper;

	@Resource
	private SysPartnerTypeReaderMapper sysPartnerTypeReaderMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Resource
	private SysCollectReaderMapper sysCollectReaderMapper;

	@Resource
	private SysPartnerLabelWriterMapper sysPartnerLabelWriterMapper;

	@Resource
	private SysPartnerUmanagerReaderMapper sysPartnerUmanagerReaderMapper;

	@Resource
	private SysPartnerUmanagerWriterMapper sysPartnerUmanagerWriterMapper;

	@Override
	public ServiceResult<Page<SysPartnerVo>> selectPartnerPageList(Map<String, Object> map) {
		ServiceResult<Page<SysPartnerVo>> result = new ServiceResult<Page<SysPartnerVo>>();
		// 查询条件
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("typeId", typeId);
		// map.put("basicsId", basicsId);
		// map.put("companyId",companyId);
		// map.put("beninTime", beninTime);
		// map.put("endTime", endTime);
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysPartnerReaderMapper.selectPartnerCount(map);
		Page<SysPartnerVo> page = new Page<>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPartnerVo> list = sysPartnerReaderMapper.selectPartnerPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysPartnerVo> selectPartnerVoByPartnerId(Integer partnerId) {
		ServiceResult<SysPartnerVo> result = new ServiceResult<SysPartnerVo>();
		SysPartnerVo partnerVo = sysPartnerReaderMapper.selectPartnerVoByPartnerId(partnerId);
		// AD域
		List<SysUmanager> ums = new ArrayList<SysUmanager>();
		for(SysPartnerUmanager spu:partnerVo.getPumans()){
			String resultStr=HttpURLConnectionResult.resultStrCheckUser(spu.getUserAccount());
			resultStr=resultStr.replace("CN=", "");
			SysUserData data=new SysUserData();
			if(!resultStr.equals("")){
				data =JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user=data.getData().get(0);
				SysUmanager sysUmanager=new SysUmanager();
				sysUmanager.setUserAccount(spu.getUserAccount());
				sysUmanager.setUmanagerId(spu.getPartnerUmanagerId());
				sysUmanager.setUmanagerName(user.getUserName());
				sysUmanager.setUmanagerCompany(user.getUserCompany());
				sysUmanager.setUmanagerDepartment(user.getUserDepartment());
				sysUmanager.setUmanagerEmail(user.getUserEmail());
				sysUmanager.setUmanagerTel(user.getUserTel());
				sysUmanager.setUmanagerMobile(user.getUserMobile());
				ums.add(sysUmanager);
			}
		}
		partnerVo.setPumans(null);
		partnerVo.setUmanagers(ums);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(partnerVo);
		return result;
	}

	@Override
	public ServiceResult<Page<SysPartnerPfVo>> selectSysPartnerPfVoPageList(SysPartnerPara para) {
		ServiceResult<Page<SysPartnerPfVo>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partnerNameKeywords", para.getPartnerNameKeywords());
		map.put("partnerState", para.getPartnerState());
		int countRecord = sysPartnerReaderMapper.selectSysPartnerPfVoCount(map);
		Page<SysPartnerPfVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			List<SysPartnerPfVo> list = sysPartnerReaderMapper.selectSysPartnerPfVoPageList(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	/****** 后台 ******/
	/**
	 * 合作伙伴置顶
	 */
	@Override
	public ServiceResult<Page<SysPartnerPfVo>> queryAllByPage(HashMap<String, Object> map) {
		ServiceResult<Page<SysPartnerPfVo>> result = new ServiceResult<>();
		Integer pageIndex = (Integer) map.get("currentPage");
		Integer pageSize = (Integer) map.get("onePageCount");
		// 获取置顶的合作伙伴的总条数
		Integer countRecord = sysPartnerReaderMapper.querySysPartnerPfVoCount(map);
		Page<SysPartnerPfVo> page = new Page<>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 分页条件
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			// 分页查询置顶的合作伙伴的信息
			List<SysPartnerPfVo> list = sysPartnerReaderMapper.queryAllByTop(map);
			page.setList(list);
		}
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public SysPartner selectByPrimaryKey(Integer partnerId) {

		return sysPartnerReaderMapper.selectByPrimaryKey(partnerId);
	}

	@Override
	public int deleteByPrimaryKey(Integer partnerId) {

		return sysPartnerWriterMapper.deleteByPrimaryKey(partnerId);
	}

	@Override
	public int insert(SysPartner record) {

		return sysPartnerWriterMapper.insert(record);
	}

	@Override
	public int insertSelective(SysPartner record) {

		return sysPartnerWriterMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysPartner record) {

		return sysPartnerWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(SysPartner record) {

		return sysPartnerWriterMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(SysPartner record) {

		return sysPartnerWriterMapper.updateByPrimaryKey(record);
	}

	@Override
	public ServiceResult<Object> updatePartnerDetails(SysPartner partner) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysPartnerWriterMapper.updateByPrimaryKeySelective(partner);

		if (partner.getLogoImgId() != null) {
			SysImages maxImg = sysImagesReaderMapper.selectByPrimaryKey(partner.getLogoImgId());
			if (maxImg.getImgState() != 1) {
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		}
		if (partner.getImgId() != null) {
			SysImages minImg = sysImagesReaderMapper.selectByPrimaryKey(partner.getImgId());
			if (minImg.getImgState() != 1) {
				minImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(minImg);
			}
		}
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功!");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败!");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addPartnerDetails(SysPartner partner) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysPartnerWriterMapper.insertSelective(partner);
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功!");
			result.setData(partner);
			if (partner.getLogoImgId() != null) {
				SysImages maxImg = new SysImages();
				maxImg.setImgId(partner.getLogoImgId());
				maxImg.setObjId(partner.getPartnerId());
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
			if (partner.getImgId() != null) {
				SysImages minImg = new SysImages();
				minImg.setImgId(partner.getImgId());
				minImg.setObjId(partner.getPartnerId());
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
	public ServiceResult<List<SysPartnerTypeStatisticsVo>> selectPartnerType() {
		ServiceResult<List<SysPartnerTypeStatisticsVo>> result = new ServiceResult<>();
		List<SysPartnerTypeStatisticsVo> sysPartnerTypeList = sysPartnerTypeReaderMapper.selectPartnerType();
		result.setData(sysPartnerTypeList);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysPartnerBasicsStatisticsVo>> selectPartnerLevel() {
		ServiceResult<List<SysPartnerBasicsStatisticsVo>> result = new ServiceResult<>();
		List<SysPartnerBasicsStatisticsVo> sysPartnerTypeList = sysPartnerReaderMapper.selectPartnerLevel();
		result.setData(sysPartnerTypeList);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysNewAddStatisticsVo>> selectNewCounts() {
		ServiceResult<List<SysNewAddStatisticsVo>> result = new ServiceResult<List<SysNewAddStatisticsVo>>();
		List<SysNewAddStatisticsVo> newAddStatisticsVoList = new ArrayList<SysNewAddStatisticsVo>();
		List<SysNewAddMonthStatisticsVo> monthList = sysPartnerReaderMapper.selectNewAddMonth();
		List<SysNewAddQuarterStatisticsVo> quarterList = sysPartnerReaderMapper.selectNewAddQuarter();
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
		currentMap.put("maxMonth", sysPartnerReaderMapper.selectNewAddMonthMax());
		currentMap.put("maxQuarter", sysPartnerReaderMapper.selectNewAddQuarterMax());
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(newAddStatisticsVoList);
		result.setDataMap(currentMap);
		return result;
	}

	@Override
	public ServiceResult<List<SysPartnerType>> selectSysPartnerType() {
		ServiceResult<List<SysPartnerType>> result = new ServiceResult<>();
		List<SysPartnerType> list = sysPartnerTypeReaderMapper.selectSysPartnerType();
		if (null != list) {
			result.setData(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public SysCollect selectSysCollect(Integer userId, Integer partnerId, Integer collectType) {
		return sysCollectReaderMapper.selectByUserIdAndCollectTypeAndObjId(userId, collectType, partnerId);
	}

	@Override
	public ServiceResult<Object> addLabelCascade(SysPartnerLabel partnerLabel) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPartnerLabelWriterMapper.insertSelective(partnerLabel);
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
		int i = sysPartnerLabelWriterMapper.deleteByObjectIdAndLabelId(objectId, labelId);
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
	public ServiceResult<List<SysCompanyVo>> selectPartnerCompanys() {
		ServiceResult<List<SysCompanyVo>> result = new ServiceResult<List<SysCompanyVo>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		int totalCount = sysPartnerReaderMapper.selectPartnerCompanyTotalCount();
		dataMap.put("totalCount", totalCount);

		List<SysCompanyVo> syslabelsVo = sysPartnerReaderMapper.selectPartnerCompanyCount();
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(syslabelsVo);
		result.setDataMap(dataMap);
		return result;
	}

	@Override
	public ServiceResult<Page<SysPartnerVo>> selectTopPartnerVoPageList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysPartnerVo>> result = new ServiceResult<Page<SysPartnerVo>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();

		int countRecord = sysPartnerReaderMapper.selectTopPartnerCount(map);
		Page<SysPartnerVo> page = new Page<SysPartnerVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPartnerVo> list = sysPartnerReaderMapper.selectTopPartnerVoPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> delUman(Integer partnerUmanagerId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPartnerUmanagerWriterMapper.deleteByPrimaryKey(partnerUmanagerId);
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
	public ServiceResult<Object> addUManager(SysPartnerUmanager partnerUmanager) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysPartnerUmanager sysPartnerUmanager = sysPartnerUmanagerReaderMapper.selectByPartnerIdAndUserAccount(partnerUmanager.getPartnerId(), partnerUmanager.getUserAccount());
		if (sysPartnerUmanager != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加");
			return result;
		}
		int i = sysPartnerUmanagerWriterMapper.insertSelective(partnerUmanager);
		if (i > 0) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			String resultStr=HttpURLConnectionResult.resultStrCheckUser(partnerUmanager.getUserAccount());
			resultStr=resultStr.replace("CN=", "");
			SysUserData data=new SysUserData();
			if(!resultStr.equals("")){
				data =JSONObject.parseObject(resultStr, SysUserData.class);
				SysUser user=data.getData().get(0);
				SysUmanager sysUmanager=new SysUmanager();
				sysUmanager.setUserAccount(partnerUmanager.getUserAccount());
				sysUmanager.setUmanagerId(partnerUmanager.getPartnerUmanagerId());
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
