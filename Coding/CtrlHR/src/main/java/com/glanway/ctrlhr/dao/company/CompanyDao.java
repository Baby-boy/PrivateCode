package com.glanway.ctrlhr.dao.company;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.company.Company;
import com.glanway.ctrlhr.entity.vo.SimpleCompanyVo;

public interface CompanyDao extends BaseDao<Company>{
	List<SimpleCompanyVo> findByParent(@Param("parentId") Long parentId);
	
	SimpleCompanyVo findOne(Long id);
	
}