package com.yd.dby.d.seller.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.a.sys.service.impl.YdSysUrl;
import com.yd.dby.d.seller.entity.YdSellerDepot;
import com.yd.dby.d.seller.entity.YdSellerGoods;
import com.yd.dby.d.seller.entity.YdSellerStoreGoodsClassify;
import com.yd.dby.d.seller.mapper.YdSellerMapperClassify;
import com.yd.dby.d.seller.mapper.YdSellerMapperGoods;
import com.yd.dby.d.seller.mapper.YdSellerMapperGoodsAttribute;
import com.yd.dby.d.seller.mapper.YdSellerMapperStoreGoodsClassify;
import com.yd.dby.d.seller.service.YdSellerServiceGoods;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.json.YdUtilJson;

@Service("_YdSellerServiceGoods")
public class YdSellerServiceImplGoods implements YdSellerServiceGoods {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdSellerMapperGoods ydWebMapperSellerGoods;

	@Autowired
	private YdSellerMapperClassify ydWebMapperClassify;
	
	@Autowired
	private YdSellerMapperGoodsAttribute ydSellerMapperGoodsAttribute;

	@Autowired
	private YdSellerMapperStoreGoodsClassify ydSellerMapperStoreGoodsClassify;
	
	
	@Autowired
	private YdSysUrl ydSysUrl;

	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * 10);
		request.put("perPage", 10);
		request.put("store_id", session.getAttribute("store_id") );
		Integer total = ydWebMapperSellerGoods.total(request);

		map.put("data", ydWebMapperSellerGoods.index(request));
		map.put("goods_url", ydSysUrl.goods() );
		
		map.put("p", request.get("p"));
		if (total % 10 == 0) {
			map.put("totalPage", Math.ceil(total / 10));
		} else {
			map.put("totalPage", Math.ceil(total / 10) + 1);
		}

		return map;
	}

	@Override
	public Map<String, Object> create(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("classData", ydWebMapperClassify.one(null));
		map.put("classStoreData", ydSellerMapperStoreGoodsClassify.index(request));
		return map;
	}

	
	/**
	 * 新增商品
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object store(HashMap<String, Object> request) {
		
		request.put("p", 1);
		request.put("store_id", session.getAttribute("store_id") );
		request.put("goods_num", request.get("depot_stock"));
		request.put("goods_price", request.get("depot_price"));
		
		if ( request.get("store_id").equals("99999") ) {
			request.put("depot_type", 2);
		} else {
			request.put("depot_type", 1);
		}
		
		YdSellerGoods goods = YdUtilJson.fromMap(request, YdSellerGoods.class);
		YdSellerDepot depot = YdUtilJson.fromMap(request, YdSellerDepot.class);
		YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify = ydSellerMapperStoreGoodsClassify.info(depot.getDepot_classify11());
		
		if ( ydSellerStoreGoodsClassify.getPid() > 0 ) {
			depot.setDepot_classify12( depot.getDepot_classify11() );
			depot.setDepot_classify11( ydSellerStoreGoodsClassify.getPid() );
		} else {
			depot.setDepot_classify12(0);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
		String date = df.format(new Date());
		goods.setGoods_created_time( date );
		
		try {
			// 新增商品
			ydWebMapperSellerGoods.storeGoods(goods);
			depot.setGoods_id( goods.getGoods_id() );
			Long dateLong = (new Date().getTime()/1000);
			depot.setDepot_created_time( dateLong  );
			depot.setDepot_is_available_time( dateLong );
			// 新增库存
			ydWebMapperSellerGoods.storeDepot(depot);
			
			// 新增商品与属性
			if ( !request.get("type_id").equals("0") ) {
				
				JSONObject json = JSONObject.fromObject( request.get("goods_attr_value") );
				@SuppressWarnings("rawtypes")
				Iterator iterator = json.keys();
				
				while(iterator.hasNext()){
					String key = (String) iterator.next();
			        String value = json.getString(key);
			        
			        JSONObject attrItem = JSONObject.fromObject( value );
			        
			        HashMap<String, Object> goodsAttributeMap = new HashMap<String, Object>();
			        
			        goodsAttributeMap.put("goods_id", goods.getGoods_id());
			        goodsAttributeMap.put("depot_id", depot.getDepot_id());
			        goodsAttributeMap.put("classify_id", request.get("depot_classify3"));
			        goodsAttributeMap.put("type_id", request.get("type_id"));
			        goodsAttributeMap.put("attr_id", attrItem.get("attr_id"));
			        goodsAttributeMap.put("attr_value_id", attrItem.get("attr_value_id"));
			        
			        ydSellerMapperGoodsAttribute.store(goodsAttributeMap);
				}
			}
			
			return YdUtilResponse.success(null, "添加商品成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("添加商品失败");
		}
	}
	
	
	@Override
	public Map<String, Object> edit(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id") );
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("classData", ydWebMapperClassify.one(null));
		map.put("classStoreData", ydSellerMapperStoreGoodsClassify.index(request));
		map.put("data", ydWebMapperSellerGoods.find(request));
		
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("depot_id", request.get("id"));
		map.put("dataAttrs", ydSellerMapperGoodsAttribute.lists(whereMap));
		map.put("goods_url", ydSysUrl.goods() );
		return map;
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object update(HashMap<String, Object> request) {

		request.put("p", 1);
		request.put("store_id", session.getAttribute("store_id") );
		request.put("goods_num", request.get("depotStock"));
		request.put("goods_price", request.get("depotPrice"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		
		map.put("p", 1);
		try {
			YdSellerStoreGoodsClassify ydSellerStoreGoodsClassify = ydSellerMapperStoreGoodsClassify.info( Integer.parseInt( request.get("depot_classify11").toString() ) );
			
			if ( ydSellerStoreGoodsClassify.getPid() > 0 ) {
				request.put("depot_classify12", request.get("depot_classify11"));
				request.put("depot_classify11", ydSellerStoreGoodsClassify.getPid());
			} else {
				request.put("depot_classify12", "0");
			}
			
			ydWebMapperSellerGoods.updateGoods(request);

			ydWebMapperSellerGoods.updateDepot(request);
			
			// 新增商品与属性
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("depot_id", request.get("depotId"));
			ydSellerMapperGoodsAttribute.delete(whereMap);
			if ( !request.get("type_id").equals(0) ) {
				JSONObject json = JSONObject.fromObject( request.get("goods_attr_value") );
				@SuppressWarnings("rawtypes")
				Iterator iterator = json.keys();
				
				while(iterator.hasNext()){
					String key = (String) iterator.next();
			        String value = json.getString(key);
			        
			        JSONObject attrItem = JSONObject.fromObject( value );
			        
			        HashMap<String, Object> goodsAttributeMap = new HashMap<String, Object>();
			        
			        goodsAttributeMap.put("goods_id", request.get("goodsId"));
			        goodsAttributeMap.put("depot_id", request.get("depotId"));
			        goodsAttributeMap.put("classify_id", request.get("depot_classify3"));
			        goodsAttributeMap.put("type_id", request.get("type_id"));
			        goodsAttributeMap.put("attr_id", attrItem.get("attr_id"));
			        goodsAttributeMap.put("attr_value_id", attrItem.get("attr_value_id"));
			        
			        ydSellerMapperGoodsAttribute.store(goodsAttributeMap);
				}
			}
			
			return YdUtilResponse.success(null, "修改商品成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("修改商品失败");
		}
	}
	
	
	/**
	 * 商品删除
	 */
	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydWebMapperSellerGoods.destroy(request));
		return map;
	}

	
	/**
	 * 商品上下架
	 */
	@Override
	public Object showhide(Integer id, Integer up) {
		try {		
			ydWebMapperSellerGoods.showhide(id, up);
			return YdUtilResponse.success(null, "操作成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("操作失败");
		}
		
	}

	
	/**
	 * 搜索
	 */
	@Override
	public Object search(HashMap<String, Object> map) {
		try {
			return YdUtilResponse.success(index(map), "查询成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("查询失败");
		}
	}
	

}