package com.glanway.ctrlhr.service.dept;

import java.util.List;

import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.service.BaseService;

public interface DeptService extends BaseService<Dept>{

	List<SimpleDeptVo> findsimpleList(String sn);

}
