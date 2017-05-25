package com.lingang.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysRegion;
import com.lingang.api.domain.para.SysRegionPara;
import com.lingang.api.domain.vo.SysRegionStatisticsVo;
import com.lingang.api.service.SysRegionService;
import com.lingang.core.persistence.reader.SysRegionReaderMapper;
import com.lingang.core.persistence.writer.SysRegionWriterMapper;

@Service("sysRegionService")
public class SysRegionServiceImpl implements SysRegionService{
	
	@Resource
	private SysRegionReaderMapper sysRegionReaderMapper;
	
	@Resource
	private SysRegionWriterMapper sysRegionWriterMapper;

	@Override
	public ServiceResult<Object> selectRegionAllList() {
		ServiceResult<Object> result=new ServiceResult<Object>();
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		List<SysRegion> allRegion=new ArrayList<SysRegion>();
		List<SysRegion> shRegion=new ArrayList<SysRegion>();
		List<SysRegion> otherRegion=new ArrayList<SysRegion>();
		allRegion=sysRegionReaderMapper.selectRegionAllList();
		//上海、域外提取
		for(SysRegion region:allRegion){
			if(region.getRegionType()==1){
				shRegion.add(region);
			}else{
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

	public ServiceResult<Page<SysRegion>> selectSysRegionPageList(SysRegionPara para) {
		ServiceResult<Page<SysRegion>> result=new ServiceResult<Page<SysRegion>>();
		//查询条件
		Map<String, Object> map=new HashMap<String, Object>();
		Integer regionType = para.getRegionType();
		
			map.put("regionType", regionType);
		
		
		int countRecord=sysRegionReaderMapper.selectSysRegionCount(map);
		Page<SysRegion> page=new Page<SysRegion>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if(countRecord>0){
			map.put("startIndex", page.getCurrentPage()-1);
			map.put("onePageCount", para.getOnePageCount());
			List<SysRegion> list=sysRegionReaderMapper.selectSysRegionPageList(map);
			page.setList(list);
		}
		//返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Page<SysRegionStatisticsVo>> selectRegionParkStatisticsPageList(Integer regionType,Integer pageIndex,Integer pageSize) {
		ServiceResult<Page<SysRegionStatisticsVo>> result=new ServiceResult<Page<SysRegionStatisticsVo>>();
		//查询条件
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("regionType", regionType);
		Map<String, Object> numMap = new HashMap<String, Object>();
		int maxNum=0;
		int countRecord=sysRegionReaderMapper.selectRegionParkStatisticsCount(map);
		Page<SysRegionStatisticsVo> page=new Page<SysRegionStatisticsVo>(pageIndex, countRecord, pageSize);
		if(countRecord>0){
			// 分页条件
			map.put("startIndex", page.getCurrentPage()-1);
			map.put("onePageCount", page.getOnePageCount());
			List<SysRegionStatisticsVo> list=sysRegionReaderMapper.selectRegionParkStatisticsPageList(map);
			page.setList(list);
			//最大数查询
			maxNum=sysRegionReaderMapper.selectRegionParkStatisticsCountMax(map);
		}
		numMap.put("maxNum", maxNum);
		result.setDataMap(numMap);;
		result.setData(page);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	/**
	 * 修改地区信息
	 * */
	@Override
	public ServiceResult<SysRegion> updateSysRegion(SysRegion region) {
		ServiceResult<SysRegion> result = new ServiceResult<SysRegion>();
		
//		Date date = new Date();
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String stringDate = sdf.format(date);
//		Date parseDate;
//		try {
//			parseDate = sdf.parse(stringDate);
//			region.setUpdateTime(parseDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		int i=sysRegionWriterMapper.updateByPrimaryKeySelective(region);
		if(i==1){
			//返回信息
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			result.setData(region);
		}else{
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	/**
	 * 添加地区
	 * */
	@Override
	public ServiceResult<SysRegion> addSysBasics(SysRegion region) {
		ServiceResult<SysRegion> result=new ServiceResult<SysRegion>();
//		Date date = new Date();
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String stringDate = sdf.format(date);
//		Date parseDate;
//		try {
//			parseDate = sdf.parse(stringDate);
//			region.setUpdateTime(parseDate);
//			region.setCreateTime(parseDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		
		int i=sysRegionWriterMapper.insertSelective(region);
		if(i==1){
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
		}else{
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}
}
