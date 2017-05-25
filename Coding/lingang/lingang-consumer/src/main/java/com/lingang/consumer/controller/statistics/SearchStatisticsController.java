package com.lingang.consumer.controller.statistics;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysSearchRecords;
import com.lingang.api.service.SysSearchService;

@Controller
@RequestMapping("/search")
public class SearchStatisticsController {

	@Resource
	private SysSearchService sysSearchService;

	/**
	 * @Description: (检索)
	 * @param pageIndex
	 * @param pageSize
	 * @param type
	 * @param keywords
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月30日 下午4:02:35
	 */
	@RequestMapping(value = "/search.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult searchIndustryStatistics(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "type", required = false, defaultValue = "1") String type,
			@RequestParam(value = "keywords", required = false) String keywords, HttpServletRequest request) {

		if ("get".equalsIgnoreCase(request.getMethod())) {
			try {
				keywords = new String(keywords.getBytes("iso-8859-1"), "utf-8");
			} catch (Exception e) {
			}
		}

		return sysSearchService.searchStatisticsPageList(type, keywords, pageIndex, pageSize);
	}

	@RequestMapping(value = "/hotSearch.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult hotSearch() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysSearchRecords>> result = sysSearchService.selectByPrimaryKeys();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;

	}

}
