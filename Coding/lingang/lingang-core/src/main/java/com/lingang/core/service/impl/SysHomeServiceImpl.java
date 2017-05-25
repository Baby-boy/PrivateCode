package com.lingang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysHome;
import com.lingang.api.service.SysHomeService;
import com.lingang.core.persistence.reader.SysHomeReaderMapper;
import com.lingang.core.persistence.writer.SysHomeWriterMapper;
@Service("sysHomeService")
public class SysHomeServiceImpl implements SysHomeService {
	
	@Resource
	private SysHomeReaderMapper sysHomeReaderMapper;
	
	@Resource
	private SysHomeWriterMapper sysHomeWriterMapper;

//	/**
//	 * 首页链接(废弃)
//	 */
//	@Override
//	public ServiceResult<Page<SysHomeVo>> selectSysNewsAll(Integer pageIndex, Integer pageSize) {
//		ServiceResult<Page<SysHomeVo>> result = new ServiceResult<Page<SysHomeVo>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		int countRecord=sysHomeReaderMapper.selectSysHomeCount();
//		Page<SysHomeVo> page = new Page<SysHomeVo>(pageIndex, countRecord, pageSize);
//		if(countRecord>0){
//			map.put("startIndex", page.getCurrentPage()-1);
//			map.put("onePageCount", pageSize);
//			List<SysHomeVo> list = sysHomeReaderMapper.selectSysHomePageList(map);
//			page.setList(list);
//		};
//		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
//		result.setData(page);
//		return result;
//	}

	@Override
	public ServiceResult<SysHome> selectSysHomeByUserId(Integer userId) {
		ServiceResult<SysHome> result=new ServiceResult<SysHome>();
		SysHome home=sysHomeReaderMapper.selectSysHomeByUserId(userId);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(home);
		return result;
	}

	@Override
	public ServiceResult<SysHome> updateSysHome(SysHome home) {
		ServiceResult<SysHome> result=new ServiceResult<SysHome>();
		int i=sysHomeWriterMapper.updateByPrimaryKeySelective(home);
		if(i==1){
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			result.setData(home);
		}else{
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		
		return result;
	}

}
