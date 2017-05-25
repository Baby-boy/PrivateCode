package com.lingang.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysBasics;
import com.lingang.api.domain.para.SysBasicsPara;
import com.lingang.api.domain.vo.SysBasicsVo;
import com.lingang.api.service.SysBasicsService;
import com.lingang.core.persistence.reader.SysBasicsReaderMapper;
import com.lingang.core.persistence.writer.SysBasicsWriterMapper;

@Service("sysBasicsService")
public class SysBasicsServiceImpl implements SysBasicsService {

	@Resource
	private SysBasicsReaderMapper sysBasicsReaderMapper;

	@Resource
	private SysBasicsWriterMapper sysBasicsWriterMapper;

	@Override
	public ServiceResult<List<SysBasics>> selectSysBasics() {
		ServiceResult<List<SysBasics>> result = new ServiceResult<>();
		List<SysBasics> list = sysBasicsReaderMapper.selectSysBasics();
		result.setData(list);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Page<SysBasics>> selectSysBasicsPageList(SysBasicsPara para) {
		ServiceResult<Page<SysBasics>> result = new ServiceResult<Page<SysBasics>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();

		Integer basicsType = para.getBasicsType();
		Integer basicsState = para.getBasicsState();
		map.put("basicsType", basicsType);
		map.put("basicsState", basicsState);
		int countRecord = sysBasicsReaderMapper.selectSysBasicsCount(map);
		Page<SysBasics> page = new Page<SysBasics>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", para.getOnePageCount());
			List<SysBasics> list = sysBasicsReaderMapper.selectSysBasicsPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 修改基础类型设置
	 */
	@Override
	public ServiceResult<SysBasics> updateSysBasics(SysBasics basics) {

		ServiceResult<SysBasics> result = new ServiceResult<SysBasics>();

//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String stringDate = sdf.format(date);
//		Date parseDate;
//		try {
//			parseDate = sdf.parse(stringDate);
//			basics.setUpdateTime(parseDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		int i = sysBasicsWriterMapper.updateByPrimaryKeySelective(basics);
		if (i == 1) {
			// 返回信息
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			result.setData(basics);
		} else {
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	/**
	 * 添加基础设置
	 */
	@Override
	public ServiceResult<SysBasics> addSysBasics(SysBasics basics) {

		ServiceResult<SysBasics> result = new ServiceResult<SysBasics>();
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String stringDate = sdf.format(date);
//		Date parseDate;
//		try {
//			parseDate = sdf.parse(stringDate);
//			basics.setUpdateTime(parseDate);
//			basics.setCreateTime(parseDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		int i = sysBasicsWriterMapper.insertSelective(basics);
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<List<SysBasicsVo>> selectTypeServiceStatisticsList() {
		ServiceResult<List<SysBasicsVo>> result = new ServiceResult<List<SysBasicsVo>>();
		List<SysBasicsVo> list = sysBasicsReaderMapper.selectTypeServiceStatisticsList();
		result.setData(list);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysBasics>> selectBasicsListByBasicsType(Integer basicsType) {
		ServiceResult<List<SysBasics>> result = new ServiceResult<List<SysBasics>>();
		List<SysBasics> list = sysBasicsReaderMapper.selectBasicsListByBasicsType(basicsType);
		result.setData(list);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<List<SysBasicsVo>> selectTypePublicStatisticsList() {
		ServiceResult<List<SysBasicsVo>> result = new ServiceResult<List<SysBasicsVo>>();
		List<SysBasicsVo> list = sysBasicsReaderMapper.selectTypePublicStatisticsList();
		result.setData(list);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}
}
