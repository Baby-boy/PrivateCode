package com.glanway.ctrlhr.dao.perfStall;

import java.util.List;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.perfSatll.PerfSatll;
import com.glanway.ctrlhr.entity.vo.SimplePerfStall;

public interface PerfStallDao  extends BaseDao<PerfSatll>{
	List<SimplePerfStall> findSimpleByJobGradeId(String jobGradeId);
}