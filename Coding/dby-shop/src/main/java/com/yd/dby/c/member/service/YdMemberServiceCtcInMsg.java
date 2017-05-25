package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月16日 下午6:15:10
 * 
 */
public interface YdMemberServiceCtcInMsg {

	List<HashMap<String, Object>> oneCtcMsg(Integer ctc_id, Integer to);

	/**
	 * @param ctc_id
	 * @return
	 * 方法作用(查询回复留言内容)
	 */
	List<HashMap<String, Object>> querySellerMessage(Integer ctc_id);
}
