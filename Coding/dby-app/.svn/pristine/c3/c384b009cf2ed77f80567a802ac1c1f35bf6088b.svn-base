package com.yd.dby.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdStoreGoodsClassify;
import com.yd.dby.app.entity.para.StorePara;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.entity.vo.StoreCirculationVo;
import com.yd.dby.app.entity.vo.StoreClassifyVo;
import com.yd.dby.app.entity.vo.StoreVo;
import com.yd.dby.app.mapper.YdGoodsMapper;
import com.yd.dby.app.mapper.YdStoreMapper;
import com.yd.dby.app.service.YdStoreService;

@Service("ydStoreService")
public class YdStoreServiceImpl implements YdStoreService {

	@Autowired
	private YdStoreMapper ydStoreMapper;

	@Autowired
	private YdGoodsMapper ydGoodsMapper;

	@Override
	public ServiceResult<Page<StoreVo>> selectStorePageList(StorePara para) {
		ServiceResult<Page<StoreVo>> result = new ServiceResult<>();
		int countRecord = ydStoreMapper.selectStorePageListCount(para);
		Page<StoreVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<StoreVo> list = ydStoreMapper.selectStorePageList(para);
			page.setList(list);
		}
		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setMsg("查询成功!");
		return result;
	}

	@Override
	public ServiceResult<StoreVo> selectStoreDetails(StorePara para) {
		ServiceResult<StoreVo> result = new ServiceResult<>();
		// 查询店铺详情信息
		StoreVo storeVo = ydStoreMapper.selectStoreDetails(para);

		// 查询该店铺热销商品列表
		int countRecord = ydGoodsMapper.selectStoreHotGoodsPageListCount(para);
		Page<GoodsListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<GoodsListVo> hotGoodsList = ydGoodsMapper.selectStoreHotGoodsPageList(para);
			page.setList(hotGoodsList);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("hotGoodsList", page);

		if (null == storeVo) {
			result.setMsg("未找到店铺!");
			result.setStatusCode(HttpCode.NO_CONTENT);
		} else {
			result.setMsg("查询成功!");
			result.setStatusCode(HttpCode.OK);
			result.setData(storeVo);
			result.setDataMap(map);
		}
		return result;
	}

	@Override
	public ServiceResult<Page<GoodsListVo>> selectStoreGoodsPageList(StorePara para) {
		ServiceResult<Page<GoodsListVo>> result = new ServiceResult<>();

		// 查询店铺商品分类
		List<YdStoreGoodsClassify> storeGoodsClassifyList = ydStoreMapper.selectStoreGoodsClassify(para);
		if (null != storeGoodsClassifyList && storeGoodsClassifyList.size() > 0) {
			if (null == para.getPid()) {
				para.setPid(storeGoodsClassifyList.get(0).getPid());
			}
			if (null == para.getScId()) {
				para.setScId(storeGoodsClassifyList.get(0).getScId());
			}
			/*
			 * else { boolean info = true; for (YdStoreGoodsClassify ydStoreGoodsClassify : storeGoodsClassifyList) { if (ydStoreGoodsClassify.getScId() == para.getScId()) { info = false; } } if (info) { para.setScId(storeGoodsClassifyList.get(0).getScId()); } }
			 */

			int countRecord = ydGoodsMapper.selectStoreGoodsPageListCount(para);
			Page<GoodsListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
			if (para.getCurrentPage() <= page.getCountPage()) {
				para.setStartIndex(page.getStartIndex());
				List<GoodsListVo> list = ydGoodsMapper.selectStoreGoodsPageList(para);
				page.setList(list);
			}

			Map<String, Object> map = new HashMap<>();
			map.put("classify", storeGoodsClassifyList);

			result.setMsg("查询成功!");
			result.setStatusCode(HttpCode.OK);
			result.setData(page);
			result.setDataMap(map);
		} else {
			result.setMsg("查询失败!");
			result.setStatusCode(HttpCode.NO_CONTENT);
		}
		return result;
	}

	@Override
	public ServiceResult<Page<StoreCirculationVo>> selectCirculationStorePageList(StorePara para) {
		ServiceResult<Page<StoreCirculationVo>> result = new ServiceResult<>();

		int countRecord = ydStoreMapper.selectCirculationStorePageListCount(para);
		Page<StoreCirculationVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<StoreCirculationVo> list = ydStoreMapper.selectCirculationStorePageList(para);
			page.setList(list);
		}
		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setMsg("查询成功!");

		return result;
	}

	@Override
	public ServiceResult<List<StoreClassifyVo>> selectStoreClassifyList(Integer storeId) {
		ServiceResult<List<StoreClassifyVo>> result = new ServiceResult<List<StoreClassifyVo>>();

		List<StoreClassifyVo> list = ydStoreMapper.selectStoreClassifyList(storeId);
		result.setData(list);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
