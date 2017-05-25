package com.yd.dby.app.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.GroupBuyPara;
import com.yd.dby.app.entity.vo.GroupBuyDetailsVo;
import com.yd.dby.app.entity.vo.GroupBuyVo;
import com.yd.dby.app.service.YdDepotService;

/**
 * 说明: 团购
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年3月9日 上午9:50:23
 * @Version:1.0
 */
@RestController
@RequestMapping(value = "/group", produces = { "application/json;charset=UTF-8" })
public class GroupBuyController {

	@Autowired
	private YdDepotService ydDepotService;

	/**
	 * 说明: 团购列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月9日 上午10:22:31
	 */
	@RequestMapping(value = "queryGroupBuyPageList", method = RequestMethod.POST)
	public JsonResult queryGroupBuyPageList(@RequestBody GroupBuyPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<GroupBuyVo>> result = ydDepotService.selectGroupBuyPageList(para);

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());

		return jsonResult;
	}

	/**
	 * 说明: 团购商品详情
	 * 
	 * @param depotId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月9日 下午12:21:29
	 */
	@RequestMapping(value = "queryGroupBuyDetails", method = RequestMethod.GET)
	public JsonResult queryGroupBuyDetails(@RequestParam(value = "depotId", required = true) Integer depotId) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<GroupBuyDetailsVo> result = ydDepotService.selectGroupBuyDetails(depotId);

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());

		return jsonResult;
	}
}
