package com.yd.dby.app.service;

import java.util.List;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.StorePara;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.entity.vo.StoreCirculationVo;
import com.yd.dby.app.entity.vo.StoreClassifyVo;
import com.yd.dby.app.entity.vo.StoreVo;

public interface YdStoreService {

	/**
	 * 说明: 查询品牌店列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月7日 下午1:51:27
	 */
	public ServiceResult<Page<StoreVo>> selectStorePageList(StorePara para);

	/**
	 * 说明: 根据品牌店店铺ID查询店铺详情
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月7日 下午4:04:56
	 */
	public ServiceResult<StoreVo> selectStoreDetails(StorePara para);

	/**
	 * 说明: 查询品牌店店铺的所有商品列表(店铺首页)
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月8日 上午10:25:40
	 */
	public ServiceResult<Page<GoodsListVo>> selectStoreGoodsPageList(StorePara para);

	/**
	 * 说明: 查询流通需要显示的店铺列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月8日 下午2:50:59
	 */
	public ServiceResult<Page<StoreCirculationVo>> selectCirculationStorePageList(StorePara para);

	/**
	 * 说明: 店铺分类筛选
	 * 
	 * @param scoreId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午9:09:09
	 */
	ServiceResult<List<StoreClassifyVo>> selectStoreClassifyList(Integer storeId);

}
