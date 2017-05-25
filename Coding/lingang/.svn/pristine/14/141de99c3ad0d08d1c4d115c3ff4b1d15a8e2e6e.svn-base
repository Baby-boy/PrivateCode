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
import com.lingang.api.domain.entity.SysAssort;
import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.para.CompanyPara;
import com.lingang.api.service.SysCompanyService;
import com.lingang.api.service.SysLogService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/company")
public class SysCompanyController {
	
	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysCompanyService sysCompanyService;
	
	/**
	 * @Description: (公司列表 针对权限)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月30日 上午10:12:57
	 */
	@RequestMapping(value = "/selectCompanyPageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectCompanyPageListP(@RequestBody CompanyPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysCompany>> result = sysCompanyService.selectCompanyPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (公司列表)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月30日 上午10:12:57
	 */
	@RequestMapping(value = "/selectCompanyPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectCompanyPageList(@RequestBody CompanyPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysCompany>> result = sysCompanyService.selectCompanyPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (修改公司详情)
	 * @param company
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月30日 上午11:21:48
	 */
	@RequestMapping(value = "/updateCompanyDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult updateCompanyDetails(@RequestBody SysCompany company,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		company.setUpdateTime(newDate);
		ServiceResult<SysCompany> result = sysCompanyService.updateCompanyDetails(company);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改公司信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加公司详情)
	 * @param company
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月30日 上午11:21:48
	 */
	@RequestMapping(value = "/addCompanyDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addCompanyDetails(@RequestBody SysCompany company,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date nowDate = new Date();
		company.setCreateTime(nowDate);
		company.setUpdateTime(nowDate);
		company.setCompanyState(2);// 废弃字段(为了避免其他地方已经关联查询，所以直接默认为2)
		ServiceResult<SysCompany> result = sysCompanyService.addCompanyDetails(company);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), nowDate,
					"添加公司信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加级联公司)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月30日 下午5:51:40
	 */
	@RequestMapping(value = "/addCompanyCascade.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addCompanyCascade(@RequestBody CompanyPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysAssort assort = new SysAssort();
		assort.setCompanyId(para.getCompanyId());
		assort.setAssortType(para.getAssortType());
		assort.setObjId(para.getObjId());
		assort.setCreateTime(newDate);
		ServiceResult<SysAssort> result = sysCompanyService.addAssort(assort);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加级联公司");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除级联公司)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月30日 下午5:51:40
	 */
	@RequestMapping(value = "/delCompanyCascade.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delCompanyCascade(@RequestBody CompanyPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysCompanyService.delCompanyCascade(para.getAssortType(), para.getObjId(),
				para.getCompanyId());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除级联公司");
		}
		return jsonResult;
	}
}
