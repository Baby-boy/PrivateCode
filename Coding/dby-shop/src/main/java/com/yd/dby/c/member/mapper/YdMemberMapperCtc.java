package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.c.member.entity.YdMemberCtc;

public interface YdMemberMapperCtc {

	/**
	 * @param request
	 * @return
	 * 方法作用(我发布的)
	 */
	List<HashMap<String,Object>> index(HashMap<String, Object> request);

	Integer total(HashMap<String, Object> request);

	Integer store(HashMap<String, Object> request);

	Object info(@Param("ctc_id") Integer ctc_id);

	Integer update(HashMap<String, Object> request);

	//懒鱼  删除
	Integer delete(Integer ctc_id, Integer state);

	// 查询所有的懒鱼列表
	List<YdMemberCtc> queryAllCtcGoods(@Param("cc_id") Integer cc_id, @Param("_search") Integer _search, @Param("lowPrice") Integer lowPrice,
			@Param("highPrice") Integer highPrice, @Param("to") Integer to);

	// 查询所有的懒鱼个数
	List<YdMemberCtc> queryTotal(@Param("cc_id") Integer cc_id, @Param("lowPrice") Integer lowPrice, @Param("highPrice") Integer highPrice);

	// 查询单个懒鱼的详情
	HashMap<String, Object> queryOneCtcDetailById(Integer ctc_id);

	// 根据uid查询所有的懒鱼列表
	List<YdMemberCtc> queryAllCtcByUid(@Param("uid") Integer uid, @Param("to") Integer to);

	/**
	 * @param request
	 * @return
	 * 方法作用(懒鱼 下架)
	 */
	Integer soldOut(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(懒鱼 重新上架)
	 */
	Integer grand(HashMap<String, Object> request);
}