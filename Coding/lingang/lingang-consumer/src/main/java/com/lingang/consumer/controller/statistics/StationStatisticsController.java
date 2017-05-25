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
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysIndustryStatisticsVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.service.SysIndustryService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysStationService;

/**
 * @Description: (入驻企业统计)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月9日 下午5:10:54
 * @Version:1.0
 */
@Controller
@RequestMapping("/stationStatistics")
public class StationStatisticsController {

	@Resource
	private SysIndustryService sysIndustryService;

	@Resource
	private SysParkService sysParkService;

	@Resource
	private SysStationService sysStationService;

	/**
	 * @Description: (产业分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value = "/industryStatisticsList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult industryStatisticsList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysIndustryStatisticsVo>> result = sysIndustryService.selectIndustryStatisticsList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * @Description: (园区分布)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value = "/parkStatisticsList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult parkStatisticsList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysParkStatisticsVo>> result = sysParkService.selectParkStationStatisticsList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * @Description: (新增企业)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value = "/newStationStatisticsList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult newStationStatisticsList() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysNewAddStatisticsVo>> result = sysStationService.selectNewStationStatisticsList();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * @Description: (多点布局)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value = "/moreStationStatisticsCount.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult moreStationStatisticsCount() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Object> result = sysStationService.selectmoreStationStatisticsCount();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * @Description: (入驻企业-特定属性接口)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:20:31
	 */
	@RequestMapping(value = "/selectStationLabel.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult getStationLabelList() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysLabelsVo>> result = sysStationService.selectStationlabel();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;

	}

	/**
	 * @Description: (入驻企业-创建单位接口)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:20:31
	 */
	@RequestMapping(value = "/selectStationCompany.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectStationCompany() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysCompanyVo>> result = sysStationService.selectStationCompany();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

}
