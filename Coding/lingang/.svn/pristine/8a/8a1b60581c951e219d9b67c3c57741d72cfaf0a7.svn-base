package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysDownload;
import com.lingang.api.domain.vo.SysDownloadVo;

public interface SysDownloadReaderMapper {

	SysDownload selectByPrimaryKey(Integer downloadId);

	int selectSysDownloadVoCount(@Param("map") Map<String, Object> map);

	List<SysDownloadVo> selectSysDownloadVoPageList(@Param("map") Map<String, Object> map);

}