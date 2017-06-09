package com.glanway.ctrlhr.service.company;

import java.util.List;

import com.glanway.ctrlhr.entity.company.Company;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleCompanyVo;
import com.glanway.ctrlhr.service.BaseService;

public interface CompanyService extends BaseService<Company>{
	public List<SimpleCompanyVo> findSimpleCompany(Long parentId);
}
