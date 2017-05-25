package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月16日 下午6:27:45
 * 
 */
public interface YdMemberMapperCtcInMsg {

	List<HashMap<String, Object>> oneCtcMsg(@Param("ctc_id") Integer ctc_id, @Param("to") Integer to);

	/**
	 * @param ctc_id
	 * @return
	 * 方法作用(查询留言回复内容)
	 */
	List<HashMap<String, Object>> querySellerMessage(@Param("ctc_id")Integer ctc_id);

}
