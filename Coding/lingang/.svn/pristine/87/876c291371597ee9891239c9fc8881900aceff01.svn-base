package com.lingang.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysAssort;
import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.para.CompanyPara;
import com.lingang.api.service.SysCompanyService;
import com.lingang.core.persistence.reader.SysAssortReaderMapper;
import com.lingang.core.persistence.reader.SysCompanyReaderMapper;
import com.lingang.core.persistence.writer.SysAssortWriterMapper;
import com.lingang.core.persistence.writer.SysCompanyWriterMapper;

@Service("sysCompanyService")
public class SysCompanyServiceImpl implements SysCompanyService {

	@Resource
	private SysCompanyReaderMapper sysCompanyReaderMapper;

	@Resource
	private SysCompanyWriterMapper sysCompanyWriterMapper;

	@Resource
	private SysAssortWriterMapper sysAssortWriterMapper;

	@Resource
	private SysAssortReaderMapper sysAssortReaderMapper;

	@Override
	public ServiceResult<Page<SysCompany>> selectCompanyPageList(CompanyPara para) {
		ServiceResult<Page<SysCompany>> result = new ServiceResult<Page<SysCompany>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyName", para.getCompanyName());
		int countRecord = sysCompanyReaderMapper.selectCompanyCount(map);
		Page<SysCompany> page = new Page<SysCompany>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", para.getOnePageCount());
			List<SysCompany> list = sysCompanyReaderMapper.selectCompanyPageList(map);
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysCompany> updateCompanyDetails(SysCompany company) {
		ServiceResult<SysCompany> result = new ServiceResult<SysCompany>();
		int i = sysCompanyWriterMapper.updateByPrimaryKeySelective(company);
		if (i == 1) {
			// 返回信息
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			result.setData(company);
		} else {
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ServiceResult<SysCompany> addCompanyDetails(SysCompany company) {
		ServiceResult<SysCompany> result = new ServiceResult<SysCompany>();
		int i = sysCompanyWriterMapper.insertSelective(company);
		if (i == 1) {
			// 返回信息
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			result.setData(company);
		} else {
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<SysAssort> addAssort(SysAssort assort) {
		ServiceResult<SysAssort> result = new ServiceResult<SysAssort>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assortType", assort.getAssortType());
		map.put("objId", assort.getObjId());
		int count = sysAssortReaderMapper.selectCompanyCascadeCount(assort.getAssortType(), assort.getObjId());
		if (count > 0) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经添加单位,请检查");
			return result;
		}
		int i = sysAssortWriterMapper.insertSelective(assort);
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功");
			result.setData(assort);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> delCompanyCascade(Integer assortType, Integer objId, Integer companyId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysAssortWriterMapper.deleteCompanyCascade(assortType, objId, companyId);
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("删除成功");
		} else {
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("删除失败");
		}
		return result;
	}

}
