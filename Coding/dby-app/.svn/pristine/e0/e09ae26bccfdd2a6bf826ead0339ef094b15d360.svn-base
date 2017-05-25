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
import com.yd.dby.app.entity.YdOnlinebooking;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.mapper.YdArticleMapper;
import com.yd.dby.app.mapper.YdBannerMapper;
import com.yd.dby.app.mapper.YdOnlinebookingMapper;
import com.yd.dby.app.service.YdCirculationService;

@Service("ydCirculationService")
public class YdCirculationServiceImpl implements YdCirculationService {

	@Autowired
	private YdOnlinebookingMapper ydOnlinebookingMapper;

	@Autowired
	private YdBannerMapper ydBannerMapper;// 流通置换banner图

	@Autowired
	private YdArticleMapper ydArticleMapper;// 查询规则文章

	@Override
	public ServiceResult<Object> insertCirculationOnlinebooking(YdOnlinebooking para) {
		ServiceResult<Object> result = new ServiceResult<>();
		int info = ydOnlinebookingMapper.insertSelective(para);
		if (info == 1) {
			result.setMsg("添加成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("添加失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<YdBanner> selectCirculationHome() {
		ServiceResult<YdBanner> result = new ServiceResult<>();

		List<YdBanner> list = ydBannerMapper.selectBannerByBannerType("circulation");
		if (null != list && list.size() > 0) {
			result.setData(list.get(0));
		}

		// 查询流通置换规则文章
		List<YdArticle> articleList = ydArticleMapper.selectRuleArticleByAcCode("circulation");

		Map<String, Object> map = new HashMap<>();
		map.put("rule", articleList);
		result.setDataMap(map);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<YdOnlinebooking>> selectCirculationOnlinebookingPageList(BasePara para, YdUser user) {
		ServiceResult<Page<YdOnlinebooking>> result = new ServiceResult<>();

		int countRecord = ydOnlinebookingMapper.selectCirculationOnlinebookingPageListCount(user.getUserId());
		Page<YdOnlinebooking> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<YdOnlinebooking> list = ydOnlinebookingMapper.selectCirculationOnlinebookingPageList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<YdOnlinebooking> selectCirculationOnlinebookingDetails(Integer id) {
		ServiceResult<YdOnlinebooking> result = new ServiceResult<>();

		YdOnlinebooking onlinebooking = ydOnlinebookingMapper.selectCirculationOnlinebookingDetails(id);
		result.setData(onlinebooking);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}
}
