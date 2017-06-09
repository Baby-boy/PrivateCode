package com.glanway.ctrlhr.controller.perfSatll;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.entity.vo.SimplePerfStall;
import com.glanway.ctrlhr.service.perfStall.PerfStallService;

@Controller
@RequestMapping("api/perfSatll")
public class PerfSatllController {
	@Autowired
	private PerfStallService perfStallService;

	
	@ResponseBody
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	public JsonResult simpleList(String jobGradeId){
		JsonResult jsonResult = new JsonResult();
		if(StringUtils.isBlank(jobGradeId)){
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			return jsonResult;
		}
		try {
			List<SimplePerfStall> list = perfStallService.findSimplePerfStall(jobGradeId);
			jsonResult.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}
		return jsonResult;
	}
}
