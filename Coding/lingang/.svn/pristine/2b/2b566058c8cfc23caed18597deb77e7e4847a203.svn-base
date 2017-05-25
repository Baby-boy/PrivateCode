package com.lingang.platform.controller.basics;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysRegion;
import com.lingang.api.domain.para.SysRegionPara;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysRegionService;
import com.lingang.common.util.IpAddressUtil;

/**
 * @Description: (区域管理)
 * @Author: zf(18817590078@163.com)
 * @date:2016年12月8日 下午8:27:52
 * @Version:1.0
 */
@Controller
@RequestMapping("/sysRegion")
public class SysRegionController {
	
	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysRegionService sysRegionService;
	
	/**
	 * @Description: (地区列表 针对权限)
	 * @param para
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月8日 下午3:57:42
	 */
	@RequestMapping(value = "/sysRegionPagePageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysRegionPagePageListP(@RequestBody SysRegionPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysRegion>> result = sysRegionService.selectSysRegionPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (地区列表)
	 * @param para
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月8日 下午3:57:42
	 */
	@RequestMapping(value = "/sysRegionPagePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysRegionPagePageList(@RequestBody SysRegionPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysRegion>> result = sysRegionService.selectSysRegionPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (修改地区信息 )
	 * @param region
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月10日 下午2:48:04
	 */
	@RequestMapping(value = "/updateSysRegion.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult updateSysRegion(@RequestBody SysRegion region,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		region.setUpdateTime(newDate);
		ServiceResult<SysRegion> result = sysRegionService.updateSysRegion(region);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改地区信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加地区)
	 * @param region
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月10日 下午3:11:38
	 */
	@RequestMapping(value = "/sysRegionAdd.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysBasicsAdd(@RequestBody SysRegion region,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		region.setCreateTime(newDate);
		ServiceResult<SysRegion> result = sysRegionService.addSysBasics(region);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加地区信息");
		}
		return jsonResult;
	}

}
