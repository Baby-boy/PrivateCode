package com.lingang.consumer.controller.statistics;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysRegionStatisticsVo;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysRegionService;

/**
 * @Description: (产业园区统计 )
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月10日 下午4:01:11
 * @Version:1.0
 */
@Controller
@RequestMapping("/parkStatistics")
public class ParkStatisticsController {

	@Resource
	private SysRegionService sysRegionService;

	@Resource
	private SysParkService sysParkService;

	/**
	 * @Description: (区位分布)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月10日 下午4:14:58
	 */
	@RequestMapping(value = "/regionStatisticsList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult regionStatisticsList(@RequestParam(value = "regionType", required = true) Integer regionType, @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysRegionStatisticsVo>> result = sysRegionService.selectRegionParkStatisticsPageList(regionType, pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (新增园区)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value = "/newParkStatisticsList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult newParkStatisticsList() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysNewAddStatisticsVo>> result = sysParkService.selectNewParkStatisticsList();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
}
