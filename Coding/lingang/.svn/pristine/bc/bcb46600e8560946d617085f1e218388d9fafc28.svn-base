package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysMessage;

public interface SysMessageWriterMapper {
	int deleteByPrimaryKey(Integer messageId);

	int insert(SysMessage record);

	int insertSelective(SysMessage record);

	int updateByPrimaryKeySelective(SysMessage record);

	int updateByPrimaryKeyWithBLOBs(SysMessage record);

	int updateByPrimaryKey(SysMessage record);

	int updateMessageByKey(Integer messageId);

	void updateUserOpinionByMessageId(@Param("userId") Integer userId, @Param("messageState") Integer messageState,
			@Param("messageId") Integer messageId);

}