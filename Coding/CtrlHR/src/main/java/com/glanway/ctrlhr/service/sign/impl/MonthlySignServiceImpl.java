package com.glanway.ctrlhr.service.sign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.sign.MonthlySignDao;
import com.glanway.ctrlhr.entity.para.MonthlySignPara;
import com.glanway.ctrlhr.entity.sign.MonthlySign;
import com.glanway.ctrlhr.entity.vo.MonthlySignVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.sign.MonthlySignService;

@Transactional
@Service("monthlySignService")
public class MonthlySignServiceImpl extends BaseServiceImpl<MonthlySign> implements MonthlySignService {

	@Autowired
	private MonthlySignDao monthlySignDao;

	@Override
	public Page<MonthlySignVo> findList(MonthlySignPara para) {
		int count = monthlySignDao.findListCount(para);
		Page<MonthlySignVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<MonthlySignVo> list = monthlySignDao.findList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public List<MonthlySignVo> findMany(MonthlySignPara para) {
		return monthlySignDao.findMany(para);
	}

}
