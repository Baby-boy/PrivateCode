package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperStoreLink;
import com.yd.dby.d.seller.service.YdSellerServiceStoreLink;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月14日 上午11:36:51
 * 
 */
@Service("_YdServiceLinkStore")
public class YdSellerServiceImplStoreLink implements YdSellerServiceStoreLink {

	@Autowired
	private YdSellerMapperStoreLink ydSellerMapperLinkStore;

	@Autowired
	private HttpSession session;

	// 根据store_id查询所有的友情店铺
	@Override
	public List<HashMap<String, Object>> stores(HashMap<String, Object> request) {
		return ydSellerMapperLinkStore.stores(request);
	}

	// 卖家中心,友情链接首页
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id"));
		map.put("data", ydSellerMapperLinkStore.stores(request));
		map.put("request", request);
		return map;
	}

	// 卖家中心,保存新增友情链接
	@Override
	public Map<String, Object> store(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id"));
		request.put("data", ydSellerMapperLinkStore.store(request));
		return index(request);
	}

	// 卖家中心,修改友情链接
	@Override
	public Map<String, Object> edit(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id"));
		map.put("data", ydSellerMapperLinkStore.edit(request));
		return map;
	}

	// 卖家中心,保存修改友情链接
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id"));
		request.put("data", ydSellerMapperLinkStore.update(request));
		return index(request);
	}

	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id"));
		request.put("delete", ydSellerMapperLinkStore.destroy(request));
		return index(request);
	}

}
