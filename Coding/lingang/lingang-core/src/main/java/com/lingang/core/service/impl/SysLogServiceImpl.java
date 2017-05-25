package com.lingang.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysLog;
import com.lingang.api.domain.para.SysLogPara;
import com.lingang.api.service.SysLogService;
import com.lingang.core.persistence.reader.SysLogReaderMapper;
import com.lingang.core.persistence.writer.SysLogWriterMapper;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	
	@Resource
	private SysLogReaderMapper sysLogReaderMapper;
	
	@Resource
	private SysLogWriterMapper sysLogWriterMapper;

	@Override
	public ServiceResult<Page<SysLog>> selectSysLogPageList(SysLogPara para) {
		ServiceResult<Page<SysLog>> result=new ServiceResult<Page<SysLog>>();
		//查询条件
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("managerAccount", para.getManagerAccount());
		map.put("beginTime", para.getBeginTime());
		map.put("endTime", para.getEndTime());
		int countRecord=sysLogReaderMapper.selectSysLogCount(map);
		Page<SysLog> page=new Page<SysLog>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if(countRecord>0){
			map.put("startIndex", page.getCurrentPage()-1);
			map.put("onePageCount", para.getOnePageCount());
			List<SysLog> list=sysLogReaderMapper.selectSysLogPageList(map);
			page.setList(list);
		}
		//返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> insertSysLog(String managerAccount, String logIp, Date createTime, String logContent) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		SysLog log=new SysLog();
		log.setManagerAccount(managerAccount);
		log.setLogIp(logIp);
		log.setLogContent(logContent);
		log.setCreateTime(createTime);
		sysLogWriterMapper.insertSelective(log);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

}
