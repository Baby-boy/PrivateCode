package com.yd.dby.b.shop.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdFavorite;

public interface YdShopMapperFavorite {

	@Delete("delete from yd_favorite where user_id = #{user_id} and fav_fid = #{fav_fid} and fav_type = #{fav_type}")
	Integer delete(@Param("fav_type") Integer fav_type, @Param("user_id") Integer user_id,
			@Param("fav_fid") Integer fav_fid);

	Integer store(YdFavorite ydFavorite);
	
	YdFavorite info(YdFavorite ydFavorite);

	Integer destory(YdFavorite ydFavorite);
}
