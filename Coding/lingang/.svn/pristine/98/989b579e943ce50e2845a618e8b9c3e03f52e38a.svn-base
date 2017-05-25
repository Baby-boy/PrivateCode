package com.lingang.core.persistence.writer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysReply;

public interface SysReplyWriterMapper {
    int deleteByPrimaryKey(@Param("replyId") List<?> replyId);

    int insert(SysReply record);

    int insertSelective(SysReply record);

    int updateByPrimaryKeySelective(SysReply record);

    int updateByPrimaryKeyWithBLOBs(SysReply record);

    int updateByPrimaryKey(SysReply record);
    int addSysReply(@Param("reply")SysReply reply);
}