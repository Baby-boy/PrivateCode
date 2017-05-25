package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysFile;
import com.lingang.api.domain.vo.SysFileParkVo;
import com.lingang.api.domain.vo.SysFileVo;

public interface SysFileReaderMapper {

	SysFile selectByPrimaryKey(Integer fileId);

	int selectAllFileCount(@Param("map") Map<String, Object> map);

	List<SysFileParkVo> selectAllFilePageList(@Param("map") Map<String, Object> map);

	SysFileVo selectFileByFileId(@Param("map") Map<String, Object> map);
}