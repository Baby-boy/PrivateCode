package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.YdStore;
import com.yd.dby.app.entity.YdStoreGoodsClassify;
import com.yd.dby.app.entity.YdStoreWithBLOBs;
import com.yd.dby.app.entity.para.FavoritePara;
import com.yd.dby.app.entity.para.StorePara;
import com.yd.dby.app.entity.vo.StoreCirculationVo;
import com.yd.dby.app.entity.vo.StoreClassifyVo;
import com.yd.dby.app.entity.vo.StoreVo;

public interface YdStoreMapper {
	public int deleteByPrimaryKey(Integer storeId);

	public int insert(YdStoreWithBLOBs record);

	public int insertSelective(YdStoreWithBLOBs record);

	public YdStoreWithBLOBs selectByPrimaryKey(Integer storeId);

	public int updateByPrimaryKeySelective(YdStoreWithBLOBs record);

	public int updateByPrimaryKeyWithBLOBs(YdStoreWithBLOBs record);

	public int updateByPrimaryKey(YdStore record);

	/** 查询店铺总数 */
	public int selectStorePageListCount(StorePara para);

	/** 查询店铺列表 */
	public List<StoreVo> selectStorePageList(StorePara para);

	/** 根据店铺ID查询店铺详情 */
	public StoreVo selectStoreDetails(StorePara para);

	/** 根据店铺ID查询店铺商品分类 */
	public List<YdStoreGoodsClassify> selectStoreGoodsClassify(StorePara para);

	/** 查询流通需要显示的店铺总数 */
	public int selectCirculationStorePageListCount(StorePara para);

	/** 查询流通需要显示的店铺列表 */
	public List<StoreCirculationVo> selectCirculationStorePageList(StorePara para);

	public List<StoreClassifyVo> selectStoreClassifyList(@Param("storeId") Integer storeId);

	/** 更新收藏总数 +1 */
	public void updateStoreTotalFav(YdFavorite ydFavorite);

	/** 更新收藏总数 -1 */
	public int updateBatchStoreTotalFav(@Param("para") FavoritePara para, @Param("favIdList") List<Integer> favIdList);
}