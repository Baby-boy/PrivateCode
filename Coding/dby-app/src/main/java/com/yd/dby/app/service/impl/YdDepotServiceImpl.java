package com.yd.dby.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdBanner;
import com.yd.dby.app.entity.YdSeckill;
import com.yd.dby.app.entity.para.GroupBuyPara;
import com.yd.dby.app.entity.para.SeckillPara;
import com.yd.dby.app.entity.vo.GroupBuyDetailsVo;
import com.yd.dby.app.entity.vo.GroupBuyVo;
import com.yd.dby.app.entity.vo.SeckillDetailsVo;
import com.yd.dby.app.entity.vo.SeckillVo;
import com.yd.dby.app.mapper.YdBannerMapper;
import com.yd.dby.app.mapper.YdDepotMapper;
import com.yd.dby.app.mapper.YdSeckillMapper;
import com.yd.dby.app.service.YdDepotService;
import com.yd.dby.app.util.YdGsonUtils;

@Service("ydDepotService")
public class YdDepotServiceImpl implements YdDepotService {

	@Autowired
	private YdDepotMapper ydDepotMapper;

	@Autowired
	private YdBannerMapper ydBannerMapper;

	@Autowired
	private YdSeckillMapper ydSeckillMapper;

	@Override
	public ServiceResult<Page<GroupBuyVo>> selectGroupBuyPageList(GroupBuyPara para) {
		ServiceResult<Page<GroupBuyVo>> result = new ServiceResult<Page<GroupBuyVo>>();

		// banner
		List<YdBanner> banners = ydBannerMapper.selectBannerByBannerType("2");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("banners", banners.size() > 0 ? banners.get(0) : "");

		// 列表
		int countRecord = ydDepotMapper.selectGroupBuyPageCount(para);
		Page<GroupBuyVo> page = new Page<GroupBuyVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<GroupBuyVo> list = ydDepotMapper.selectGroupBuyPageList(para);
			page.setList(list);
		}

		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setDataMap(map);

		return result;
	}

	@Override
	public ServiceResult<GroupBuyDetailsVo> selectGroupBuyDetails(Integer depotId) {
		ServiceResult<GroupBuyDetailsVo> result = new ServiceResult<GroupBuyDetailsVo>();

		GroupBuyDetailsVo buyDetailsVo = ydDepotMapper.selectGroupBuyDetails(depotId);

		if (StringUtils.isNotEmpty(buyDetailsVo.getGoodsAttrs())) {
			List<Object> goodsAttrsList = YdGsonUtils.fromJson(buyDetailsVo.getGoodsAttrs());
			buyDetailsVo.setGoodsAttrsList(goodsAttrsList);
			buyDetailsVo.setGoodsAttrs(null);
		}
		if (StringUtils.isNotEmpty(buyDetailsVo.getGoodsAttrSelect())) {
			List<Object> goodsAttrSelectList = YdGsonUtils.fromJson(buyDetailsVo.getGoodsAttrSelect());
			buyDetailsVo.setGoodsAttrSelectList(goodsAttrSelectList);
			buyDetailsVo.setGoodsAttrSelect(null);
		}

		long nowTime = System.currentTimeMillis();
		long endTime = Long.valueOf(buyDetailsVo.getEndTime());
		long differTiem = endTime - nowTime;
		buyDetailsVo.setEndTime(String.valueOf(differTiem > 0 ? differTiem : 0));

		result.setData(buyDetailsVo);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<SeckillVo>> selectSeckillPageList(SeckillPara para) {
		ServiceResult<Page<SeckillVo>> result = new ServiceResult<Page<SeckillVo>>();

		// 秒杀时间
		List<YdSeckill> timeList = ydSeckillMapper.selectSeckillTimeList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timeList", timeList);
		result.setDataMap(map);

		// 秒杀列表
		if (timeList != null && timeList.size() > 0) {
			if (StringUtils.isEmpty(para.getStartTime())) {
				para.setStartTime(timeList.get(0).getStartTime());
			}
			int countRecord = ydDepotMapper.selectSeckillPageCount(para);
			Page<SeckillVo> page = new Page<SeckillVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
			if (para.getCurrentPage() <= page.getCountPage()) {
				para.setStartIndex(page.getStartIndex());
				List<SeckillVo> list = ydDepotMapper.selectSeckillPageList(para);
				page.setList(list);
			}
			result.setData(page);
		}

		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<SeckillDetailsVo> selectSeckillDetails(Integer depotId) {
		ServiceResult<SeckillDetailsVo> result = new ServiceResult<SeckillDetailsVo>();

		SeckillDetailsVo detailsVo = ydDepotMapper.selectSeckillDetails(depotId);

		if (StringUtils.isNotEmpty(detailsVo.getGoodsAttrs())) {
			List<Object> goodsAttrsList = YdGsonUtils.fromJson(detailsVo.getGoodsAttrs());
			detailsVo.setGoodsAttrsList(goodsAttrsList);
			detailsVo.setGoodsAttrs(null);
		}
		if (StringUtils.isNotEmpty(detailsVo.getGoodsAttrSelect())) {
			List<Object> goodsAttrSelectList = YdGsonUtils.fromJson(detailsVo.getGoodsAttrSelect());
			detailsVo.setGoodsAttrSelectList(goodsAttrSelectList);
			detailsVo.setGoodsAttrSelect(null);
		}

		long nowTime = System.currentTimeMillis();
		long endTime = Long.valueOf(detailsVo.getEndTime());
		long differTiem = endTime - nowTime;
		detailsVo.setEndTime(String.valueOf(differTiem > 0 ? differTiem : 0));

		result.setData(detailsVo);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<List<YdSeckill>> selectSeckillTimeList() {
		ServiceResult<List<YdSeckill>> result = new ServiceResult<List<YdSeckill>>();

		List<YdSeckill> list = ydSeckillMapper.selectSeckillTimeList();

		result.setData(list);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
