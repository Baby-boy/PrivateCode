package com.glanway.ctrlhr.service.perfStall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.perfStall.PerfStallDao;
import com.glanway.ctrlhr.entity.vo.SimplePerfStall;
import com.glanway.ctrlhr.service.perfStall.PerfStallService;

@Transactional
@Service("perfStallService")
public class PerfStallServiceImpl implements PerfStallService {
	@Autowired
	private PerfStallDao perfStallDao;
	@Override
	public List<SimplePerfStall> findSimplePerfStall(String jobGradeId) {
		return perfStallDao.findSimpleByJobGradeId(jobGradeId);
	}


}
