package com.glanway.ctrlhr.controller.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.service.dept.DeptService;


/**
 * 说明 : 部门管理
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月26日 上午2:40:32
 */
@Controller
@RequestMapping("api/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	/**
	 * 说明 : 考勤设备下的部门
	 *
	 * @param sn
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月26日 上午2:40:51
	 */
	@ResponseBody
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	public JsonResult simpleList(String sn) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<SimpleDeptVo> simpleList = deptService.findsimpleList(sn);
			jsonResult.setData(simpleList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return jsonResult;

	}
	
	

}
