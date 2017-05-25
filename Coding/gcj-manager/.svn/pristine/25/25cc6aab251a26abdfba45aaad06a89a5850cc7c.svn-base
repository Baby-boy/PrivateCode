package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerLabel;
import com.yd.gcj.entity.vo.YdMangerLabelVo;
import com.yd.gcj.mapper.YdMangerMapperLabel;
import com.yd.gcj.service.YdMangerServiceLabel;
import com.yd.gcj.util.YdMangerLabelFactory;
@Service("YdMangerServiceTaskLabel")
public class YdMangerServiceImplTaskLabel implements YdMangerServiceLabel{
	
	@Autowired
	private YdMangerMapperLabel ydMangerMapperTaskLabel;
	
	/*@Autowired
	private ServletContext context;*/
	
	
	@Override
	public List<YdMangerLabelVo> $queryAll() {
		
		List<YdMangerLabel> taskLabels = ydMangerMapperTaskLabel.$queryAll();
		List<YdMangerLabelVo> taskLabelVos = YdMangerLabelFactory.getLabelVos(taskLabels);
		
		return taskLabelVos;
		
	}

	@Override
	public Integer $queryCountNum() {
		return ydMangerMapperTaskLabel.$queryCountNum();
	}

	@Override
	public Integer $queryCountNumByType(Integer taskl_type) {
		return ydMangerMapperTaskLabel.$queryCountNum();
	}

	@Override
	public Integer $insert(YdMangerLabel taskl) {
		return ydMangerMapperTaskLabel.$insert(taskl);
	}

	@Override
	public Integer $update(YdMangerLabel taskl) {
		Integer updateNum =ydMangerMapperTaskLabel.$update(taskl);
		return updateNum;
	}

	@Override
	public Integer $delete(Integer taskl_id) {
		return ydMangerMapperTaskLabel.$delete(taskl_id);
	}

}
