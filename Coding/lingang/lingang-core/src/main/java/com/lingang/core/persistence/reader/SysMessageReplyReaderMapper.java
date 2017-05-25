package com.lingang.core.persistence.reader;

import java.util.List;

import com.lingang.api.domain.entity.SysMessageReply;

public interface SysMessageReplyReaderMapper {

	SysMessageReply selectByPrimaryKey(Integer messageReplyId);

	List<Object> selectByMessageKey(Integer messageId);
}