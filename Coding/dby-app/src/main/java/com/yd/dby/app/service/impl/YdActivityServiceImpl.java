package com.yd.dby.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.ActivityPara;
import com.yd.dby.app.entity.vo.ActivityVo;
import com.yd.dby.app.mapper.YdActivityMapper;
import com.yd.dby.app.service.YdActivityService;

@Service("ydActivityService")
public class YdActivityServiceImpl implements YdActivityService {

	@Autowired
	private YdActivityMapper ydActivityMapper;

	@Override
	public ServiceResult<Page<ActivityVo>> selectActivityGoodsPageList(ActivityPara para) {
		ServiceResult<Page<ActivityVo>> result = new ServiceResult<>();

		int countRecord = ydActivityMapper.selectActivityGoodsPageListCount(para);
		Page<ActivityVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<ActivityVo> list = ydActivityMapper.selectActivityGoodsPageList(para);
			page.setList(list);
		}
		result.setData(page);
		result.setStatusCode(HttpCode.OK);
		result.setMsg("查询成功!");

		return result;
	}

}
