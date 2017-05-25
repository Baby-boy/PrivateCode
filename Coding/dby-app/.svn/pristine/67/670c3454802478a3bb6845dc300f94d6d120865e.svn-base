package com.yd.dby.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCtcComment;
import com.yd.dby.app.mapper.YdCtcCommentMapper;
import com.yd.dby.app.service.YdCtcCommentService;

@Service("ydCtcCommentService")
public class YdCtcCommentServiceImpl implements YdCtcCommentService {
	
	@Autowired
	private YdCtcCommentMapper ydCtcCommentMapper;

	@Override
	public ServiceResult<YdCtcComment> insertCommentOrder(YdCtcComment comment) {
		ServiceResult<YdCtcComment> result = new ServiceResult<YdCtcComment>();

		int i = ydCtcCommentMapper.insertSelective(comment);
		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("评论成功");
			result.setData(comment);
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("评论失败");
			result.setData(comment);
		}

		return result;
	}

}
