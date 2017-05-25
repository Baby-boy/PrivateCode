package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yd.dby.c.member.entity.YdMemberCtc;

public interface YdMemberServiceCtc {

	/* 懒鱼-列表 */
	Map<String, Object> index(HashMap<String, Object> request);

	/* 懒鱼-创建 */
	Map<String, Object> create(HashMap<String, Object> request);

	/* 懒鱼-存储 */
	Object store(HashMap<String, Object> request);

	/* 懒鱼-编辑 */
	Map<String, Object> edit(HashMap<String, Object> request);

	/* 懒鱼-编辑-更新 */
	Object update(HashMap<String, Object> request);

	/* 删除 */
	Object delete(HashMap<String, Object> request);
	
	/* 懒鱼 下架*/
	Object soldOut(HashMap<String,Object> request);
	
	/* 懒鱼 下架*/
	Object grand(HashMap<String,Object> request);

	// 查询所有的分页列表
	List<YdMemberCtc> queryAllCtcGoods(Integer cc_id, Integer _search, Integer lowPrice, Integer highPrice, Integer to);

	// 查询出所有的懒鱼个数
	List<YdMemberCtc> queryTotal(Integer cc_id,Integer lowPrice, Integer highPrice);

	// 查询出单个懒鱼的详情
	HashMap<String, Object> queryOneCtcDetailById(Integer ctc_id);

	// 根据用户uid查询出所有发布的懒鱼
	List<YdMemberCtc> queryAllCtcByUid(Integer uid, Integer to);

	//取消
	Object cancel(HashMap<String, Object> request);

	Object deleteOrder(HashMap<String, Object> request);
}
