package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskPM;
import com.yd.gcj.mapper.YdMangerMapperTaskPM;
import com.yd.gcj.service.YdMangerServiceTaskPM;

@Service("YdMangerServiceTaskPM")
public class YdMangerServiceImplTaskPM implements YdMangerServiceTaskPM{
	
	@Autowired
	private YdMangerMapperTaskPM ydMangerMapperTaskPM;
	
	@Override
	public List<YdMangerTaskPM> $queryByTid(Integer tpm_taskid) {
		return ydMangerMapperTaskPM.$queryByTid(tpm_taskid);
	}

	@Override
	public YdMangerTaskPM $queryById(Integer tpm_id) {
		return ydMangerMapperTaskPM.$queryById(tpm_id);
	}

	@Override
	public Integer $insert(YdMangerTaskPM tpm) {
		return ydMangerMapperTaskPM.$insert(tpm);
	}

	@Override
	public Integer $update(YdMangerTaskPM tpm) {
		return ydMangerMapperTaskPM.$update(tpm);
	}

	@Override
	public Integer $upateState(Integer tpm_id, Integer tpm_state, long tpm_update_time) {
		return ydMangerMapperTaskPM.$upateState(tpm_id, tpm_state, tpm_update_time);
	}

	@Override
	public Integer $delete(Integer tpm_id) {
		return ydMangerMapperTaskPM.$delete(tpm_id);
	}

}
