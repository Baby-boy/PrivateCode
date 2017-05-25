package com.yd.dby.b.shop.service.impl;

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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.model.Event;
import com.pingplusplus.model.EventData;
import com.pingplusplus.model.Webhooks;
import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.b.shop.entity.YdCtcOrder;
import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperAddress;
import com.yd.dby.b.shop.mapper.YdShopMapperCart;
import com.yd.dby.b.shop.mapper.YdShopMapperCoupon;
import com.yd.dby.b.shop.mapper.YdShopMapperCtc;
import com.yd.dby.b.shop.mapper.YdShopMapperCtcOrder;
import com.yd.dby.b.shop.mapper.YdShopMapperGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperOrder;
import com.yd.dby.b.shop.mapper.YdShopMapperOrderGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperStore;
import com.yd.dby.b.shop.service.YdShopServicePay;
import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.utils.YdUtilCommon;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.YdUtilTokenOnlyExpire;
import com.yd.dby.utils.date.YdUtilDate;
import com.yd.dby.utils.jwt.YdJwt;
import com.yd.dby.utils.md5.YdMd5Util;
import com.yd.dby.utils.ordersn.YdOrdersn;
import com.yd.dby.utils.pingpp.YdUtilPingpp;
import com.yd.dby.utils.pingpp.YdUtilPingppChannel;
import com.yd.dby.utils.uuid.YdUtilUUID;

@Service("_YdShopServicePay")
public class YdShopServiceImplPay implements YdShopServicePay {

    @Autowired
    private YdShopServiceImplGoods ydShopServiceImplGoods;

    @Autowired
    private YdShopMapperGoods ydShopMapperGoods;

    @Autowired
    private YdShopMapperStore ydShopMapperStore;

    @Autowired
    private YdShopMapperCart ydShopMapperCart;

    @Autowired
    private YdShopMapperCoupon ydShopMapperCoupon;

    @Autowired
    private YdShopMapperAddress ydShopMapperAddress;

    @Autowired
    private YdShopMapperOrder ydShopMapperOrder;

    @Autowired
    private YdMemberMapperOrder ydMemberMapperOrder;

    @Autowired
    private YdShopMapperCtc ydShopMapperCtc;

    @Autowired
    private YdShopMapperCtcOrder ydShopMapperCtcOrder;

    @Autowired
    private YdShopMapperOrderGoods ydShopMapperOrderGoods;

    @Autowired
    private StringRedisTemplate redis;

    @Autowired
    private HttpSession session;

    private Integer timeInterger = 3600;

    /**
     * 购物车提交 - 生成购物车秘钥
     *
     * @param ids
     * @return
     */
    // public Object generateCart(String ids){
    // try {
    // String uuid = YdUtilUUID.generate();
    // redis.opsForValue().set(uuid, ids, timeInterger*12, TimeUnit.SECONDS);
    // return YdUtilResponse.success(uuid, "");
    // } catch (Exception e) {
    // return YdUtilResponse.fail("系统错误");
    // }
    // }

    /**
     * 生成 redis 订单
     */
    // public Object generateRedisOrder(String ids){
    // try {
    // String uuid = YdUtilUUID.generate();
    // // uuid.contains(",")
    // redis.opsForValue().set(uuid, ids, timeInterger*12, TimeUnit.SECONDS);
    // return YdUtilResponse.success(uuid, "");
    // } catch (Exception e) {
    // return YdUtilResponse.fail("系统错误");
    // }
    // }

    /**
     * 生成 redis 订单
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public Object generateRedisOrder(String ids, String type) throws Exception {
        try {
            Integer user_id = (Integer) session.getAttribute("user_id");
            if (user_id == null) {
                return YdUtilResponse.fail("请先登录！！！");
            }

            // 设置购买方式
            session.setAttribute("buyType", type);

            // Redis adhesion key
            String keyString = YdMd5Util.GetMD5Code(YdUtilUUID.generate() + ids + "" + user_id);

            List<HashMap<String, Object>> dataGoodsList = null;
            if (type.equals("cart")) {
                dataGoodsList = ydShopMapperGoods.confirmOrderCart(YdUtilCommon.getSplitList(ids, ","));
            } else {
                dataGoodsList = ydShopMapperGoods.confirmOrderGoods(Integer.parseInt(ids));
            }

            for (HashMap<String, Object> item : dataGoodsList) {

                @SuppressWarnings("all")
                List<HashMap<String, Object>> goodsHashMap = (List<HashMap<String, Object>>) item.get("goods");
                for (HashMap<String, Object> gitem : goodsHashMap) {
                    if (Integer.parseInt(gitem.get("depot_stock").toString()) <= 0) {
                        return YdUtilResponse.fail("商品已经售完！！！");
                    }
                    
                    // 活动商品 - 检查活动库存
                    if ( gitem.get("depot_type").toString().equals("5") ) {
                    	if (Integer.parseInt(gitem.get("depot_pre_stock").toString()) <= 0) {
                            return YdUtilResponse.fail("商品已被抢完！！！");
                        }
                    }
                }

                if (item.get("store_user_id").equals(user_id)) {
                    return YdUtilResponse.fail("不可购买自己的商品！！！");
                }

            }

            if (ids != null) {
                // redis 设置
                redis.opsForValue().set(keyString, ids, timeInterger * 12, TimeUnit.SECONDS);
                return YdUtilResponse.success(keyString, null);
            } else {
                return YdUtilResponse.fail("对不起，参数错误！！！");
            }
        } catch (Exception e) {
            return YdUtilResponse.fail("对不起，参数错误！！！");
        }

    }

    /**
     * 确认订单页
     *
     * @throws Exception
     */
    public Object orderConfirm(String spm, HttpServletResponse response) throws Exception {
        // 判断 redis 是否存在订单信息
        if (!redis.hasKey(spm)) {
            response.sendRedirect("http://www.wadby.com");
            return null;
        }

        Integer user_id = (Integer) session.getAttribute("user_id");

        String keyString = YdMd5Util.GetMD5Code(spm);

        // 判断 redis 是否存在此用户对应此商品的数据，如果存在则删除
        if (redis.hasKey(keyString)) {
            String uuidChar = redis.opsForValue().get(keyString);
            String[] uuidStrings = uuidChar.split(",");
            if (uuidStrings.length > 0) {
                for (int i = 0; i < uuidStrings.length; i++) {
                    redis.delete(uuidStrings[i]);
                }
            }
        }

        String uuidChar = redis.opsForValue().get(spm);

        // 判断是普通提交 - 购物车提交
        List<HashMap<String, Object>> dataGoodsList = null;
        if (session.getAttribute("buyType").equals("cart")) {
            dataGoodsList = ydShopMapperGoods.confirmOrderCart(YdUtilCommon.getSplitList(uuidChar, ","));
        } else {
            dataGoodsList = ydShopMapperGoods.confirmOrderGoods(Integer.parseInt(uuidChar));
        }

        HashMap<String, Object> dataOrder = new HashMap<String, Object>();

        // 总金额
        float totalAmount = 0;

        // uuid
        String uuid = "";

        // Reds string[] 记录订单 uuid 号
        StringBuffer redisUuids = new StringBuffer();

        // 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        
        // 判断是否为活动商品  1是  2否
        Integer isActivityGoods = 2;
        
        // 订单类型
        Integer orderType = 1;

        for (HashMap<String, Object> item : dataGoodsList) {

            // 店铺运费
            float freight = 0;

            // 店铺合计
            float shopSubtotal = 0;

            // 循环商品
            @SuppressWarnings("unchecked")
            List<HashMap<String, Object>> goodsHashMap = (List<HashMap<String, Object>>) item.get("goods");
            for (HashMap<String, Object> gitem : goodsHashMap) {
            	
            	// depot_type 1:普通商品 2:养护 3:拍卖 4:团购 5:秒杀 6:普通活动
            	if (isActivityGoods.equals(2) && gitem.get("depot_type").toString().equals("5") ) {
            		isActivityGoods = 1;
            		switch (gitem.get("depot_type").toString()) {
					case "2": orderType = 5; break;
					case "3": orderType = 4; break;
					case "4": orderType = 2; break;
					case "5": orderType = 3; break;
					default: orderType = 1; break;
					}
            	}
            	
            	if (isActivityGoods.equals(1)) {
            		gitem.put("depot_price", gitem.get("depot_min_bid"));
            	}
            	
                Integer num = 1;
                if (gitem.get("cart_num") != null) {
                    num = (Integer) gitem.get("cart_num");
                }
                gitem.put("cart_num", num);

                // 商品小计
                float simplePrice = num * Float.parseFloat(gitem.get("depot_price").toString());
                gitem.put("simple_price", decimalFormat.format(simplePrice));

                // 计算总运费
                freight += Float.parseFloat(gitem.get("goods_freight").toString());

                // 店铺合计 - 商品总价
                shopSubtotal += simplePrice;
            }
            
            item.put("orderType", orderType);

            // 商品总价
            item.put("goodsTotalPrice", shopSubtotal);

            // 0 配送 & 1 自提
            item.put("deliver", 0);

            // 店铺运费
            item.put("freight", decimalFormat.format(freight));

            // 店铺合计
            float shopSubtotalFloat = shopSubtotal += freight;

            List<Object> coupObjects = null;
            if ( isActivityGoods.equals(2) ) {
            	// 用户优惠券信息
                coupObjects = ydShopMapperCoupon.listConfirmOrder(user_id, Integer.parseInt(item.get("store_id").toString()));

                // 全场优惠券
                List<Object> coupFullObjects = ydShopMapperCoupon.listConfirmOrderFull(user_id);
                
                if(coupFullObjects != null) {
                    coupObjects.addAll(coupFullObjects);
                }
            }


            // 如果有店铺优惠券，默认选中第一个优惠券
//            if (coupObjects.size() > 0) {
//                JSONObject couponItemHashMap = JSONObject.fromObject(coupObjects.get(0));
//                shopSubtotal -= Float.parseFloat(couponItemHashMap.get("coupon_money").toString());
//                item.put("coupon_id", couponItemHashMap.get("coupon_id"));
//                item.put("coupon_price", couponItemHashMap.get("coupon_money"));
//            } else {
//                item.put("coupon_id", 0);
//                item.put("coupon_price", 0);
//            }
            item.put("coupon_id", 0);
            item.put("coupon_price", 0);

            // 店铺合计 商品总价 加上运费
            item.put("shopSubtotal", decimalFormat.format(shopSubtotal));

            // 店铺合计 - 固定的 + 运费
            item.put("shopSubtotal_fixed", shopSubtotalFloat);

            // 总金额
            totalAmount += shopSubtotal;

            // 用户优惠券
            item.put("coupon", coupObjects);

            // 订单号
            item.put("order_sn", YdOrdersn.get(user_id));

            // 生成 uuid 订单号，用于存 redis
            uuid = YdUtilUUID.generate();
            if (redisUuids.length() > 0) {
                redisUuids.append(",");
            }
            redisUuids.append(uuid);

            item.put("sn", uuid);
            redis.opsForValue().set(uuid, JSONObject.fromObject(item).toString(), timeInterger, TimeUnit.SECONDS);
        }

        // redis 设置
        redis.opsForValue().set(keyString, redisUuids.toString(), timeInterger, TimeUnit.SECONDS);

        uuid = YdUtilUUID.generate();
        dataOrder.put("dataGoods", dataGoodsList);
        dataOrder.put("totalAmount", decimalFormat.format(totalAmount));
        dataOrder.put("token", YdJwt.generate(uuid));
        dataOrder.put("uu", uuid);
        dataOrder.put("key", keyString);
        return dataOrder;
    }

    /**
     * 确认订单页 - 相关操作修改订单信息
     */
    public Object updateOrderConfirm(String token, String uu, String type, String value) {
        try {
            // 验证token
            YdUtilTokenOnlyExpire.verification(token, uu);

            // 优惠 - 运送
            String[] typeStrings = StringUtils.split(type, "\\.");

            // 返回数据
            HashMap<String, Object> returnMap = new HashMap<String, Object>();

            // redis 订单信息
            JSONObject orderJson = JSONObject.fromObject(redis.opsForValue().get(typeStrings[0]));

            // 返回订单号
            returnMap.put("sn", typeStrings[0]);

            // 店铺小计
            float shopSubtotal = Float.parseFloat(orderJson.get("shopSubtotal").toString());

            DecimalFormat decimalFormat = new DecimalFormat(".00");

            switch (typeStrings[1]) {
                case "$deliver": // 运送
                    // 运送金额
                    float freight = Float.parseFloat(orderJson.get("freight").toString());

                    // 是否自提 修改店铺小计
                    if (value.equals("1") && orderJson.get("deliver").toString().equals("0")) {
                        orderJson.put("deliver", 1);
                        shopSubtotal -= freight;
                    } else if (value.equals("0") && orderJson.get("deliver").toString().equals("1")) {
                        orderJson.put("deliver", 0);
                        shopSubtotal += freight;
                    }

                    // 修改店铺合计
                    orderJson.put("shopSubtotal", shopSubtotal);
                    orderJson.put("shopSubtotal_fixed", shopSubtotal);

                    // 修改 redis 店铺订单信息
                    redis.opsForValue().set(typeStrings[0], orderJson.toString(), timeInterger, TimeUnit.SECONDS);

                    returnMap.put("shopSubtotal", decimalFormat.format(shopSubtotal));
                    returnMap.put("freight", decimalFormat.format(freight));

                    break;
                case "$promotion": // 优惠券
                    // 店铺订单原价 算上运费
                    float shopSubtotal_fixed = Float.parseFloat(orderJson.get("shopSubtotal_fixed").toString());
                    if (value.equals("0")) {
                        shopSubtotal = shopSubtotal_fixed;
                        orderJson.put("coupon_id", 0);
                        orderJson.put("coupon_price", 0);
                    } else {
                        // 获取订单优惠券
                        JSONArray couponJson = JSONArray.fromObject(orderJson.get("coupon"));
                        for (int i = 0; i < couponJson.size(); i++) {
                            JSONObject couponItemHashMap = JSONObject.fromObject(couponJson.get(i));

                            // 获取用户选择的优惠券
                            if (couponItemHashMap.get("coupon_id").toString().equals(value)) {
                                shopSubtotal = shopSubtotal_fixed - Float.parseFloat(couponItemHashMap.get("coupon_money").toString());
                                orderJson.put("coupon_id", couponItemHashMap.get("coupon_id"));
                                orderJson.put("coupon_price", couponItemHashMap.get("coupon_money"));
                            }
                        }
                    }

                    String shopSubtotalString = decimalFormat.format(shopSubtotal);

                    // 修改店铺小计
                    orderJson.put("shopSubtotal", shopSubtotalString);

                    // 修改 redis 店铺订单信息
                    redis.opsForValue().set(typeStrings[0], orderJson.toString(), timeInterger, TimeUnit.SECONDS);

                    returnMap.put("shopSubtotal", shopSubtotalString);

                    break;

                default:
                    break;
            }

            returnMap.put("token", YdJwt.generate(token));
            returnMap.put("uu", token);
            return YdUtilResponse.success(returnMap, "成功");
        } catch (Exception e) {
            return YdUtilResponse.fail("失败");
        }
    }

    /**
     * 生成订单
     *
     * @param token
     * @param uu
     * @param key
     * @param addressId 地址id
     * @param message   留言信息
     * @param invoice   发票信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Object generateOrder(String token, String uu, String key, String addressId, String message, String invoice) {
        try {
            // 验证token
            YdUtilTokenOnlyExpire.verification(token, uu);

            // 获取用户信息
            YdSysUserFull dataUser = (YdSysUserFull) session.getAttribute("user");

            // 配送地址
            HashMap<String, Object> addressHashMap = ydShopMapperAddress.only(dataUser.getUser_id(), Integer.parseInt(addressId));

            // 发票
            JSONObject invoiceJsonObject = JSONObject.fromObject(invoice);

            // 留言
            JSONObject messageJsonObject = JSONObject.fromObject(message);

            // 用于记录 redis 中订单金额和订单编号的信息
            String returnUu = YdUtilUUID.generate();
            HashMap<String, Object> redisOrderMap = new HashMap<String, Object>();

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            // 总金额
            float totalAmount = 0;

            // 记录订单信息 存 redis
            List<Object> orderList = new ArrayList<Object>();

            YdOrder ydOrder = new YdOrder();

            // 商品名称
            String goodsName = "";
            // 商品简介
            String goodsBody = "";

            // 记录所有已经使用过的优惠券ID
            List<String> couponIds = new ArrayList<>();

            // 获取 redis key 值
            if (redis.hasKey(key)) {
                String uuidChar = redis.opsForValue().get(key);
                String[] uuidStrings = uuidChar.split(",");
                if (uuidStrings.length > 0) {
                    for (int i = 0; i < uuidStrings.length; i++) {
                        // redis 订单信息
                        JSONObject orderJson = JSONObject.fromObject(redis.opsForValue().get(uuidStrings[i]));

                        totalAmount += Float.parseFloat(orderJson.getString("shopSubtotal"));

                        // 删除 redis 订单信息
                        redis.delete(uuidStrings[i]);

                        // 记录订单信息
                        HashMap<String, Object> orderMap = new HashMap<String, Object>();
                        orderMap.put("order_sn", orderJson.getString("order_sn"));
                        orderMap.put("money", decimalFormat.format(Float.parseFloat(orderJson.getString("shopSubtotal"))));
                        orderMap.put("deliver", orderJson.getString("deliver").equals("0") ? "快递" : "自提");
                        orderList.add(JSONObject.fromObject(orderMap).toString());

                        ydOrder.setOrder_sn(orderJson.getString("order_sn"));
                        ydOrder.setType( orderJson.getInt("orderType") );
                        ydOrder.setStore_id(orderJson.getInt("store_id"));
                        ydOrder.setStore_name(orderJson.getString("store_name"));
                        ydOrder.setBuyer_id(dataUser.getUser_id());
                        ydOrder.setBuyer_name(dataUser.getUser_username().toString());
                        ydOrder.setCoupon_id(Integer.parseInt(orderJson.getString("coupon_id")));
                        ydOrder.setCoupon_price(Float.parseFloat(orderJson.getString("coupon_price")));
                        ydOrder.setGoods_price(Float.parseFloat(orderJson.getString("goodsTotalPrice")));
                        ydOrder.setTotal_price(Float.parseFloat(orderJson.getString("shopSubtotal")));

                        if (!orderJson.getString("coupon_id").equals("0")) {
                            couponIds.add(orderJson.getString("coupon_id"));
                        }

                        // 0 为配送
                        if (orderJson.get("deliver").toString().equals("0")) {
                            if (addressHashMap == null) {
                                return YdUtilResponse.fail("地址选择有误！");
                            }
                            ydOrder.setTransport_price(Float.parseFloat(orderJson.getString("freight")));
                            ydOrder.setTransport_address("");

                            ydOrder.setReceipt_name(addressHashMap.get("linkman").toString());
                            ydOrder.setReceipt_mobile(addressHashMap.get("linktel").toString());
                            ydOrder.setReceipt_address(addressHashMap.get("pca").toString() + addressHashMap.get("street").toString());
                        } else { // 自提
                            HashMap<String, Object> storeMap = ydShopMapperStore.info(orderJson.getInt("store_id"));
                            ydOrder.setTransport_price(0f);
                            ydOrder.setTransport_address(storeMap.get("store_address").toString());

                            ydOrder.setReceipt_name("");
                            ydOrder.setReceipt_mobile("");
                            ydOrder.setReceipt_address("");
                        }

                        // 留言
                        ydOrder.setOrder_message(messageJsonObject.get(uuidStrings[i]).toString());

                        // 发票
                        ydOrder.setInvoice_type(invoiceJsonObject.getInt("type"));

                        String invoiceNoString = "";
                        if (invoiceJsonObject.get("type").equals("2")) {
                            invoiceNoString = invoiceJsonObject.getString("no");
                        }
                        ydOrder.setInvoice_no(invoiceNoString);

                        // 插入订单
                        ydShopMapperOrder.store(ydOrder);

                        // 插入订单商品
                        YdOrderGoods ydOrderGoods = new YdOrderGoods();
                        @SuppressWarnings("unchecked")
                        List<HashMap<String, Object>> goodsHashMap = (List<HashMap<String, Object>>) orderJson.get("goods");
                        JSONArray goodsJson = JSONArray.fromObject(goodsHashMap);

                        for (int j = 0; j < goodsJson.size(); j++) {
                            JSONObject goodsItemHashMap = JSONObject.fromObject(goodsJson.get(j));


                            // 获取商品库存
                            HashMap<String, Object> dataDepotMap = ydShopMapperGoods.info(goodsItemHashMap.getInt("depot_id"));
                            Integer depotStockInteger = Integer.parseInt(dataDepotMap.get("depot_stock").toString());
                            if (depotStockInteger <= 0) {
                                // 商品已售完
                                System.out.println(goodsItemHashMap.getString("goods_name") + "已经售完");
                                continue;
                            }


                            // 删除购物车
                            if (goodsItemHashMap.get("cart_id") != null) {
                                ydShopMapperCart.delete((Integer) goodsItemHashMap.get("cart_id"), dataUser.getUser_id());
                            }

                            // 库存减少
                            ydShopMapperGoods.updateStockMinus(goodsItemHashMap.getInt("depot_id"), goodsItemHashMap.getInt("cart_num"));
                            
                            // 如何是活动商品，活动库存同时减少
                            if ( goodsItemHashMap.getString("depot_type").equals("5") ) {
                            	ydShopMapperGoods.updatePreStockMinus(goodsItemHashMap.getInt("depot_id"), 1);
                            }

                            ydOrderGoods.setOrder_id(ydOrder.getOrder_id());
                            ydOrderGoods.setGoods_id(goodsItemHashMap.getInt("depot_id"));
                            goodsName = goodsItemHashMap.getString("goods_name");
                            goodsBody = goodsItemHashMap.getString("goods_name");
                            ydOrderGoods.setGoods_name(goodsItemHashMap.getString("goods_name"));
                            ydOrderGoods.setGoods_num(goodsItemHashMap.getInt("cart_num"));
                            ydOrderGoods.setGoods_price(Float.parseFloat(goodsItemHashMap.getString("depot_price")));
                            ydOrderGoods.setGoods_cover(goodsItemHashMap.getString("goods_cover"));
                            ydOrderGoods.setGoods_pay_price(Float.parseFloat(goodsItemHashMap.getString("simple_price")));
                            ydOrderGoods.setStore_id(ydOrder.getStore_id());
                            ydOrderGoods.setBuyer_id(dataUser.getUser_id());
                            ydOrderGoods.setGoods_original_price(Float.parseFloat(goodsItemHashMap.getString("depot_original_price")));

                            ydShopMapperOrderGoods.store(ydOrderGoods);
                        }

                    }
                }
            }
            // 删除 redis key
            redis.delete(key);

            // 删除已经使用过的优惠券
            if ( couponIds.size() > 0 ) {
            	ydShopMapperCoupon.deleteReceiveCoupon(couponIds, dataUser.getUser_id());            	
            }

            // 把订单信息存 redis
            redisOrderMap.put("money", decimalFormat.format(totalAmount)); // 订单总金额
            redisOrderMap.put("goods_name", goodsName);
            redisOrderMap.put("goods_body", goodsBody);
            redisOrderMap.put("order", orderList.toString());
            redis.opsForValue().set(returnUu, JSONObject.fromObject(redisOrderMap).toString(), timeInterger, TimeUnit.SECONDS);

            // 返回数据
            HashMap<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("uu", returnUu);
            returnMap.put("token", YdJwt.generate(returnUu));
            return YdUtilResponse.success(returnMap, "成功");
        } catch (Exception e) {
            return YdUtilResponse.fail("提交订单失败");
        }
    }

    /**
     * 选择支付方式页面
     *
     * @throws IOException
     */
    public Object paymentSelect(String uu, ServletResponse __response) throws IOException {
        HttpServletResponse response = (HttpServletResponse) __response;
        try {
            // 检查 uu 是否存在 redis
            if (redis.hasKey(uu)) {
                // redis 订单信息
                JSONObject orderJson = JSONObject.fromObject(redis.opsForValue().get(uu));
                return orderJson;
            } else {
                response.sendRedirect("http://www.baidu.com");
            }
        } catch (Exception e) {
            return null;
        }
        return response;
    }

    /**
     * 发起支付
     *
     * @throws Exception
     */
    public Object payment(String key, String type) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();

        // 判断 redis 订单信息是否存在
        if (redis.hasKey(key)) {
            JSONObject orderJson = JSONObject.fromObject(redis.opsForValue().get(key));
            session.setAttribute("payMoney", Float.parseFloat(orderJson.get("money").toString()));
            map.put("data",
                    YdUtilPingpp.YdCharge(
                            YdUtilPingppChannel.ALIPAY_PC_DIRECT, key,
                            Float.parseFloat(orderJson.get("money").toString()) * 100,
                            orderJson.getString("goods_name"),
                            orderJson.getString("goods_body"),
                            1
                    ).getCharge());

        } else {
            map.put("data", "");
        }
        return map;

    }

    /**
     * 支付成功 - 同步执行
     */
    public Object paymentSuccess() {

        return null;
    }

    /**
     * 支付成功 - 异步通知
     *
     * @throws IOException
     */
    public Object payNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        // 获取头部所有信息
        // Enumeration headerNames = request.getHeaderNames();
        // while (headerNames.hasMoreElements()) {
        // String key = (String) headerNames.nextElement();
        // String value = request.getHeader(key);
        // System.out.println(key+" "+value);
        // }

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

                System.out.println(event.getData());

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

    /**
     * 支付成功，异步通知处理订单信息
     *
     * @param eventData
     * @return
     */
    private void payNotifySuccess(EventData eventData) {
        JSONObject data = JSONObject.fromObject(eventData.getObject());

        String orderNo = data.get("orderNo").toString();

        @SuppressWarnings("unchecked")
        JSONObject metadataJson = JSONObject.fromObject(data.get("metadata"));

        // 判断是普通商品支付还ctc支付等
        switch (metadataJson.get("payType").toString()) {
            case "1":    // 普通商品支付成功
                // 判断 redis 订单信息是否存在
                if (redis.hasKey(orderNo)) {
                    JSONObject orderJsonObject = JSONObject.fromObject(redis.opsForValue().get(orderNo));

                    JSONArray orderJsonArray = JSONArray.fromObject(orderJsonObject.get("order"));
                    for (int i = 0; i < orderJsonArray.size(); i++) {
                        JSONObject orderItemHashMap = JSONObject.fromObject(orderJsonArray.get(i));
                        ydShopMapperOrder.paySuccess(orderItemHashMap.get("order_sn").toString(), data.get("transactionNo").toString(), YdUtilDate.currentMillisString());
                    }
                } else {
                    System.out.println("普通商品支付成功后修改订单状态 redis no");
                }
                break;

            case "2":    // ctc商品支付成功


                if (redis.hasKey(YdMd5Util.GetMD5Code(orderNo))) {

                    Integer ctcId = Integer.parseInt(redis.opsForValue().get(YdMd5Util.GetMD5Code(orderNo)).toString());
//					YdCtc ydCtc = new YdCtc();
//					ydCtc.setCtc_id( ctcId );
//					ydCtc.setCtc_is_available(4);
//					
//					ydShopMapperCtc.updateState(ydCtc);

                    ydShopMapperCtcOrder.paySuccess(orderNo, data.get("transactionNo").toString(), YdUtilDate.currentMillisString(), ctcId);

                } else {
                    System.out.println("ctc 支付错误");
                }

                break;
            default:
                System.out.println("支付方式错误");
                break;
        }
    }

    /**
     * 生成 ctc 订单
     */
    @Override
    public Object generateCtcOrder(String token, String key, String addressId, String message, Integer ctc_id) {

        try {
            Integer user_id = (Integer) session.getAttribute("user_id");
            YdCtcOrder ydCtcOrder = new YdCtcOrder();

            // ctc 信息
            HashMap<String, Object> ctcMap = ydShopMapperCtc.info(ctc_id);

            // 获取用户信息
            YdSysUserFull dataUser = (YdSysUserFull) session.getAttribute("user");
            if (addressId == null) {
                return YdUtilResponse.fail("请选择收货地址");
            }
            // 用户地址信息
            HashMap<String, Object> addressHashMap = ydShopMapperAddress.only(dataUser.getUser_id(), Integer.parseInt(addressId));

            String orderSn = YdOrdersn.get(user_id);
            ydCtcOrder.setCtc_id(ctc_id);
            ydCtcOrder.setCtc_cover(ctcMap.get("ctc_cover").toString());
            ydCtcOrder.setCtc_order_sn(orderSn);
            ydCtcOrder.setUser_id((Integer) ctcMap.get("user_id"));
            ydCtcOrder.setUser_name(ctcMap.get("user_username").toString());
            ydCtcOrder.setBuyer_id(user_id);
            ydCtcOrder.setBuyer_name(dataUser.getUser_username().toString());
            ydCtcOrder.setState(1);
            ydCtcOrder.setDelete_state(0);
            ydCtcOrder.setGoods_name(ctcMap.get("ctc_name").toString());
            ydCtcOrder.setGoods_price(Float.parseFloat(ctcMap.get("ctc_price").toString()));
            ydCtcOrder.setTotal_price(Float.parseFloat(ctcMap.get("ctc_price").toString()));
            Long time = System.currentTimeMillis();
            ydCtcOrder.setCreated_at(String.valueOf(time));
            ydCtcOrder.setOrder_message(message);

            if (addressHashMap == null) {
                return YdUtilResponse.fail("地址选择有误！");
            }

            ydCtcOrder.setReceipt_address(addressHashMap.get("pca").toString() + addressHashMap.get("street").toString());
            ydCtcOrder.setReceipt_name(addressHashMap.get("linkman").toString());
            ydCtcOrder.setReceipt_mobile(addressHashMap.get("linktel").toString());

            ydShopMapperCtcOrder.store(ydCtcOrder);


            redis.opsForValue().set(YdMd5Util.GetMD5Code(orderSn), ctc_id.toString(), timeInterger, TimeUnit.SECONDS);

            return YdUtilResponse.success(orderSn, "提交订单成功");
        } catch (Exception e) {
            e.printStackTrace();
            return YdUtilResponse.fail("提交订单失败");
        }
    }

    /**
     * 选择支付方式页面
     */
    @Override
    public Object paymentCtcSelect(String uu, ServletResponse __response) throws IOException {
        try {
            YdCtcOrder ydCtcOrder = ydShopMapperCtcOrder.info(uu);
            return ydCtcOrder;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 发起支付 - ctc
     *
     * @throws Exception
     */
    public Object paymentCtc(String orderSn, String type) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        YdCtcOrder ydCtcOrder = ydShopMapperCtcOrder.info(orderSn);
        session.setAttribute("payMoney", ydCtcOrder.getTotal_price());
        map.put("data",
                YdUtilPingpp.YdCharge(
                        YdUtilPingppChannel.ALIPAY_PC_DIRECT, orderSn,
                        ydCtcOrder.getTotal_price() * 100,
                        ydCtcOrder.getGoods_name(),
                        ydCtcOrder.getGoods_name(),
                        2            // ctc 支付为2 、 普通商品为 1
                ).getCharge());
        return map;
    }

    /**
     * 用户订单列表 - 发起支付
     */
    @Override
    public Object orderPay(Integer orderId) {
    	// 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        try {
        	
            Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
            HashMap<String, Object> orderMap = ydMemberMapperOrder.info(user_id, orderId);
            List<HashMap<String, Object>> listOrderGoods = ydMemberMapperOrder.orderGoodsMap(orderId);
            
            // 记录订单信息 存 redis
            List<Object> orderList = new ArrayList<Object>();
            
            // 记录订单信息
            HashMap<String, Object> orderReidsMap = new HashMap<String, Object>();
            orderReidsMap.put("order_sn", orderMap.get("order_sn"));
            orderReidsMap.put("money", decimalFormat.format(Float.parseFloat(orderMap.get("total_price").toString())));
            orderReidsMap.put("deliver", orderMap.get("receipt_name").equals("") ? "自提" : "快递");
            orderList.add(JSONObject.fromObject(orderReidsMap).toString());

            

            // redis 订单信息
            HashMap<String, Object> redisOrderMap = new HashMap<String, Object>();

            // 用于记录 redis 中订单金额和订单编号的信息
            String returnUu = YdUtilUUID.generate();

            // 把订单信息存 redis
            redisOrderMap.put("money", decimalFormat.format(Float.parseFloat(orderMap.get("total_price").toString()))); // 订单总金额
            redisOrderMap.put("goods_name", listOrderGoods.get(0).get("goods_name"));
            redisOrderMap.put("goods_body", listOrderGoods.get(0).get("goods_name"));
            redisOrderMap.put("order", orderList.toString());
            ;
            redis.opsForValue().set(returnUu, JSONObject.fromObject(redisOrderMap).toString(), timeInterger, TimeUnit.SECONDS);

            // 返回数据
            HashMap<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("uu", returnUu);
            returnMap.put("token", YdJwt.generate(returnUu));

            return YdUtilResponse.success(returnMap, "成功");
        } catch (Exception e) {
            return YdUtilResponse.fail("提交订单失败");
        }
    }

    /**
     * 退款成功，异步通知处理订单信息
     *
     * @param eventData
     * @return
     */
    // private Object refundSuccess(EventData eventData) {
    //
    // return null;
    // }
}
