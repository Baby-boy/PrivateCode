package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

public interface YdMemberServiceFavorite {

	/* 关注的商品 */
	Map<String, Object> goods(HashMap<String, Object> request);

	/* 关注的店铺 */
	Map<String, Object> store(HashMap<String, Object> request);

	/* 关注删除 */
	Map<String, Object> destroy(HashMap<String, Object> request);

	// 关注的懒鱼
	Map<String, Object> ctcs(HashMap<String, Object> request);
}
