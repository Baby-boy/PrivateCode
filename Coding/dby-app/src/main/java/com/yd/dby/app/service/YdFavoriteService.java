package com.yd.dby.app.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.FavoritePara;
import com.yd.dby.app.entity.vo.FavoriteCtcGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteStoreVo;

public interface YdFavoriteService {

	/**
	 * 说明: 添加收藏
	 * 
	 * @param ydFavorite
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午4:19:59
	 */
	@Transactional
	public ServiceResult<Object> insertUserFavorite(YdFavorite ydFavorite);

	/**
	 * 说明: 取消收藏
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午5:42:29
	 */
	@Transactional
	public ServiceResult<Object> deleteUserFavorite(FavoritePara para);

	/**
	 * 说明: 查询收藏商品列表
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午7:37:46
	 */
	public ServiceResult<Page<FavoriteGoodsVo>> selectFavoriteGoodsList(BasePara para, YdUser user);

	/**
	 * 说明: 查询收藏店铺列表
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午8:11:15
	 */
	public ServiceResult<Page<FavoriteStoreVo>> selectFavoriteStoreList(BasePara para, YdUser user);

	/**
	 * 说明: 查询收藏懒鱼商品列表
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月18日 下午3:10:28
	 */
	public ServiceResult<Page<FavoriteCtcGoodsVo>> selectFavoriteCtcGoodsList(BasePara para, YdUser user);

}
