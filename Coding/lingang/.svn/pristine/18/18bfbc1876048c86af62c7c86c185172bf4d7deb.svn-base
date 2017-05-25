package com.lingang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysFile;
import com.lingang.api.service.SysFileService;
import com.lingang.core.persistence.reader.SysFileReaderMapper;
import com.lingang.core.persistence.writer.SysFileWriterMapper;

@Service("sysFileService")
public class SysFileServiceImpl implements SysFileService {
	
	@Resource
	private SysFileReaderMapper sysFileReaderMapper;

	@Resource
	private SysFileWriterMapper sysFileWriterMapper;

	@Override
	public ServiceResult<SysFile> addSysFile(SysFile file) {
		ServiceResult<SysFile> result = new ServiceResult<SysFile>();
		SysFile newFile=null;
		int i =0;
		//更新与添加
		if (file.getFileRemark() != null && !file.getFileRemark().equals("") && !file.getFileRemark().equals("0")) {
			newFile=sysFileReaderMapper.selectByPrimaryKey(Integer.parseInt(file.getFileRemark()));
			if(newFile==null){
				result.setStateCode(StateCodeConstant.ERROR_CODE);
				result.setMessage("系统异常");
				return result;
			}
			newFile.setFileTitle(file.getFileTitle());
			newFile.setFileAddress(file.getFileAddress());
			newFile.setUpdateTime(file.getUpdateTime());
			newFile.setFileSize(file.getFileSize());
			i = sysFileWriterMapper.updateByPrimaryKeySelective(newFile);
		}else{
			file.setFileRemark(null);
			i = sysFileWriterMapper.insertSelective(file);
		}
		if (i == 1) {
			result.setData(file);
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
		}
		return result;
	}

}
