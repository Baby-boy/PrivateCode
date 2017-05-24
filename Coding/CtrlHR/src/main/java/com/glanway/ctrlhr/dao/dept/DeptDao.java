package com.glanway.ctrlhr.dao.dept;

import java.util.List;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;

public interface DeptDao extends BaseDao<Dept>{
	
    int deleteByPrimaryKey(Long id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

	List<SimpleDeptVo> findsimpleList(String sn);

	List<SimpleDeptVo> findDeptList(String sn);
}