package com.lingang.api.service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysFile;

public interface SysFileService {

	ServiceResult<SysFile> addSysFile(SysFile file);
}
