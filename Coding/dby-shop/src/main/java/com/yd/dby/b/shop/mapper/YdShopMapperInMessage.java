package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.b.shop.entity.YdCtcMessage;
import com.yd.dby.b.shop.entity.YdShopInMessage;
import com.yd.dby.b.shop.entity.YdShopMessage;
import com.yd.dby.d.seller.entity.YdSellerGoods;

public interface YdShopMapperInMessage {

	/**
	 * @param user_id(留言用户id)
	 * @return
	 * 方法作用(根据留言用户id查询用户的部分消息)
	 */
	YdSysUserFull queryInMessageUser(Integer user_id);

	/**
	 * @param ydShopInMessage
	 * @return
	 * 方法作用(添加普通商品留言信息)
	 */
	Integer inMessage(YdShopInMessage ydShopInMessage);

	/**
	 * @param im_depot_id(仓库ID)
	 * @return
	 * 方法作用(根据商品的仓库id查询商品的部分信息)
	 */
	YdSellerGoods queryGoods(@Param("goods_id")Integer im_depot_id);

	/**
	 * @param ydShopMessage
	 * @return
	 * 方法作用(添加普通商品关联的信息表)
	 */
	Integer addMessage(YdShopMessage ydShopMessage);

	/**
	 * @param user_id(留言用户id)
	 * @return
	 * 方法作用(查询留言用户的昵称,头像)
	 */
	YdSysUserFull queryMessageUserByUserId(@Param("user_id")Integer user_id);

	/**
	 * @param sellerUserId(卖家用户id)
	 * @return
	 * 方法作用(查询接收留言方的昵称 头像)
	 */
	YdSysUserFull querySellerUserByUserId(@Param("seller_userId")Integer sellerUserId);

	/**
	 * @param ydCtcMessage
	 * @return
	 * 方法作用(添加ctc商品留言)
	 */
	Integer ctcSaveMessage(YdCtcMessage ydCtcMessage);

	/**
	 * @param request
	 * @return
	 * 方法作用(添加ctc商品关联的信息表)
	 */
	Integer saveMessage(Map<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(ctc商品的留言已经存在更新ctc商品的信息)
	 */
	Integer updateCtcMessage(Map<String, Object> request);

	/**
	 * @param object
	 * @return
	 * 方法作用(根据message_ctc id 查询当前商品信息是否存在)
	 */
	HashMap<String, Object> queryCtcMessage(@Param("ctc_id")Integer ctc_id);

}
