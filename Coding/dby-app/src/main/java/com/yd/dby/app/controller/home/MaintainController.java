package com.yd.dby.app.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.service.YdMaintainService;

@RestController
@RequestMapping("maintain")
public class MaintainController {

	@Autowired
	private YdMaintainService ydMaintainService;

	/**
	 * 说明: 查询养护商品列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月23日 下午7:54:07
	 */
	@RequestMapping(value = "maintainGoodsPageList", method = RequestMethod.POST)
	public JsonResult queryMaintainGoodsPageList(@RequestBody HomePara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<GoodsListVo>> result = ydMaintainService.selectMaintainGoodsPageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;

	}
}
