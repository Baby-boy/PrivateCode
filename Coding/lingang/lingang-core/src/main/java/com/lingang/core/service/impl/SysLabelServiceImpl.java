package com.lingang.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.para.SysLabelPara;
import com.lingang.api.service.SysLabelService;
import com.lingang.core.persistence.reader.SysLabelReaderMapper;
import com.lingang.core.persistence.writer.SysLabelWriterMapper;
@Service("sysLabelService")
public class SysLabelServiceImpl implements SysLabelService {
	@Resource
	private SysLabelReaderMapper sysLabelReaderMapper;
	
	@Resource
	private SysLabelWriterMapper sysLabelWriterMapper;

	/**
	 * 标签：入驻企业筛选
	 */
	@Override
	public ServiceResult<Map<String,Object>> selectSysLabelByType() {
		ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysLabel> list=sysLabelReaderMapper.selectSysLabelByType();
		List<SysLabel> companyList=new ArrayList<SysLabel>();
		List<SysLabel> qualificationList=new ArrayList<SysLabel>();
		List<SysLabel> capitalLsit=new ArrayList<SysLabel>();
		List<SysLabel> situationList=new ArrayList<SysLabel>();
		List<SysLabel> CharacteristicList=new ArrayList<SysLabel>();
		for (int i = 1; i <list.size(); i++) {

			if(list.get(i).getLabelType()==3){
				SysLabel label = new SysLabel();
				label.setLabelId(list.get(i).getLabelId());
				label.setLabelName(list.get(i).getLabelName());
				companyList.add(label);
				
			};
			if(list.get(i).getLabelType()==4){
				SysLabel label = new SysLabel();
				label.setLabelId(list.get(i).getLabelId());
				label.setLabelName(list.get(i).getLabelName());
				qualificationList.add(label);
			};
			if(list.get(i).getLabelType()==5){
				SysLabel label = new SysLabel();
				label.setLabelId(list.get(i).getLabelId());
				label.setLabelName(list.get(i).getLabelName());
				capitalLsit.add(label);
			};
			if(list.get(i).getLabelType()==6){
				SysLabel label = new SysLabel();
				label.setLabelId(list.get(i).getLabelId());
				label.setLabelName(list.get(i).getLabelName());
				situationList.add(label);
			};
			if(list.get(i).getLabelType()==7){
				SysLabel label = new SysLabel();
				label.setLabelId(list.get(i).getLabelId());
				label.setLabelName(list.get(i).getLabelName());
				CharacteristicList.add(label);
				//map.put("labeltype5", label);
			};
		}
		map.put("企业性质", companyList);
		map.put("企业资质", qualificationList);
		map.put("注册资本", capitalLsit);
		map.put("上市情况", situationList);
		map.put("特色标签", CharacteristicList);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setDataMap(map);
		return result;
	}
//public static void main(String[] args) {
//	String listStr="3,4,5,6,7";
//	String[] list=listStr.split(",");
//	for(Integer s:list){
//		System.out.println(s);
//	}
//}
	/**
	 * 查询标签的列表
	 * */
	@Override
	public ServiceResult<Page<SysLabel>> selectSysLabelPageList(SysLabelPara para) {

		ServiceResult<Page<SysLabel>> result=new ServiceResult<Page<SysLabel>>();
		//查询条件
		Map<String, Object> map=new HashMap<String, Object>();
		String labelType = para.getLabelType();
		Integer labelState = para.getLabelState();
		List<Integer> typeIds = null;
		if (StringUtils.isNotBlank(labelType)) {
			String[] str = StringUtils.split(labelType, ",");
			typeIds = new ArrayList<Integer>();
			for (String id : str) {
				typeIds.add(Integer.parseInt(id));
			}
		}
		map.put("labelType", typeIds);
		map.put("labelState", labelState);
		
		int countRecord=sysLabelReaderMapper.selectSysLabelCount(map);
		Page<SysLabel> page=new Page<SysLabel>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if(countRecord>0){
			map.put("startIndex", page.getCurrentPage()-1);
			map.put("onePageCount", para.getOnePageCount());
			List<SysLabel> list=sysLabelReaderMapper.selectSysLabelPageList(map);
			page.setList(list);
		}
		//返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 修改标签
	 * */
	@Override
	public ServiceResult<SysLabel> updateSysLabel(SysLabel label) {
		
		ServiceResult<SysLabel> result = new ServiceResult<SysLabel>();
		
//		Date date = new Date();
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String stringDate = sdf.format(date);
//		Date parseDate;
//		try {
//			parseDate = sdf.parse(stringDate);
//			label.setUpdateTime(parseDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		int i=sysLabelWriterMapper.updateByPrimaryKeySelective(label);
		if(i==1){
			//返回信息
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			result.setData(label);
		}else{
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	/**
	 * 添加标签
	 * */
	@Override
	public ServiceResult<SysLabel> sysLabelAdd(SysLabel label) {
		ServiceResult<SysLabel> result=new ServiceResult<SysLabel>();
//		Date date = new Date();
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String stringDate = sdf.format(date);
//		Date parseDate;
//		try {
//			parseDate = sdf.parse(stringDate);
//			label.setUpdateTime(parseDate);
//			label.setCreateTime(parseDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		
		int i=sysLabelWriterMapper.insertSelective(label);
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
