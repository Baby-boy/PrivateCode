package com.yd.dby.app.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.ActivityPara;
import com.yd.dby.app.entity.vo.ActivityVo;
import com.yd.dby.app.service.YdActivityService;

@RestController
@RequestMapping("activity")
public class ActivityController {

	@Autowired
	private YdActivityService ydActivityService;

	/**
	 * 说明: 活动商品列表
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月14日 下午5:47:04
	 */
	@RequestMapping(value = "activityGoodsPageList", method = RequestMethod.POST)
	public JsonResult queryActivityGoodsPageList(@RequestBody ActivityPara para) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (null == para.getBannerId() || null == para.getBannerOpenType()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		ServiceResult<Page<ActivityVo>> result = ydActivityService.selectActivityGoodsPageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
