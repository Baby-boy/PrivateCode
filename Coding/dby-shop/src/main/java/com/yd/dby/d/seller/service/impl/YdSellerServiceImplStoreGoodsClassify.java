package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.entity.YdSellerStoreGoodsClassify;
import com.yd.dby.d.seller.mapper.YdSellerMapperStoreGoodsClassify;
import com.yd.dby.d.seller.service.YdSellerServiceStoreGoodsClassify;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.algorithm.YdUtilAlgorithm;


@Service("_YdSellerServiceStoreGoodsClassify")
public class YdSellerServiceImplStoreGoodsClassify implements YdSellerServiceStoreGoodsClassify {

	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerMapperStoreGoodsClassify ydWebMapperSellerStoreGoodsClassify;
	
	@Override
	public Map<String, Object> create(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("classStoreData", ydWebMapperSellerStoreGoodsClassify.one(request) );
		return map;
	}
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydWebMapperSellerStoreGoodsClassify.index(request));
		return map;
	}
	
	@Override
	public Object store(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify) {
		try {
			ydSellerStoreGoodsClassify.setStore_id( Integer.parseInt( session.getAttribute("store_id").toString() ) );
			ydWebMapperSellerStoreGoodsClassify.store(ydSellerStoreGoodsClassify);
			return YdUtilResponse.success(null, "添加成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("添加失败");
		}
	}
	
	@Override
	public Map<String, Object> edit(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("classStoreData", ydWebMapperSellerStoreGoodsClassify.one(request) );
		map.put("data", ydWebMapperSellerStoreGoodsClassify.edit(request));
		return map;
	}
	
	@Override
	public Object update(YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify) {
		try {
			if ( !ydSellerStoreGoodsClassify.getPid().equals(0) && ydWebMapperSellerStoreGoodsClassify.sonCount(ydSellerStoreGoodsClassify.getSc_id()) > 0 ) {
				return YdUtilResponse.fail("该分类下有子类，请先删除子类");
			}
			
			ydSellerStoreGoodsClassify.setStore_id( Integer.parseInt( session.getAttribute("store_id").toString() ) );
			ydWebMapperSellerStoreGoodsClassify.update(ydSellerStoreGoodsClassify);
			return YdUtilResponse.success(null, "修改成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("修改失败");
		}
	}
	
	
	@Override
	public Map<String, Object> setShow(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("data", ydWebMapperSellerStoreGoodsClassify.setShow(request));
		return index(request);
	}
	
	
	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		request.put("delete", ydWebMapperSellerStoreGoodsClassify.destroy(request));
		return index(request);
	}

}