package com.lingang.consumer.controller.wcm;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.WcmCount;
import com.lingang.api.service.WcmCountService;

@Controller
@RequestMapping("/wcm")
public class WcmCountController {

	@Resource
	private WcmCountService wcmCountService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * @Description: (wcm点击次数)
	 * @param docId
	 * @param siteId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月29日 下午2:41:37
	 */
	@ResponseBody
	@RequestMapping(value = "/clickCount.do", method = RequestMethod.GET)
	public String wcmClickCount(@RequestParam(value = "docId", required = true) Integer docId,
			@RequestParam(value = "siteId", required = true) Integer siteId,
			@RequestParam(value = "callback", required = false) String callback) {
		String resultStr = null;
		JsonResult jsonResult = new JsonResult();

		ServiceResult<WcmCount> result = wcmCountService.selectClickCount(docId, siteId);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		try {
			resultStr = MAPPER.writeValueAsString(jsonResult);
			if (StringUtils.isNotEmpty(callback)) {
				resultStr = "getViewsCount(" + resultStr + ")";
			}
		} catch (Exception e) {
		}

		return resultStr;
	}
}
