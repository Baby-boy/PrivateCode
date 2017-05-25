package com.yd.dby.app.controller.buy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdSeckill;
import com.yd.dby.app.entity.para.GroupBuyPara;
import com.yd.dby.app.entity.para.SeckillPara;
import com.yd.dby.app.entity.vo.GroupBuyDetailsVo;
import com.yd.dby.app.entity.vo.GroupBuyVo;
import com.yd.dby.app.entity.vo.SeckillDetailsVo;
import com.yd.dby.app.entity.vo.SeckillVo;
import com.yd.dby.app.service.YdDepotService;

@RestController
@RequestMapping(value = "seckill", produces = { "application/json;charset=UTF-8" })
public class SeckillController {

	@Autowired
	private YdDepotService ydDepotService;

	/**
	 * 说明: 秒杀列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月10日 上午11:32:49
	 */
	@RequestMapping(value = "querySeckillPageList", method = RequestMethod.POST)
	public JsonResult querySeckillPageList(@RequestBody SeckillPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<SeckillVo>> result = ydDepotService.selectSeckillPageList(para);

		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		jsonResult.setStatusCode(result.getStatusCode());

		return jsonResult;
	}

	/**
	 * 说明: 秒杀商品详情
	 * 
	 * @param depotId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月10日 上午11:34:39
	 */
	@RequestMapping(value = "querySeckillDetails", method = RequestMethod.GET)
	public JsonResult querySeckillDetails(@RequestParam(value = "depotId", required = true) Integer depotId) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<SeckillDetailsVo> result = ydDepotService.selectSeckillDetails(depotId);

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());

		return jsonResult;
	}

	/**
	 * 说明: 查询秒杀时间
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月10日 下午1:35:34
	 */
	@RequestMapping(value = "querySeckillTimeList", method = RequestMethod.POST)
	public JsonResult querySeckillTimeList() {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<List<YdSeckill>> result = ydDepotService.selectSeckillTimeList();

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());

		return jsonResult;
	}
}
