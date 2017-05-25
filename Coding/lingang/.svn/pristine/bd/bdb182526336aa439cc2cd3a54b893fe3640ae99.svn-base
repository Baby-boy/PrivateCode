package com.lingang.platform.controller.home;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPolicy;
import com.lingang.api.domain.para.SysPolicyPara;
import com.lingang.api.domain.vo.SysPolicyVo;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysPolicyService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/policy")
public class SysPolicyController {
	
	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysPolicyService sysPolicyService;
	@Resource
	private SysImagesService sysImagesService;

	/**
	 * @Description: 根据条件查询对应的政策信息
	 * @param map
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @date: 2016年12月8日 下午2:32:02
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAllByPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult queryAllByPage(@RequestBody SysPolicyPara para) {
		return sysPolicyService.queryAll(para);
	}

	/**
	 * @Description: (查询政策详情)
	 * @param policy_id
	 *            政策Id
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月13日 下午2:11:11
	 */
	@ResponseBody
	@RequestMapping(value = "/sysPoliyById.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult queryById(@RequestParam(value = "policyId", required = true) Integer policyId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPolicyVo> result = sysPolicyService.selectByPrimaryKey(policyId);
		// 缩略图
		/*
		 * ServiceResult<SysImages>
		 * imgResult=sysImagesService.selectSysImagesByImgTypeAndObjId(13,
		 * result.getData().getPolicyId()); if(imgResult.getData() !=null){
		 * result.getData().setImgId(imgResult.getData().getImgId());
		 * result.getData().setImgPath(imgResult.getData().getImgPath()); }
		 */
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: 添加新政策信息
	 * @param policy
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午2:33:18
	 */
	@RequestMapping(value = "/addPolicyDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addPolicyDetails(@RequestBody SysPolicy policy,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		policy.setCreateTime(newDate);
		ServiceResult<Object> result = sysPolicyService.insert(policy);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加政策信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (修改指定政策)
	 * @param policy
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月13日 下午5:23:05
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePolicyDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updatePolicyDetails(@RequestBody SysPolicyPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPolicy policy = new SysPolicy();
		policy.setPolicyId(para.getPolicyId());
		policy.setPolicyTitle(para.getPolicyTitle());
		policy.setPolicyState(para.getPolicyState());
		policy.setPolicyContent(para.getPolicyContent());
		policy.setImgId(para.getImgId());
		ServiceResult<Object> result = sysPolicyService.updatePolicyDetails(policy);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改政策信息");
		}
		return jsonResult;
	}

	/**
	 * @Description:删除指定的政策信息
	 * @param policy_id
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午2:35:27
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult del(Integer policy_id,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		int i=sysPolicyService.del(policy_id);
		
		//记录日志
		if(i>0){
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), new Date(),
					"修改政策信息");
		}
		
		return jsonResult;
	}
}
