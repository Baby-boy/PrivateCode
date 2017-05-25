package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdArticle;
import com.yd.dby.b.shop.mapper.YdShopMapperArticle;

@Service("_YdShopServiceArticle")
public class YdShopServiceImplArticle {
	@Autowired
	private YdShopMapperArticle ydShopMapperArticle;

	
	/**
	 * 网站底部
	 * @return
	 */
	public Object footer() {
		return ydShopMapperArticle.footer();
	}
	
	
	/**
	 * 详情
	 */
	public Object info(Integer id) {
		return ydShopMapperArticle.info(id);
	}
	
	
	/**
	 * 首页快报
	 */
	public Object indexNews(){
		return ydShopMapperArticle.indexNews();
	}
	
	
	/**
	 * 列表
	 */
	public Object lists(YdArticle ydArticle, Integer p) {
		Integer perPage = 2;
		
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		HashMap<String, Object> request = new HashMap<String, Object>();
		if (p == null) {
			p = 1;
		}
		request.put("article_title", ydArticle.getArticle_title());
		request.put("ac_id", ydArticle.getAc_id());
		request.put("to", ( p - 1 ) * perPage );
		request.put("perPage", perPage);
		
		returnHashMap.put("data", ydShopMapperArticle.lists(request));
		Integer total = ydShopMapperArticle.total(request);
		
		returnHashMap.put("p", p);
		
		if ( total % perPage == 0 ) {
			returnHashMap.put("totalPage", Math.ceil( total / perPage ));
		} else {
			returnHashMap.put("totalPage", Math.ceil( total / perPage )+1);
		}
		return returnHashMap;
	}
}