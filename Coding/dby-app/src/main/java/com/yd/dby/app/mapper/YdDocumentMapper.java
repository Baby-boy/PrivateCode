package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdDocument;

public interface YdDocumentMapper {
    int deleteByPrimaryKey(Integer docId);

    int insert(YdDocument record);

    int insertSelective(YdDocument record);

    YdDocument selectByPrimaryKey(Integer docId);

    int updateByPrimaryKeySelective(YdDocument record);

    int updateByPrimaryKeyWithBLOBs(YdDocument record);

    int updateByPrimaryKey(YdDocument record);
}