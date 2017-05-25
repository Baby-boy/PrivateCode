package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdCtc;
import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcHomePara;
import com.yd.dby.app.entity.para.CtcPara;
import com.yd.dby.app.entity.para.FavoritePara;
import com.yd.dby.app.entity.vo.CtcGoodsDetailsVo;
import com.yd.dby.app.entity.vo.CtcGoodsListVo;

public interface YdCtcMapper {
	public int deleteByPrimaryKey(Integer ctcId);

	public int insert(YdCtc record);

	public int insertSelective(YdCtc record);

	public YdCtc selectByPrimaryKey(Integer ctcId);

	public int updateByPrimaryKeySelective(YdCtc record);

	public int updateByPrimaryKeyWithBLOBs(YdCtc record);

	public int updateByPrimaryKey(YdCtc record);

	/** 查询用户发布商品总数 */
	public int selectUserCtcGoodsPageListCount(@Param("userId") Integer userId);

	/** 查询用户发布商品列表 */
	public List<CtcGoodsListVo> selectUserCtcGoodsPageList(@Param("para") BasePara para, @Param("userId") Integer userId);

	/** 删除懒鱼商品 */
	public int deleteUserCtcGoods(@Param("ctcIdList") List<Integer> ctcIdList);

	/** 懒鱼首页分类商品总数 */
	public int selectCtcGoodsPageListCount(CtcHomePara para);

	/** 懒鱼首页分类商品列表 */
	public List<CtcGoodsListVo> selectCtcGoodsPageList(CtcHomePara para);

	/** 懒鱼商品详情 */
	public CtcGoodsDetailsVo selectCtcGoodsDetails(@Param("ctcId") Integer ctcId, @Param("userId") Integer userId);

	/** 查询他人主页发布商品总数 */
	public int selectCtcOthersHomepageCount(CtcPara para);

	/** 查询他人主页发布商品列表 */
	public List<CtcGoodsListVo> selectCtcOthersHomepage(CtcPara para);

	/** 更新收藏总数 +1 */
	public void updateCtcTotalFav(YdFavorite ydFavorite);

	/** 更新收藏总数 -1 */
	public int updateBatchCtcTotalFav(@Param("para") FavoritePara para, @Param("favIdList") List<Integer> favIdList);

	/** 更新留言总数 +1 */
	public void updateCtcTotalMessage(@Param("ctcId") Integer ctcId);

}