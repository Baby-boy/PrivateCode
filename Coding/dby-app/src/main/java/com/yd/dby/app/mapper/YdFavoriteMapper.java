package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.vo.FavoriteCtcGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteStoreVo;

public interface YdFavoriteMapper {
	public int deleteByPrimaryKey(Integer favId);

	public int insert(YdFavorite record);

	public int insertSelective(YdFavorite record);

	public YdFavorite selectByPrimaryKey(Integer favId);

	public int updateByPrimaryKeySelective(YdFavorite record);

	public int updateByPrimaryKey(YdFavorite record);

	/** 查询是否已经收藏 */
	public YdFavorite selectUserFavorite(@Param("userId") Integer userId, @Param("favFid") Integer favFid, @Param("favType") Byte favType);

	/** 删除用户收藏 */
	public int deleteUserFavorite(@Param("favIdList") List<Integer> favIdList);

	/** 查询收藏商品总数 */
	public int selectFavoriteGoodsListCount(@Param("userId") Integer userId);

	/** 查询收藏商品列表 */
	public List<FavoriteGoodsVo> selectFavoriteGoodsList(@Param("para") BasePara para, @Param("userId") Integer userId);

	/** 查询收藏店铺总数 */
	public int selectFavoriteStoreListCount(@Param("userId") Integer userId);

	/** 查询收藏店铺列表 */
	public List<FavoriteStoreVo> selectFavoriteStoreList(@Param("para") BasePara para, @Param("userId") Integer userId);

	/** 查询收藏懒鱼商品总数 */
	public int selectFavoriteCtcGoodsListCount(@Param("userId") Integer userId);

	/** 查询收藏懒鱼商品列表 */
	public List<FavoriteCtcGoodsVo> selectFavoriteCtcGoodsList(@Param("para") BasePara para, @Param("userId") Integer userId);

}