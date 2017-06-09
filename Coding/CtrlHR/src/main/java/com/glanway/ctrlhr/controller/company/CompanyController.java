package com.glanway.ctrlhr.controller.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ponly.common.json.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.entity.dept.ComVO;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.service.dept.DeptService;
import com.glanway.ctrlhr.util.StringUtil;
import com.google.common.collect.Maps;


/**
 * 说明 : 公司相关请求
 *
 * @author 王晨
 * @version 1.0.0
 * @dateTime 2017年5月22日 下午2:00:00
 */
@Controller
@RequestMapping("api/com")
public class CompanyController {
	@Autowired
	private DeptService deptService;

	/**
	 * 说明 : 架构-公司一览
	 *
	 * @param companyIds
	 * @return
	 * @author 王晨
	 * @dateTime 2017年5月22日 下午2:00:00
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult simpleList(String companyIds) {
		JsonResult jsonResult = new JsonResult();
		try {
			ComVO com = deptService.getCompanys(companyIds);
			jsonResult.setData(com);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return jsonResult;

	}
	
	


}
