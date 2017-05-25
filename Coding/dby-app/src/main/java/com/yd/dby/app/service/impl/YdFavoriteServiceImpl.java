package com.yd.dby.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.FavoritePara;
import com.yd.dby.app.entity.vo.FavoriteCtcGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteStoreVo;
import com.yd.dby.app.mapper.YdCtcMapper;
import com.yd.dby.app.mapper.YdFavoriteMapper;
import com.yd.dby.app.mapper.YdGoodsMapper;
import com.yd.dby.app.mapper.YdStoreMapper;
import com.yd.dby.app.service.YdFavoriteService;

@Service("ydFavoriteService")
public class YdFavoriteServiceImpl implements YdFavoriteService {

	@Autowired
	private YdFavoriteMapper ydFavoriteMapper;

	@Autowired
	private YdGoodsMapper ydGoodsMapper;

	@Autowired
	private YdStoreMapper ydStoreMapper;

	@Autowired
	private YdCtcMapper ydCtcMapper;

	@Override
	public ServiceResult<Object> insertUserFavorite(YdFavorite ydFavorite) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 查询是否已经收藏
		YdFavorite favorite = ydFavoriteMapper.selectUserFavorite(ydFavorite.getUserId(), ydFavorite.getFavFid(), ydFavorite.getFavType());
		if (null != favorite) {
			result.setMsg("已经收藏!");
			result.setStatusCode(HttpCode.NO_CONTENT);
			return result;
		}

		int info = ydFavoriteMapper.insertSelective(ydFavorite);// 添加收藏完成后需要返回ID,需要在方法上进行配置
		if (info == 1) {
			result.setMsg("添加收藏成功!");
			result.setData(ydFavorite);
			result.setStatusCode(HttpCode.OK);

			// 添加收藏成功后需要更新收藏数量 +1
			if (1 == ydFavorite.getFavType()) {
				ydStoreMapper.updateStoreTotalFav(ydFavorite);
			} else if (2 == ydFavorite.getFavType()) {
				ydGoodsMapper.updateGoodsTotalFav(ydFavorite);
			} else if (4 == ydFavorite.getFavType()) {
				ydCtcMapper.updateCtcTotalFav(ydFavorite);
			}
		} else {
			result.setMsg("添加收藏失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Object> deleteUserFavorite(FavoritePara para) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 对需要删除的多个收藏ID继续处理
		String[] str = StringUtils.split(para.getFavIds(), ",");
		List<Integer> favIdList = new ArrayList<>();
		for (String favId : str) {
			favIdList.add(Integer.parseInt(favId));
		}

		// 取消收藏后需要更新收藏数量 -1
		// 这里因为取消收藏是物理删除导致更新找不到条件,所以先更新再删除,处理失败后通过事务进行控制
		int in = 0;
		if (1 == para.getFavType()) {
			in = ydStoreMapper.updateBatchStoreTotalFav(para, favIdList);
		} else if (2 == para.getFavType()) {
			in = ydGoodsMapper.updateBatchGoodsTotalFav(para, favIdList);
		} else if (4 == para.getFavType()) {
			in = ydCtcMapper.updateBatchCtcTotalFav(para, favIdList);
		}

		int info = ydFavoriteMapper.deleteUserFavorite(favIdList);
		if (info > 0 && in > 0) {
			result.setMsg("取消收藏成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("取消收藏失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<FavoriteGoodsVo>> selectFavoriteGoodsList(BasePara para, YdUser user) {
		ServiceResult<Page<FavoriteGoodsVo>> result = new ServiceResult<>();

		int countRecord = ydFavoriteMapper.selectFavoriteGoodsListCount(user.getUserId());
		Page<FavoriteGoodsVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<FavoriteGoodsVo> list = ydFavoriteMapper.selectFavoriteGoodsList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setMsg("查询成功!");

		return result;
	}

	@Override
	public ServiceResult<Page<FavoriteStoreVo>> selectFavoriteStoreList(BasePara para, YdUser user) {
		ServiceResult<Page<FavoriteStoreVo>> result = new ServiceResult<>();
		
		int countRecord = ydFavoriteMapper.selectFavoriteStoreListCount(user.getUserId());
		Page<FavoriteStoreVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<FavoriteStoreVo> list = ydFavoriteMapper.selectFavoriteStoreList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setMsg("查询成功!");
		
		return result;
	}

	@Override
	public ServiceResult<Page<FavoriteCtcGoodsVo>> selectFavoriteCtcGoodsList(BasePara para, YdUser user) {
		ServiceResult<Page<FavoriteCtcGoodsVo>> result = new ServiceResult<>();
		
		int countRecord = ydFavoriteMapper.selectFavoriteCtcGoodsListCount(user.getUserId());
		Page<FavoriteCtcGoodsVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<FavoriteCtcGoodsVo> list = ydFavoriteMapper.selectFavoriteCtcGoodsList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setMsg("查询成功!");
		
		return result;
	}

}
