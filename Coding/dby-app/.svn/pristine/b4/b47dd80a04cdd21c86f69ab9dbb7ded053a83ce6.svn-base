package com.yd.dby.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdComment;
import com.yd.dby.app.mapper.YdCommentMapper;
import com.yd.dby.app.service.YdCommentService;

@Service("ydCommentService")
public class YdCommentServiceImpl implements YdCommentService {

	@Autowired
	private YdCommentMapper ydCommentMapper;

	@Override
	public ServiceResult<YdComment> insertCommentOrder(YdComment comment) {
		ServiceResult<YdComment> result = new ServiceResult<YdComment>();

		int i = ydCommentMapper.insertSelective(comment);
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
