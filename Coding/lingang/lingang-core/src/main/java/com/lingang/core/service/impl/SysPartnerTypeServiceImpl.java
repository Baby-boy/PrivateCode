package com.lingang.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.para.SysPartnerTypePara;
import com.lingang.api.service.SysPartnerTypeService;
import com.lingang.core.persistence.reader.SysPartnerTypeReaderMapper;
import com.lingang.core.persistence.writer.SysPartnerTypeWriterMapper;

@Service("sysPartnerTypeService")
public class SysPartnerTypeServiceImpl implements SysPartnerTypeService {

	@Resource
	private SysPartnerTypeReaderMapper sysPartnerTypeReaderMapper;

	@Resource
	private SysPartnerTypeWriterMapper sysPartnerTypeWriterMapper;

	@Override
	public ServiceResult<Page<SysPartnerType>> selectPartnerTypeList(Integer pageIndex, Integer pageSize) {
		ServiceResult<Page<SysPartnerType>> result = new ServiceResult<Page<SysPartnerType>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		int countRecord = sysPartnerTypeReaderMapper.selectPartnerTypeCount();
		Page<SysPartnerType> page = new Page<SysPartnerType>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPartnerType> list = sysPartnerTypeReaderMapper.selectPartnerTypeList(map);
			page.setList(list);
		}

		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Page<SysPartnerType>> selectPartnerTypePageList(SysPartnerTypePara para) {
		ServiceResult<Page<SysPartnerType>> result = new ServiceResult<Page<SysPartnerType>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", para.getTypeName());
		int countRecord = sysPartnerTypeReaderMapper.selectPartnerTypePageListCount(map);
		Page<SysPartnerType> page = new Page<SysPartnerType>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", page.getOnePageCount());
			List<SysPartnerType> list = sysPartnerTypeReaderMapper.selectPartnerTypePageList(map);
			page.setList(list);
		}

		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> updatePartnerType(SysPartnerType para) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysPartnerTypeWriterMapper.updateByPrimaryKeySelective(para);
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> addPartnerType(SysPartnerType para) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = sysPartnerTypeWriterMapper.insertSelective(para);
		if (info == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}

}
