package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdFeedback;

public interface YdFeedbackMapper {
    int deleteByPrimaryKey(Integer feedbackId);

    int insert(YdFeedback record);

    int insertSelective(YdFeedback record);

    YdFeedback selectByPrimaryKey(Integer feedbackId);

    int updateByPrimaryKeySelective(YdFeedback record);

    int updateByPrimaryKeyWithBLOBs(YdFeedback record);

    int updateByPrimaryKey(YdFeedback record);
}