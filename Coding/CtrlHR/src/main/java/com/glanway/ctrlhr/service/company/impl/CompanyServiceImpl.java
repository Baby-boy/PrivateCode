package com.glanway.ctrlhr.service.company.impl;

import java.util.List;
import java.util.Map;

import org.ponly.webbase.domain.Filters;
import org.ponly.webbase.domain.Page;
import org.ponly.webbase.domain.Pageable;
import org.ponly.webbase.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.company.CompanyDao;
import com.glanway.ctrlhr.entity.company.Company;
import com.glanway.ctrlhr.entity.vo.SimpleCompanyVo;
import com.glanway.ctrlhr.service.company.CompanyService;
import com.google.common.base.Predicate;

@Transactional
@Service("companyServiceImpl")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public Company find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company findOne(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company findOne(Map<String, ?> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findMany(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findMany(Filters filters, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findMany(Filters filters, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findMany(Map<String, ?> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Company> findPage(Filters filters, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Company> findPage(Map<String, ?> paramsMap, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count(String property, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count(Filters filters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count(Map<String, ?> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(Company e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company e, String... ignoreProperties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company e, Predicate<String> propPredicate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company e, Predicate<String> propPredicate, Predicate<?> valuePredicate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Long id, String prop, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Long id, Map<String, ?> patchData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Company e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String property, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Map<String, ?> params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<SimpleCompanyVo> findSimpleCompany(Long parentId) {
		if(parentId==null){
			parentId = 0l;
		}
		return companyDao.findByParent(parentId);
	}

}
