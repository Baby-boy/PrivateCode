package com.yd.dby.b.shop.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.b.shop.service.YdShopServiceInMessage;

/**
 * @author Administrator
 * 说明(用户给商品的留言管理)
 */
@RequestMapping(value="/message")
@Controller
public class YdShopControllerInMessage {

	@Autowired
	private YdShopServiceInMessage ydShopServiceInMessage;

	/**
	 * @param im_store_id(用户留言店铺id)
	 * @param im_depot_id(商品仓库 id)
	 * @return
	 * 方法作用(添加普通商品留言)
	 */
	
	@RequestMapping(value="goodsSave")
	@ResponseBody
	public Object goodsSave(Integer im_store_id, Integer im_goods_id,String message_content){
		
		return ydShopServiceInMessage.inMessage(im_store_id,im_goods_id,message_content); 
	}
	
	/**
	 * @param seller_userId(卖家id)
	 * @param ctc_id(ctc商品id)
	 * @param message_content(留言内容)
	 * @param ctc_name(ctc商品名称)
	 * @param ctc_price(ctc商品价格)
	 * @param ctc_img(ctc商品图片)
	 * @return
	 * 方法作用(添加ctc商品留言)
	 */
	@RequestMapping(value="ctcSave")
	@ResponseBody
	public Object ctcSave(Integer seller_userId, Integer ctc_id,String message_content,String ctc_name,BigDecimal ctc_price,String ctc_summary,String ctc_img){
		Map<String,Object> request = new HashMap<String, Object>();
		request.put("ctc_img",ctc_img);
		request.put("seller_userId",seller_userId);
		request.put("ctc_id",ctc_id);
		request.put("message_content",message_content);
		request.put("ctc_name",ctc_name);
		request.put("ctc_price",ctc_price);
		request.put("ctc_summary",ctc_summary);
		return ydShopServiceInMessage.ctcSave(request); 
	}
	
	/**
	 * @param ctc_id(ctc商品id)
	 * @param recive_id(回复人id)
	 * @param user_id(接收人id)
	 * @param recive_content(回复内容)
	 * @return
	 * 方法作用(回复留言)
	 */
	@RequestMapping(value="ctcRecive")
	@ResponseBody
	public Object ctcRecive(Integer ctc_id,Integer recive_id,Integer user_id,String recive_contentrecive_content){
		Map<String,Object> request = new HashMap<String, Object>();
		request.put("ctc_id",ctc_id);
		request.put("recive_id",recive_id);
		request.put("user_id",user_id);
		request.put("recive_content",recive_contentrecive_content);
		return ydShopServiceInMessage.ctcRecive(request);
	}
}
