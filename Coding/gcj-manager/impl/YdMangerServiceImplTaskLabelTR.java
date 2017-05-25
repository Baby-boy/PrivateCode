package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskLabelTR;
import com.yd.gcj.mapper.YdMangerMapperTaskLabelTR;
import com.yd.gcj.service.YdMangerServiceTaskLabelTR;

@Service("YdMangerServiceTaskLabelTR")
public class YdMangerServiceImplTaskLabelTR implements YdMangerServiceTaskLabelTR{
	
	@Autowired
	private YdMangerMapperTaskLabelTR ydMangerMapperTaskLabel;
	
	@Override	
	public List<YdMangerTaskLabelTR> $queryByTid(Integer tasktr_tid) {
		
		return ydMangerMapperTaskLabel.$queryByTid(tasktr_tid);
	}

	@Override
	public YdMangerTaskLabelTR $queryById(Integer tasktr_id) {
		
		return ydMangerMapperTaskLabel.$queryById(tasktr_id);
	}

	@Override
	public Integer $insert(YdMangerTaskLabelTR tasktr) {
		
		return ydMangerMapperTaskLabel.$insert(tasktr);
	}

	@Override
	public Integer $delete(Integer tasktr_tid, Integer tasktr_tlid) {
		return ydMangerMapperTaskLabel.$delete(tasktr_tid, tasktr_tlid);
	}
	
}
