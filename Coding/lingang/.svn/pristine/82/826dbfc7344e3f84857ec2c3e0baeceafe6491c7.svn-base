package com.lingang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.service.SysImagesService;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;

@Service("sysImagesService")
public class SysImagesServiceImpl implements SysImagesService {

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Override
	public ServiceResult<SysImages> addSysImages(SysImages images) {
		ServiceResult<SysImages> result = new ServiceResult<SysImages>();
		int i = sysImagesWriterMapper.insertSelective(images);
		if (i == 1) {
			result.setData(images);
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
		}
		return result;
	}

	@Override
	public ServiceResult<SysImages> selectSysImagesByImgTypeAndObjId(Integer imgType, Integer objId) {
		ServiceResult<SysImages> result = new ServiceResult<SysImages>();
		SysImages images = sysImagesReaderMapper.selectSysImagesByImgTypeAndObjId(imgType, objId);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(images);
		return result;
	}

	@Override
	public SysImages selectSysImagesByImgId(Integer imgId) {
		// ServiceResult<SysImages> result=new ServiceResult<SysImages>();
		return sysImagesReaderMapper.selectByPrimaryKey(imgId);
	}

}
