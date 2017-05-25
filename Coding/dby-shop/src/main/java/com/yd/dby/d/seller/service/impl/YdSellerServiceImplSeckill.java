package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdShopSetting;
import com.yd.dby.b.shop.mapper.YdShopMapperSetting;
import com.yd.dby.d.seller.entity.YdSellerDepot;
import com.yd.dby.d.seller.entity.YdSellerSeckillGoods;
import com.yd.dby.d.seller.entity.YdSellerStore;
import com.yd.dby.d.seller.mapper.YdSellerMapperGoods;
import com.yd.dby.d.seller.mapper.YdSellerMapperSeckill;
import com.yd.dby.d.seller.service.YdSellerServiceSeckill;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.date.YdUtilDate;


@Service("_YdSellerServiceSeckill")
public class YdSellerServiceImplSeckill implements YdSellerServiceSeckill {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private StringRedisTemplate redis;
	
	@Autowired
	private YdSellerMapperSeckill ydSellerMapperSeckill;
	
	@Autowired
	private YdSellerMapperGoods ydSellerMapperGoods;
	
	@Autowired
	private YdShopMapperSetting ydShopMapperSetting;

	@Override
	public Object lists(HashMap<String, Object> map) {
		map.put("storeId", session.getAttribute("store_id"));
		List<YdSellerSeckillGoods> ydSellerSeckillGoods = ydSellerMapperSeckill.lists(map);
		
		for (YdSellerSeckillGoods ydSellerSeckillGoodsItem : ydSellerSeckillGoods) {
			if ( ydSellerSeckillGoodsItem.getStart_time() != null ) {
				ydSellerSeckillGoodsItem.setStart_time( YdUtilDate.stampToDateDay( ydSellerSeckillGoodsItem.getStart_time() ) );
				ydSellerSeckillGoodsItem.setEnd_time( YdUtilDate.stampToDateDay( ydSellerSeckillGoodsItem.getEnd_time() ) );
			}
		}
		
		return ydSellerSeckillGoods;
	}

	
	/**
	 * 取消
	 */
	@Override
	public Object cancel(Integer sgId) {
		Integer storeId = Integer.parseInt( session.getAttribute("store_id").toString() );
		try {
			YdSellerSeckillGoods ydSellerSeckillGoods = ydSellerMapperSeckill.info(sgId, storeId);
			if ( ydSellerSeckillGoods == null ) {
				return YdUtilResponse.fail("参数错误");
			}
			if ( !ydSellerSeckillGoods.getSg_state().equals(1) ) {
				return YdUtilResponse.fail("参数错误");
			}
			ydSellerMapperSeckill.delete(sgId, storeId);
			return YdUtilResponse.success(null, "操作成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("操作失败");
		}
	}


	/**
	 * 添加秒杀商品
	 */
	@Override
	public Object store(Integer depotId, float price, Integer num) {
		try{
			YdSellerSeckillGoods ydSellerSeckillGoods = new YdSellerSeckillGoods();
			YdSellerStore ydSellerStore = (YdSellerStore) session.getAttribute("store");
			Integer storeId = Integer.parseInt( session.getAttribute("store_id").toString() );
			
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("id", depotId);
			@SuppressWarnings("all")
			HashMap<String, Object> goodsDepotData = (HashMap<String, Object>) ydSellerMapperGoods.find(whereMap);
			
			if ( !goodsDepotData.get("depotType").toString().equals("1") ) {
				return YdUtilResponse.fail("商品已被锁定，不能作为秒杀商品");
			}
			
			if ( price >= Float.parseFloat( goodsDepotData.get("depotPrice").toString() ) ) {
				return YdUtilResponse.fail("秒杀价格必须小于商品价格");
			}
			
			if ( num >= Integer.parseInt( goodsDepotData.get("depotStock").toString() ) ) {
				return YdUtilResponse.fail("秒杀数量必须小于商品库存");
			}
			
			YdShopSetting ydShopSetting = ydShopMapperSetting.info("seckill_store_num");
			if ( ydSellerMapperSeckill.total(storeId) >= Integer.parseInt( ydShopSetting.getValue() ) ) {
				return YdUtilResponse.fail("秒杀商品数量已经达到上线");
			}
			
			ydSellerSeckillGoods.setSeckill_id(0);
			ydSellerSeckillGoods.setDepot_id(depotId);
			ydSellerSeckillGoods.setStore_id( storeId );
			ydSellerSeckillGoods.setStore_name( ydSellerStore.getStore_name() );
			ydSellerSeckillGoods.setSg_sort(255);
			ydSellerSeckillGoods.setSg_apply_time( YdUtilDate.currentMillisString() );
			ydSellerSeckillGoods.setSg_state(1);
			
			ydSellerMapperSeckill.store(ydSellerSeckillGoods);
			
			// 修改商品活动信息
			YdSellerDepot ydSellerDepot = new YdSellerDepot();
			ydSellerDepot.setDepot_id(depotId);
			ydSellerDepot.setDepot_type(5);
			ydSellerDepot.setDepot_min_bid(price);
			ydSellerDepot.setDepot_pre_stock(num);
			ydSellerMapperGoods.updateDepotActivity(ydSellerDepot);
			
				return YdUtilResponse.success(null, "提交成功");
			} catch (Exception e) {
				return YdUtilResponse.fail("提交失败");
			}
	}

}