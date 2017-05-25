package com.yd.dby.b.shop.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yd.dby.b.shop.mapper.YdShopMapperComment;
import com.yd.dby.b.shop.service.YdShopServiceComment;
import com.yd.dby.utils.YdUtilRelativeDateForma;
import com.yd.dby.utils.date.YdUtilDate;

@Service("_Comment")
public class YdShopServiceImplComment implements YdShopServiceComment {

	@Autowired
	private YdShopMapperComment ydAppMapperComment;

	public Integer get_count(@Param("goods_id") Integer goods_id, @Param("type") Integer type) {

		return ydAppMapperComment.get_count(goods_id, type);
	}

	public List<String> get_list_only_img(@Param("goods_id") Integer goods_id) {
		return ydAppMapperComment.get_list_only_img(goods_id);
	}

	public List<HashMap<String, Object>> get_list(@Param("goods_id") Integer goods_id, @Param("type") Integer type,
			@Param("page_start") Integer page_index) {

		List<HashMap<String, Object>> list = ydAppMapperComment.get_list(goods_id, type, page_index * 10);
		Gson g = new Gson();

		for (HashMap<String, Object> map : list) {
			map.put("pics", g.fromJson(map.get("pics").toString(), new TypeToken<List<String>>() {
			}.getType()));
		}

		return list;
	}
	
	
	/**
	 * 总数
	 */
	@Override
	public Integer total(String type, Integer goods_id){
		HashMap<String, Object> request = new HashMap<String, Object>();

		if ( type != null ) {
			switch (type) {
				case "1": request.put("comment_is_pic", "1"); break;
				case "2": request.put("comment_grade", "1"); break;
				case "3": request.put("comment_grade", "2"); break;
				case "4": request.put("comment_grade", "3"); break;
				default:
					break;
			}
		}
		
		request.put("goods_id", goods_id);
		return ydAppMapperComment.total(request);
	}
	
	
	/**
	 * 获取商品评价列表
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> lists(HashMap<String, Object> request) throws ParseException {
		Integer perPage = 2;
		
		if ( request.get("state") != null ) {
			switch (request.get("state").toString()) {
				case "2": request.put("comment_is_pic", "1"); break;
				case "3": request.put("comment_grade", "1"); break;
				case "4": request.put("comment_grade", "2"); break;
				case "5": request.put("comment_grade", "3"); break;
				default:
					break;
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		List<HashMap<String, Object>> dataComments = ydAppMapperComment.lists(request);
		for (HashMap<String, Object> hashMap : dataComments) {
			hashMap.put("comment_pics", hashMap.get("comment_pics").toString().split(","));
			
			// 几天前
			String string = "";
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
			Date commentCreatedTime =  format.parse( YdUtilDate.stampToDateYear( hashMap.get("comment_created_time").toString() ) );
			if ( hashMap.get("comment_buy_time").equals("") ) {
				string = YdUtilRelativeDateForma.format(commentCreatedTime);
			} else {
				Date commentBuyTime = format.parse( YdUtilDate.stampToDateYear( hashMap.get("comment_buy_time").toString() ) );
				string = YdUtilRelativeDateForma.format(commentCreatedTime, commentBuyTime);
			}
			string = string.replace("前", "");
			hashMap.put("relative_date", string);
		}
		
		map.put("dataComments", dataComments);

		Integer total = ydAppMapperComment.total(request);
		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

}
