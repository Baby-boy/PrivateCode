package com.lingang.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysPolicy;
import com.lingang.api.domain.para.SysPolicyPara;
import com.lingang.api.domain.vo.SysPolicyVo;
import com.lingang.api.service.SysPolicyService;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysPolicyReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysPolicyWriterMapper;

@Service("sysPolicyService")
public class SysPolicyServiceImpl implements SysPolicyService {
	@Resource
	private SysPolicyReaderMapper sysPolicyReaderMapper;
	@Resource
	private SysPolicyWriterMapper sysPolicyWriterMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	/**
	 * 查询政策列表
	 */
	@Override
	public ServiceResult<Page<SysPolicy>> selectSysPolicyAll(Map<String, Object> map) {
		ServiceResult<Page<SysPolicy>> result = new ServiceResult<Page<SysPolicy>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysPolicyReaderMapper.selectSysPolicyCount(map);
		Page<SysPolicy> page = new Page<SysPolicy>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPolicy> list = sysPolicyReaderMapper.selectSysPolicyAll(map);
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 查询政策信息
	 */
	@Override
	public ServiceResult<SysPolicyVo> selectByPrimaryKey(Integer policyId) {
		ServiceResult<SysPolicyVo> result = new ServiceResult<SysPolicyVo>();
		SysPolicyVo policy = sysPolicyReaderMapper.selectByKey(policyId);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(policy);
		return result;
	}

	/*************** 后台 *****************/

	@Override
	public JsonResult queryAll(SysPolicyPara para) {
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> map = new HashMap<>();
		map.put("policyTitle", para.getPolicyTitle());
		map.put("policyState", para.getPolicyState());

		int countRecord = sysPolicyReaderMapper.querySysPolicyCount(map);
		Page<SysPolicy> page = new Page<SysPolicy>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", para.getOnePageCount());
			List<SysPolicy> list = sysPolicyReaderMapper.queryAllByPage(map);
			for (SysPolicy sysPolicy : list) {
				String title = sysPolicy.getPolicyTitle();
				int num = 15;
				sysPolicy.setPolicyTitle(title.length() > num ? title.substring(0, num) + "..." : title);
			}
			page.setList(list);
		}
		jsonResult.setData(page);
		jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return jsonResult;
	}

	@Override
	public SysPolicy queryById(Integer policy_id) {

		return sysPolicyReaderMapper.selectByPrimaryKey(policy_id);
	}

	@Override
	public ServiceResult<Object> insert(SysPolicy policy) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPolicyWriterMapper.insertSelective(policy);
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			if (policy.getImgId() != null) {
				SysImages maxImg = new SysImages();
				maxImg.setImgId(policy.getImgId());
				maxImg.setObjId(policy.getPolicyId());
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public int del(Integer policy_id) {

		return sysPolicyWriterMapper.deleteByPrimaryKey(policy_id);
	}

	/**
	 * 修改政策详情
	 */
	@Override
	public ServiceResult<Object> updatePolicyDetails(SysPolicy policy) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysPolicyWriterMapper.updateByPrimaryKeySelective(policy);

		if (policy.getImgId() != null) {
			SysImages maxImg = sysImagesReaderMapper.selectByPrimaryKey(policy.getImgId());
			if (maxImg.getImgState() != 1) {
				maxImg.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(maxImg);
			}
		}
		/*
		 * if(policy.getImgId() !=null){ SysImages
		 * imgId=sysImagesReaderMapper.selectByPrimaryKey(park.getMinImgId());
		 * if(imgId.getImgState() !=1){ imgId.setImgState(1);
		 * sysImagesWriterMapper.updateByPrimaryKeySelective(minImg); } }
		 */
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

}
