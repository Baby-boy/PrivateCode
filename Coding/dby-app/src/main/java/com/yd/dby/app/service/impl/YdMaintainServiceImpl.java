package com.yd.dby.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdArticle;
import com.yd.dby.app.entity.YdBanner;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.mapper.YdArticleMapper;
import com.yd.dby.app.mapper.YdBannerMapper;
import com.yd.dby.app.mapper.YdGoodsMapper;
import com.yd.dby.app.service.YdMaintainService;

@Service("ydMaintainService")
public class YdMaintainServiceImpl implements YdMaintainService {

	@Autowired
	private YdGoodsMapper ydGoodsMapper;

	@Autowired
	private YdBannerMapper ydBannerMapper;// 养护banner图

	@Autowired
	private YdArticleMapper ydArticleMapper;// 查询养护规则文章

	@Override
	public ServiceResult<Page<GoodsListVo>> selectMaintainGoodsPageList(HomePara para) {
		ServiceResult<Page<GoodsListVo>> result = new ServiceResult<>();

		Map<String, Object> map = new HashMap<>();

		// 查询养护banner图
		List<YdBanner> ydBanners = ydBannerMapper.selectBannerByBannerType("appMaintenance");
		if (null != ydBanners && ydBanners.size() > 0) {
			map.put("banner", ydBanners.get(0));
		}

		// 查询养护规则
		List<YdArticle> ydArticles = ydArticleMapper.selectRuleArticleByAcCode("maintain");
		if (null != ydArticles && ydArticles.size() > 0) {
			map.put("article", ydArticles.get(0));
		}

		int countRecord = ydGoodsMapper.selectMaintainGoodsPageListCount(para);
		Page<GoodsListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<GoodsListVo> list = ydGoodsMapper.selectMaintainGoodsPageList(para);
			page.setList(list);
		}
		result.setMsg("查询成功!");
		result.setData(page);
		result.setDataMap(map);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
