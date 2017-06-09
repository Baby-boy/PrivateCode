package com.glanway.ctrlhr.service.perfStall;

import java.util.List;

import com.glanway.ctrlhr.entity.vo.SimplePerfStall;

public interface PerfStallService {
	List<SimplePerfStall> findSimplePerfStall(String jobGradeId);
}
