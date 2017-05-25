package com.yd.dby.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.vo.ClassifyVo;
import com.yd.dby.app.mapper.YdClassifyMapper;
import com.yd.dby.app.service.YdClassifyService;

@Service("ydClassifyService")
public class YdClassifyServiceImpl implements YdClassifyService {

	@Autowired
	YdClassifyMapper ydClassifyMapper;

	@Override
	public ServiceResult<List<ClassifyVo>> selectClassifyList() {

		ServiceResult<List<ClassifyVo>> result = new ServiceResult<List<ClassifyVo>>();

		List<ClassifyVo> list = ydClassifyMapper.selectClassifyList();

		result.setData(list);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
