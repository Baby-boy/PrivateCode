package com.glanway.ctrlhr.controller.job;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.job.Job;
import com.glanway.ctrlhr.entity.job.JobOrg;
import com.glanway.ctrlhr.entity.job.OrgDept;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.para.JobOrgPara;
import com.glanway.ctrlhr.entity.vo.DeptOrgTreeVO;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.entity.vo.JobOrgVo;
import com.glanway.ctrlhr.entity.vo.JobVo;
import com.glanway.ctrlhr.service.dept.DeptService;
import com.glanway.ctrlhr.service.employee.EmployeeService;
import com.glanway.ctrlhr.service.job.JobOrgService;
import com.glanway.ctrlhr.service.job.JobService;
import com.glanway.ctrlhr.util.StringUtil;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明 : 职位编制管理相关
 * 
 * @author 于瑞智
 * @version 1.0.0
 * @dateTime 2017年5月16日 下午9:21:30
 */
@Controller
@RequestMapping("api/jobOrg")
public class JobOrgController {

	@Autowired
	private JobOrgService jobOrgService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private DeptService deptService;

	/**
	 * 说明 : 新增职位编制
	 * 
	 * @param jobOrg
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:21:30
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult addJobOrg(JobOrg jobOrg) {
		JsonResult jsonResult = new JsonResult();
		
		if (null == jobOrg.getDeptId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("部门不能为空!");
			return jsonResult;
		}
		if (null == jobOrg.getJobId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位不能为空!");
			return jsonResult;
		}
		if (null == jobOrg.getOrganizeNum()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("编制人数不能为空!");
			return jsonResult;
		}
		if (null == jobOrg.getWorkSystemId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("工作制不能为空!");
			return jsonResult;
		}
		
		JobVo jobVo = jobService.getInfo(jobOrg.getJobId());
		
		if (null == jobVo) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位不能为空!");
			return jsonResult;
		}
		
		Long jobGradeId = jobVo.getJobGradeId();  //参数：职位等级ID
		Long deptId = jobOrg.getDeptId();  //参数： 部门ID
		String[] deptIdArray = null;  //参数： 管辖子部门集合
		List<String> deptIdList = null; 
		if (StringUtils.isNotEmpty(jobOrg.getDeptIds())) {
			deptIdArray = StringUtils.split(jobOrg.getDeptIds(), ",");
			deptIdList = java.util.Arrays.asList(deptIdArray);
		}else{
			// TODO 保存数据关系，不存在管辖子部门
			try {
				jobOrgService.saveJobOrg(jobOrg);
			} catch (RuntimeException runtime) {
				jsonResult.setCode(HttpCode.BAD_REQUEST);
				jsonResult.setMsg("参数异常!");
				runtime.printStackTrace();
			} catch (Exception e) {
				jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
				jsonResult.setMsg("操作失敗!");
				e.printStackTrace();
			}
			return jsonResult;
		}
		
		/**
		 * 新增验证逻辑 :
		 * a.必须按照职位等级从高到低创建
		 * b.子编制管辖子部门  <= 父编制管辖子部门
		 * c.相同等级子编制管辖子部门不能存在交集
		 */
		
		//a.判断该职位等级是否在同部门下存在职位等级低的编制，如果存在，提示
		List<JobOrg> childJobOrgs = jobOrgService.getChildJobOrg(deptId, jobGradeId);
		if(null != childJobOrgs && !childJobOrgs.isEmpty()){
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("编制只能按照职位等级从高到低创建!");
			return jsonResult;
		}
		
		//b.判断管辖子部门是否全部属于等级高的编制，如果不是，提示
		List<JobOrg> parentJobOrgs = jobOrgService.getParentJobOrg(deptId, jobGradeId);
		
		//同级编制
		List<JobOrg> siblingsJobOrgs = jobOrgService.getSiblingsJobOrg(deptId, jobGradeId);
		
		if(null == parentJobOrgs || parentJobOrgs.isEmpty()){ //b-1   如果不存在父级编制，则该部门为首次创建编制，无需验证
			
			//c.判断同级编制是否存在共同管辖部门，如果存在，提示
			if(null == siblingsJobOrgs || siblingsJobOrgs.isEmpty()){ //c-1   不存在同级编制，无需验证
				// 保存数据关系，存在管辖子部门，不包括父子编制关系
				try {
					jobOrgService.saveJobOrg(jobOrg);
			    } catch (RuntimeException runtime) {
					jsonResult.setCode(HttpCode.BAD_REQUEST);
					jsonResult.setMsg("参数异常!");
					runtime.printStackTrace();
				} catch (Exception e) {
					jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
					jsonResult.setMsg("操作失敗!");
					e.printStackTrace();
				}
				return jsonResult;
			}else{ 
				Boolean result = validateSiblingsOrg(deptIdList, siblingsJobOrgs); //c-2   存在同级编制，验证管辖部门是否存在交集，如果存在，提示
				if(!result){
					jsonResult.setCode(HttpCode.BAD_REQUEST);
					jsonResult.setMsg("管辖子部门已被同级编制所管理!");
					return jsonResult;
				}
				
				// 保存数据关系，存在管辖子部门，不包括父子编制关系
				try {
					jobOrgService.saveJobOrg(jobOrg);
			    } catch (RuntimeException runtime) {
					jsonResult.setCode(HttpCode.BAD_REQUEST);
					jsonResult.setMsg("参数异常!");
					runtime.printStackTrace();
				} catch (Exception e) {
					jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
					jsonResult.setMsg("操作失敗!");
					e.printStackTrace();
				}
				return jsonResult;
			}
		}else{ //b-2   如果存在父级编制，验证
			Boolean resultSecond = false;
			List<String> parentDeptIdList = new ArrayList<String>();
			for(JobOrg parentJobOrg : parentJobOrgs){  //循环上级编制，比对 子编制管辖子部门  <= 父编制管辖子部门
				List<OrgDept> parentOrgDeptList = parentJobOrg.getOrgDeptList();
				if(null != parentOrgDeptList && !parentOrgDeptList.isEmpty()){	
					for(int i = 0; i < parentOrgDeptList.size(); i++){
						parentDeptIdList.add(parentOrgDeptList.get(i).getDeptId() + "");
					}
					if(parentDeptIdList.containsAll(deptIdList)){
						resultSecond = true;
						
						if(null == siblingsJobOrgs || siblingsJobOrgs.isEmpty()){ //c-1   不存在同级编制，无需验证
							//TODO 保存数据关系，存在管辖子部门，包括父子编制关系
							jobOrg.setParentId(parentJobOrg.getId());
							try {
								jobOrgService.saveJobOrg(jobOrg);
							} catch (RuntimeException runtime) {
								jsonResult.setCode(HttpCode.BAD_REQUEST);
								jsonResult.setMsg("参数异常!");
								runtime.printStackTrace();
							} catch (Exception e) {
								jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
								jsonResult.setMsg("操作失敗!");
								e.printStackTrace();
							}
							return jsonResult;
						}else{ 
							Boolean result = validateSiblingsOrg(deptIdList, siblingsJobOrgs); //c-2   存在同级编制，验证管辖部门是否存在交集，如果存在，提示
							if(!result){
								jsonResult.setCode(HttpCode.BAD_REQUEST);
								jsonResult.setMsg("管辖子部门已被同级编制所管理!");
								return jsonResult;
							}
							//TODO 保存数据关系，存在管辖子部门，包括父子编制关系
							jobOrg.setParentId(parentJobOrg.getId());
							try {
								jobOrgService.saveJobOrg(jobOrg);
							} catch (RuntimeException runtime) {
								jsonResult.setCode(HttpCode.BAD_REQUEST);
								jsonResult.setMsg("参数异常!");
								runtime.printStackTrace();
							} catch (Exception e) {
								jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
								jsonResult.setMsg("操作失敗!");
								e.printStackTrace();
							}
							return jsonResult;
						}	
					}
					
				}
			}
			if(!resultSecond){
				jsonResult.setCode(HttpCode.BAD_REQUEST);
				jsonResult.setMsg("编制管辖子部门必须包含于上级编制管辖子部门!");
				return jsonResult;
			}
		}	

		return jsonResult;
	}

	/**
	 * 说明 : 验证新增管辖子部门是否已被同级编制所管理
	 * 
	 * @param id
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月29日 下午9:21:30
	 */
	private Boolean validateSiblingsOrg(List<String> deptIdList, List<JobOrg> siblingsJobOrgs) {
		List<String> siblingsDeptIdList = new ArrayList<String>();
		for(JobOrg siblingsJobOrg : siblingsJobOrgs){  //循环同级编制，比对 管辖子部门是否存在交集
			List<OrgDept> siblingsOrgDeptList = siblingsJobOrg.getOrgDeptList();
			if(null != siblingsOrgDeptList && !siblingsOrgDeptList.isEmpty()){	
				//String[] siblingsDeptIdArray = new String[siblingsOrgDeptList.size()];
				//List<String> siblingsDeptIdList = java.util.Arrays.asList(siblingsDeptIdArray);
				
				for(int i = 0; i < siblingsOrgDeptList.size(); i++){
					siblingsDeptIdList.add(siblingsOrgDeptList.get(i).getDeptId() + "");
				}
				siblingsDeptIdList.retainAll(deptIdList); //集合交集
				if(null != siblingsDeptIdList && !siblingsDeptIdList.isEmpty()){
					
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 说明 : 根据ID获取职位编制信息
	 * 
	 * @param id
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:21:30
	 */
	@ResponseBody
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public JsonResult getInfo(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			JobOrgVo jobOrgVo = jobOrgService.getInfo(id);
			if (null == jobOrgVo) {
				jsonResult.setCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("查无信息!");
				return jsonResult;
			}
			jsonResult.setData(jobOrgVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 通过职位编制ID查找子部门集合
	 *
	 * @param id
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月19日 下午5:21:30
	 */
	@ResponseBody
	@RequestMapping(value = "getChindDept", method = RequestMethod.GET)
	public JsonResult getChindDept(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			List<OrgDept> orgDeptList = jobOrgService.getChindDept(id);
			if (null == orgDeptList) {
				jsonResult.setCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("查无信息!");
				return jsonResult;
			}
			jsonResult.setData(orgDeptList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 编辑职位编制信息
	 * 
	 * @param jobOrg
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:21:30
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonResult update(JobOrg jobOrg) {
		JsonResult jsonResult = new JsonResult();

		//修改逻辑 :只能修改编制数量
		if (null == jobOrg.getDeptId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("部门不能为空!");
			return jsonResult;
		}
		if (null == jobOrg.getJobId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位不能为空!");
			return jsonResult;
		}
		if (null == jobOrg.getOrganizeNum()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("编制人数不能为空!");
			return jsonResult;
		}
		if (null == jobOrg.getWorkSystemId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("工作制不能为空!");
			return jsonResult;
		}

		try {
			jobOrgService.updateJobOrg(jobOrg);
		} catch (RuntimeException runtime) {
			runtime.printStackTrace();
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位编制代号已存在!");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 导入CSV文件
	 *
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:21:30
	 */
	@RequestMapping("export")
	public void exportCsv() {
		// TODO 职位编制信息导出
	}
	
	
	
	/**
	 * 说明 : 获得部门一览
	 *
	 * @return
	 * @author 王晨
	 * @dateTime 2017年5月21日 上午01:42:50
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult list(
				@RequestParam(required = false, defaultValue = "") String deptId,
				@RequestParam(required = false, defaultValue = "") String kw,
				@RequestParam(required = false, defaultValue = "") String jobTypeId
			) {
		JsonResult jsonResult = new JsonResult();
		try {
			Map<String,Object> parMap = Maps.newHashMap();
			parMap.put("id", deptId);
			parMap.put("kw", kw);
			parMap.put("jobTypeId", jobTypeId);
			List<DeptOrgTreeVO> retDepts= jobOrgService.jobOrgList(parMap);
			jsonResult.setData(retDepts);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return jsonResult;
	}
	
	
	/**
	 * 说明 : 获得区域支店
	 *
	 * @return
	 * @author 王晨
	 * @dateTime 2017年5月16日 下午10:50:50
	 */
	@ResponseBody
	@RequestMapping(value = "areaDept", method = RequestMethod.GET)
	public JsonResult getDeptTree(
				@RequestParam(required = false, defaultValue = "") String id
			) {
		JsonResult jsonResult = new JsonResult();
		try {
			Map<String,Object> parMap = Maps.newHashMap();
			parMap.put("id", id);
			Dept dept= deptService.getDeptTreeByPar(parMap);
			jsonResult.setData(dept);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return jsonResult;
	}


}
