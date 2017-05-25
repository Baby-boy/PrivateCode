package com.yd.gcj.controller.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/pageNum", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageNumFactory {

	@RequestMapping(value = "/pageNum", method = RequestMethod.POST)
	public Object queryTask(Integer pageNum, String pageNumName, HttpServletRequest request) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			// ajax提交页码:pageNum
			request.getSession().setAttribute(pageNumName, pageNum);
			mapInitFactory.setMsg("200", "");
		} catch (Exception e) {
			e.printStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
}
