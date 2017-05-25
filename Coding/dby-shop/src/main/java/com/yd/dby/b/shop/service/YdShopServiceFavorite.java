package com.yd.dby.b.shop.service;

import com.yd.dby.b.shop.entity.YdFavorite;

public interface YdShopServiceFavorite {

	Integer delete(Integer fav_type, Integer user_id, Integer fav_fid);
	
	Object store(YdFavorite ydFavorite);
	
	boolean isFavorite(Integer fav_fid, Integer fav_type);

	Object destory(YdFavorite ydFavorite);
}