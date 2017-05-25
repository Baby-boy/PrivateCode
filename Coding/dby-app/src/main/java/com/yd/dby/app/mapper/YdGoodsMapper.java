package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdCart;
import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.YdGoods;
import com.yd.dby.app.entity.YdGoodsWithBLOBs;
import com.yd.dby.app.entity.para.FavoritePara;
import com.yd.dby.app.entity.para.GoodsPara;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.para.StorePara;
import com.yd.dby.app.entity.vo.GoodsDetailsVo;
import com.yd.dby.app.entity.vo.GoodsListVo;

public interface YdGoodsMapper {
	public int deleteByPrimaryKey(Integer goodsId);

	public int insert(YdGoodsWithBLOBs record);

	public int insertSelective(YdGoodsWithBLOBs record);

	public YdGoodsWithBLOBs selectByPrimaryKey(Integer goodsId);

	public int updateByPrimaryKeySelective(YdGoodsWithBLOBs record);

	public int updateByPrimaryKeyWithBLOBs(YdGoodsWithBLOBs record);

	public int updateByPrimaryKey(YdGoods record);

	/** 首页推荐商品总数 */
	public int selectHomeGoodsPageCount(@Param("para") HomePara para);

	/** 首页推荐商品数据 */
	public List<GoodsListVo> selectHomeGoodsPageList(@Param("para") HomePara para);

	/** 分类商品总数 */
	public int selectClassifyGoodsPageCount(@Param("para") GoodsPara para);

	/** 分类商品数据 */
	public List<GoodsListVo> selectClassifyGoodsPageList(@Param("para") GoodsPara para);

	/** 商品详情 */
	public GoodsDetailsVo selectGoodsDetails(@Param("goodsId") Integer goodsId);

	/** 查询店铺热销商品总数 */
	public int selectStoreHotGoodsPageListCount(StorePara para);

	/** 查询店铺热销商品列表 */
	public List<GoodsListVo> selectStoreHotGoodsPageList(StorePara para);

	/** 查询店铺所有商品总数 */
	public int selectStoreGoodsPageListCount(StorePara para);

	/** 查询店铺所有商品列表 */
	public List<GoodsListVo> selectStoreGoodsPageList(StorePara para);

	/** 添加到购物车的商品详细 */
	public YdCart selectGoodsToCartDetails(@Param("goodsId") Integer goodsId);

	/** 普通商品总数 */
	public int selectGoodsPageCount(@Param("para") HomePara para);

	/** 普通商品数据 */
	public List<GoodsListVo> selectGoodsPageList(@Param("para") HomePara para);

	/** 更新收藏总数 +1 */
	public void updateGoodsTotalFav(YdFavorite ydFavorite);

	/** 更新收藏总数 -1 */
	public int updateBatchGoodsTotalFav(@Param("para") FavoritePara para, @Param("favIdList") List<Integer> favIdList);

	/** 查询养护商品总数 */
	public int selectMaintainGoodsPageListCount(@Param("para") HomePara para);

	/** 查询养护商品列表 */
	public List<GoodsListVo> selectMaintainGoodsPageList(@Param("para") HomePara para);

}