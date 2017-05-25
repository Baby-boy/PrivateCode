/*package com.yd.dby.b.shop.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.model.Event;
import com.pingplusplus.model.EventData;
import com.pingplusplus.model.Webhooks;
import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperAddress;
import com.yd.dby.b.shop.mapper.YdShopMapperCoupon;
import com.yd.dby.b.shop.mapper.YdShopMapperGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperOrder;
import com.yd.dby.b.shop.mapper.YdShopMapperOrderGoods;
import com.yd.dby.b.shop.service.YdShopServicePay;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.YdUtilTokenOnlyExpire;
import com.yd.dby.utils.jwt.YdJwt;
import com.yd.dby.utils.md5.YdMd5Util;
import com.yd.dby.utils.ordersn.YdOrdersn;
import com.yd.dby.utils.pingpp.YdUtilPingpp;
import com.yd.dby.utils.pingpp.YdUtilPingppChannel;
import com.yd.dby.utils.uuid.YdUtilUUID;

@Service("_YdShopServicePay")
public class CopyOfYdShopServiceImplPay implements YdShopServicePay {
	
	@Autowired
	private YdShopServiceImplGoods ydShopServiceImplGoods;
	
	@Autowired
	private YdShopMapperGoods ydShopMapperGoods;
	
	@Autowired
	private YdShopMapperCoupon ydShopMapperCoupon;
	
	@Autowired
	private YdShopMapperAddress ydShopMapperAddress;
	
	@Autowired
	private YdShopMapperOrder ydShopMapperOrder;
	
	@Autowired
	private YdShopMapperOrderGoods ydShopMapperOrderGoods;
	
	@Autowired
	private StringRedisTemplate redis;
	
	@Autowired
	private HttpSession session;

	private Integer timeInterger = 3600;
	
	
	*//**
	 * 购物车提交 - 生成购物车秘钥
	 * @param ids
	 * @return
	 *//*
	public Object generateCart(String ids){
		try {
			String uuid = YdUtilUUID.generate();
			redis.opsForValue().set(uuid, ids, timeInterger*12, TimeUnit.SECONDS);
			return YdUtilResponse.success(uuid, "");
		} catch (Exception e) {
			return YdUtilResponse.fail("系统错误");
		}
	}
	
	
	
	*//**
	 * 生成 redis 订单
	 *//*
	public Object generateRedisOrder(String ids){
		try {
			String uuid = YdUtilUUID.generate();
			// uuid.contains(",")
			redis.opsForValue().set(uuid, ids, timeInterger*12, TimeUnit.SECONDS);
			return YdUtilResponse.success(uuid, "");
		} catch (Exception e) {
			return YdUtilResponse.fail("系统错误");
		}
	}
	
	
	*//**
	 * 确认订单页
	 * @param request
	 * @return
	 * @throws Exception 
	 *//*
	public Object orderConfirm(HashMap<String, Object> request) throws Exception {
		Integer user_id = (Integer) session.getAttribute("user_id");
		List<HashMap<String, Object>> dataGoodsList = ydShopMapperGoods.confirmOrderGoods(request);
		
		HashMap<String, Object> dataOrder = new HashMap<String, Object>();
		
		// 总金额
		float totalAmount = 0;
		
		// uuid
		String uuid = "";
		
		// Reds string[] 记录订单 uuid 号
		StringBuffer redisUuids = new StringBuffer();
		
		// Reds adhesion key
		String keyString = YdMd5Util.GetMD5Code( YdUtilUUID.generate()+""+user_id );
		
		// 保留两位小数
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		
		// 判断 redis 是否存在此用户对应此商品的数据，如果存在则删除
		if ( redis.hasKey( keyString ) ) {
			String uuidChar = redis.opsForValue().get( keyString );
			String[] uuidStrings = uuidChar.split(",");
			if(uuidStrings.length > 0){
				for (int i = 0; i < uuidStrings.length; i++) {
					redis.delete( uuidStrings[i] );
				}
			}
		}
		
		for (HashMap<String, Object> item : dataGoodsList) {
			
			// 店铺运费
			float freight = 0;
			
			// 店铺合计
			float shopSubtotal = 0;
			
			// 循环商品
			@SuppressWarnings("unchecked")
			List<HashMap<String, Object>> goodsHashMap = (List<HashMap<String, Object>>) item.get("goods");
			for (HashMap<String, Object> gitem : goodsHashMap) {
				Integer num = 1;
				gitem.put("cart_num", num);
				
				// 商品小计
				float simplePrice = num * Float.parseFloat( gitem.get("depot_price").toString() );
				gitem.put("simple_price", decimalFormat.format( simplePrice ) );
				
				// 计算总运费
				freight += Float.parseFloat( gitem.get("goods_freight").toString() );
				 
				// 店铺合计 - 商品总价
				shopSubtotal += simplePrice;
			}
			
			// 商品总价
			item.put("goodsTotalPrice", shopSubtotal);
			
			// 0 配送      &  1 自提
			item.put("deliver", 0);
			
			// 店铺运费
			item.put("freight", decimalFormat.format( freight ) );
			
			// 店铺合计
			float shopSubtotalFloat = shopSubtotal += freight;
			
			// 用户优惠券信息
			List<Object> coupObjects = ydShopMapperCoupon.listConfirmOrder(user_id, Integer.parseInt( item.get("store_id").toString() ) );
			
			// 如果有优惠券，默认选中第一个优惠券
			if ( coupObjects.size() > 0 ) {
				JSONObject couponItemHashMap = JSONObject.fromObject( coupObjects.get(0) );
				shopSubtotal -= Float.parseFloat( couponItemHashMap.get("coupon_money").toString() );
				item.put("coupon_id", couponItemHashMap.get("coupon_id"));
				item.put("coupon_price", couponItemHashMap.get("coupon_money"));
			} else {
				item.put("coupon_id", 0);
				item.put("coupon_price", 0);
			}
			
			// 店铺合计 商品总价 加上运费
			item.put("shopSubtotal", decimalFormat.format( shopSubtotal ) );
			
			// 店铺合计 - 固定的 + 运费
			item.put("shopSubtotal_fixed", shopSubtotalFloat );
			
			// 总金额
			totalAmount += shopSubtotal;
			
			// 用户优惠券
			item.put("coupon", coupObjects );
			
			// 订单号
			item.put("order_sn", YdOrdersn.get( user_id ));
			
			// 生成 uuid 订单号，用于存 redis
			uuid = YdUtilUUID.generate();
			if( redisUuids.length() > 0 ) {
				redisUuids.append(",");				
			}
			redisUuids.append(uuid);
			
			item.put("sn", uuid);
			
			redis.opsForValue().set(uuid, JSONObject.fromObject( item ).toString(), timeInterger, TimeUnit.SECONDS);
		}
		
		// redis 设置
		redis.opsForValue().set(keyString, redisUuids.toString(), timeInterger, TimeUnit.SECONDS);
		
		uuid = YdUtilUUID.generate();
		dataOrder.put("dataGoods", dataGoodsList);
		dataOrder.put("totalAmount", decimalFormat.format(  totalAmount ));
		dataOrder.put("token", YdJwt.generate( uuid ) );
		dataOrder.put("uu", uuid);
		dataOrder.put("key", keyString);
		return dataOrder;
	}
	
	
	*//**
	 * 确认订单页 - 相关操作修改订单信息
	 *//*
	public Object updateOrderConfirm(String token, String uu, String type, String value){
		try {
			// 验证token
			YdUtilTokenOnlyExpire.verification(token, uu);
			
			// 优惠 - 运送
			String[] typeStrings = type.split("\\.");
			
			// 返回数据
			HashMap<String, Object> returnMap = new HashMap<String, Object>();

			// redis 订单信息
			JSONObject orderJson = JSONObject.fromObject( redis.opsForValue().get( typeStrings[0] ) );
			
			// 返回订单号
			returnMap.put("sn", typeStrings[0]);
			
			// 店铺小计
			float shopSubtotal = Float.parseFloat( orderJson.get("shopSubtotal").toString() );
			
			DecimalFormat decimalFormat = new DecimalFormat(".00");
			
			switch (typeStrings[1]) {
				case "$deliver":	// 运送
 					// 运送金额
 					float freight = Float.parseFloat( orderJson.get("freight").toString() );
 					
 					// 是否自提     修改店铺小计
 					if ( value.equals("1") && orderJson.get("deliver").toString().equals("0") ) {
 						orderJson.put("deliver", 1);
 						shopSubtotal -= freight;
 					} else if( value.equals("0") && orderJson.get("deliver").toString().equals("1") ) {
 						orderJson.put("deliver", 0);
 						shopSubtotal += freight;
 					}
 					
 					// 修改店铺合计
 					orderJson.put("shopSubtotal", shopSubtotal);
 					orderJson.put("shopSubtotal_fixed", shopSubtotal);
 					
 					// 修改 redis 店铺订单信息
 					redis.opsForValue().set(typeStrings[0], orderJson.toString(), timeInterger, TimeUnit.SECONDS);
 					
 					returnMap.put("shopSubtotal", decimalFormat.format( shopSubtotal ) );
 					returnMap.put("freight", decimalFormat.format( freight ) );
 					
					break;
				case "$promotion":	// 优惠券
					// 店铺订单原价  算上运费
					float shopSubtotal_fixed = Float.parseFloat( orderJson.get("shopSubtotal_fixed").toString() );					
					if ( value.equals("0") ) {
						shopSubtotal = shopSubtotal_fixed;
						orderJson.put("coupon_id", 0);
						orderJson.put("coupon_price", 0);
					} else {
						// 获取订单优惠券
						JSONArray couponJson = JSONArray.fromObject( orderJson.get("coupon") );
						for (int i = 0; i < couponJson.size(); i++) {
							JSONObject couponItemHashMap = JSONObject.fromObject( couponJson.get(i) );
							
							// 获取用户选择的优惠券
							if ( couponItemHashMap.get("coupon_id").toString().equals(value) ) {
								shopSubtotal = shopSubtotal_fixed - Float.parseFloat( couponItemHashMap.get("coupon_money").toString() );
								orderJson.put("coupon_id", couponItemHashMap.get("coupon_id"));
								orderJson.put("coupon_price", couponItemHashMap.get("coupon_money"));
							}
						}
					}
					
					String shopSubtotalString = decimalFormat.format( shopSubtotal );
					
					// 修改店铺小计
 					orderJson.put("shopSubtotal", shopSubtotalString );
					
					// 修改 redis 店铺订单信息
 					redis.opsForValue().set(typeStrings[0], orderJson.toString(), timeInterger, TimeUnit.SECONDS);
 					
 					returnMap.put("shopSubtotal", shopSubtotalString );
 					
					break;
	
				default:
					break;
			}
			
			returnMap.put("token", YdJwt.generate( token ) );
			returnMap.put("uu", token );
			return YdUtilResponse.success(returnMap, "成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("失败");
		}
	}
	
	
	*//**
	 * 生成订单
	 * @param token
	 * @param uu
	 * @param key
	 * @param addressId   	地址id
	 * @param message	  	留言信息
	 * @param invoice	 	发票信息
	 * @return
	 *//*
	@Transactional(rollbackFor = Exception.class)
	public Object generateOrder(String token, String uu, String key, String addressId, String message, String invoice) {
		try {
			// 验证token
			YdUtilTokenOnlyExpire.verification(token, uu);

			// 获取用户信息
			YdSysUserFull dataUser = (YdSysUserFull) session.getAttribute("user");
			
			// 配送地址
			HashMap<String, Object> addressHashMap = ydShopMapperAddress.only(dataUser.getUser_id(), Integer.parseInt( addressId ));
			
			// 发票
			JSONObject invoiceJsonObject = JSONObject.fromObject( invoice );
			
			// 用于记录 redis 中订单金额和订单编号的信息
			String returnUu = YdUtilUUID.generate();
			HashMap<String, Object> redisOrderMap = new HashMap<String, Object>();
			
			DecimalFormat decimalFormat = new DecimalFormat(".00");
			
			// 总金额
			float totalAmount = 0;
			
			// 记录订单信息  存  redis
			List<Object> orderList = new ArrayList<Object>();
			
			YdOrder ydOrder = new YdOrder();
			
			// 商品名称
			String goodsName = "";
			// 商品简介
			String goodsBody = "";
			
			// 获取 redis key 值
			if ( redis.hasKey( key ) ) {
				String uuidChar = redis.opsForValue().get( key );
				String[] uuidStrings = uuidChar.split(",");
				if(uuidStrings.length > 0){
					for (int i = 0; i < uuidStrings.length; i++) {
						// redis 订单信息
						JSONObject orderJson = JSONObject.fromObject( redis.opsForValue().get( uuidStrings[i] ) );
						
						totalAmount += Float.parseFloat( orderJson.getString("shopSubtotal") );
						
						// 删除 redis 订单信息
						redis.delete( uuidStrings[i] );
						
						// 记录订单信息
						HashMap<String, Object> orderMap = new HashMap<String, Object>();
						orderMap.put("order_sn", orderJson.getString("order_sn"));
						orderMap.put("money", decimalFormat.format( Float.parseFloat( orderJson.getString("shopSubtotal") ) ) );
						orderMap.put("deliver", orderJson.getString("deliver").equals("0") ? "快递" : "自提");
						orderList.add( JSONObject.fromObject( orderMap ).toString() );
						
						ydOrder.setOrder_sn( orderJson.getString("order_sn") );
						ydOrder.setStore_id( orderJson.getInt("store_id") );
						ydOrder.setStore_name( orderJson.getString("store_name") );
						ydOrder.setBuyer_id( dataUser.getUser_id() );
						ydOrder.setBuyer_name( dataUser.getUser_username().toString() );
						ydOrder.setCoupon_id( Integer.parseInt( orderJson.getString("coupon_id") ) );
						ydOrder.setCoupon_price( Float.parseFloat( orderJson.getString("coupon_id") ) );
						ydOrder.setGoods_price( Float.parseFloat( orderJson.getString("goodsTotalPrice") ) );
						ydOrder.setTotal_price( Float.parseFloat( orderJson.getString("shopSubtotal") ) );
						
						// 0 为配送
						if ( orderJson.get("deliver").toString().equals("0") ) {
							if ( addressHashMap == null) {
								return YdUtilResponse.fail("地址选择有误！");
							}

							ydOrder.setTransport_price( Float.parseFloat( orderJson.getString("freight") ) );
							ydOrder.setTransport_address( addressHashMap.get("pca").toString() + addressHashMap.get("street").toString() );
						} else {
							ydOrder.setTransport_price( 0f );
							ydOrder.setTransport_address("");
						}
						
						// 发票
						ydOrder.setInvoice_type( invoiceJsonObject.getInt("type") );
						
						String invoiceNoString = "";
						if ( invoiceJsonObject.get("type").equals("2") ) {
							invoiceNoString = invoiceJsonObject.getString("no");
						}
						ydOrder.setInvoice_no( invoiceNoString );
						
						// 插入订单
						ydShopMapperOrder.store(ydOrder);
						
						// 插入订单商品
						YdOrderGoods ydOrderGoods = new YdOrderGoods();
						@SuppressWarnings("unchecked")
						List<HashMap<String, Object>> goodsHashMap = (List<HashMap<String, Object>>) orderJson.get("goods");
						JSONArray goodsJson = JSONArray.fromObject( goodsHashMap );

						
						for (int j = 0; j < goodsJson.size(); j++) {
							JSONObject goodsItemHashMap = JSONObject.fromObject( goodsJson.get( j ) );
							ydOrderGoods.setOrder_id( ydOrder.getOrder_id() );
							ydOrderGoods.setGoods_id( goodsItemHashMap.getInt("depot_id") );
							goodsName = goodsItemHashMap.getString("goods_name");
							goodsBody = goodsItemHashMap.getString("goods_name");
							ydOrderGoods.setGoods_name( goodsItemHashMap.getString("goods_name") );
							ydOrderGoods.setGoods_num( goodsItemHashMap.getInt("cart_num") );
							ydOrderGoods.setGoods_price( Float.parseFloat( goodsItemHashMap.getString("depot_price") ) );
							ydOrderGoods.setGoods_cover( goodsItemHashMap.getString("goods_cover") );
							ydOrderGoods.setGoods_pay_price( Float.parseFloat( goodsItemHashMap.getString("simple_price") ) );
							ydOrderGoods.setStore_id( ydOrder.getStore_id() );
							ydOrderGoods.setBuyer_id( dataUser.getUser_id() );
							ydOrderGoods.setGoods_original_price( Float.parseFloat( goodsItemHashMap.getString("depot_original_price") ) );
							
							ydShopMapperOrderGoods.store(ydOrderGoods);
						}
						
					}
				}
			}
			// 删除 redis key
			redis.delete(key);
			
			// 把订单信息存 redis
			redisOrderMap.put("money",  decimalFormat.format(totalAmount) );		// 订单总金额
			redisOrderMap.put("goods_name", goodsName);
			redisOrderMap.put("goods_body", goodsBody);
			redisOrderMap.put("order", orderList.toString());
			redis.opsForValue().set(returnUu, JSONObject.fromObject( redisOrderMap ).toString(), timeInterger, TimeUnit.SECONDS );
			
			// 返回数据
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("uu", returnUu);
			returnMap.put("token", YdJwt.generate( returnUu ) );
			return YdUtilResponse.success(returnMap, "成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("提交订单失败");
		}
	}
	
	
	*//**
	 * 选择支付方式页面
	 * @throws IOException 
	 *//*
	public Object paymentSelect(String uu, ServletResponse __response) throws IOException {
		HttpServletResponse response = (HttpServletResponse) __response;
		try {
			// 检查 uu 是否存在 redis
			if ( redis.hasKey( uu ) ) {
				// redis 订单信息
				JSONObject orderJson = JSONObject.fromObject( redis.opsForValue().get( uu ) );
				return orderJson;
			} else {
				response.sendRedirect("http://www.baidu.com");				
			}
		} catch (Exception e) {
			return null;			
		}
		return response;
	}
	
	
	*//**
	 * 发起支付
	 * @throws Exception 
	 *//*
	public Object payment(String key, String type) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// 判断 redis 订单信息是否存在
		if ( redis.hasKey( key ) ) {
			JSONObject orderJson = JSONObject.fromObject( redis.opsForValue().get( key ) );
			
			map.put("data", YdUtilPingpp
					.YdCharge(YdUtilPingppChannel.ALIPAY_PC_DIRECT, key, Float.parseFloat( orderJson.get("money").toString() ) * 100, orderJson.getString("goods_name"), orderJson.getString("goods_body"))
					.getCharge());
		} else {
			map.put("data", "");
		}
		return map;
		
	}
	
	
	*//**
	 * 支付成功 - 同步执行
	 *//*
	public Object paymentSuccess(){
		
		return null;
	}
	
	
	*//**
	 * 支付成功 - 异步通知
	 * @throws IOException 
	 *//*
	public Object payNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF8");
        //获取头部所有信息
//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
            // String key = (String) headerNames.nextElement();
            // String value = request.getHeader(key);
            // System.out.println(key+" "+value);
//        }
        
        // 获得 http body 内容
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String string;
        while ((string = reader.readLine()) != null) {
            buffer.append(string);
        }
        reader.close();
        // 解析异步通知数据
        Event event = Webhooks.eventParse(buffer.toString());
        
        
        if (event != null) {
        	if ("charge.succeeded".equals(event.getType())) {
        		payNotifySuccess(event.getData());
        		response.setStatus(200);
        	} else if ("refund.succeeded".equals(event.getType())) {
        		response.setStatus(200);
        	} else {
        		response.setStatus(500);
        	}
        }
		return "1";
	}
	
	
	*//**
	 * 支付成功，异步通知处理订单信息
	 * @param eventData 
	 * @return
	 *//*
	private void payNotifySuccess(EventData eventData) {
		JSONObject data = JSONObject.fromObject( eventData.getObject() );

		String orderNo = data.get("orderNo").toString();
		
		// 判断 redis 订单信息是否存在
		if ( redis.hasKey( orderNo ) ) {
			JSONObject orderJsonObject = JSONObject.fromObject( redis.opsForValue().get( orderNo ) );
			
			JSONArray orderJsonArray = JSONArray.fromObject( orderJsonObject.get("order") );
			for (int i = 0; i < orderJsonArray.size(); i++) {
				JSONObject orderItemHashMap = JSONObject.fromObject( orderJsonArray.get(i) );
				ydShopMapperOrder.paySuccess(orderItemHashMap.get("order_sn").toString());
			}
		} else {
			System.out.println("redis no");
		}
	}

	
	*//**
	 * 退款成功，异步通知处理订单信息
	 * @param eventData 
	 * @return
	 *//*
//	private Object refundSuccess(EventData eventData) {
//		
//		return null;
//	}
}
*/