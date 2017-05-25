package com.yd.dby.app.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCtcComment;

public interface YdCtcCommentService {

	/**
	 * 说明: 添加订单评论
	 * 
	 * @param comment
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午2:34:50
	 */
	@Transactional
	ServiceResult<YdCtcComment> insertCommentOrder(YdCtcComment comment);
}
