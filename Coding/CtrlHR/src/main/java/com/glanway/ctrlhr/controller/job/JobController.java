package com.glanway.ctrlhr.controller.job;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.job.*;
import com.glanway.ctrlhr.entity.para.JobPara;
import com.glanway.ctrlhr.entity.vo.JobVo;
import com.glanway.ctrlhr.entity.vo.SimpleJobVo;
import com.glanway.ctrlhr.service.job.JobService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 说明 : 职位管理相关
 * 
 * @author 于瑞智
 * @version 1.0.0
 * @dateTime 2017年5月15日 下午4:42:30
 */
@Controller
@RequestMapping("api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 说明 : 查询职位列表(分页
     * 
     * @param para(请求参数)
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月15日 下午4:42:30
     * @remark http://localhost:8080/hr/api/job/list
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public JsonResult list(JobPara para) {
        JsonResult jsonResult = new JsonResult();

        try {
            Page<JobVo> page = jobService.findList(para);
            jsonResult.setData(page);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }

        return jsonResult;
    }

    /**
     * 说明 : 新增职位
     * 
     * @param job
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月16日 下午4:45:26
     * @remark http://localhost:8080/hr/api/job/add
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonResult addJob(Job job) {
        JsonResult jsonResult = new JsonResult();

        if (StringUtils.isEmpty(job.getName())) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位名称不能为空!");
            return jsonResult;
        }
        if (null == job.getJobTypeId()) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位类型为空!");
            return jsonResult;
        }
        if (null == job.getJobGradeId()) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位等级不能为空!");
            return jsonResult;
        }

        if (null == job.getSalaryTypeId()) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("薪资类型不能为空!");
            return jsonResult;
        }

        try {
            jobService.saveJob(job);
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
     * 说明 : 根据ID获取职位信息
     * 
     * @param id
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:30:52
     * @remark http://localhost:8080/hr/api/job/getInfo?id=1
     */
    @ResponseBody
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public JsonResult getInfo(Long id) {
        JsonResult jsonResult = new JsonResult();
        try {
            JobVo jobVo = jobService.getInfo(id);
            if (null == jobVo) {
                jsonResult.setCode(HttpCode.NO_CONTENT);
                jsonResult.setMsg("查无信息!");
                return jsonResult;
            }
            jsonResult.setData(jobVo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }

        return jsonResult;
    }

    /**
     * 说明 : 编辑职位信息
     * 
     * @param job
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月16日 下午3:01:46
     * @remark http://localhost:8080/hr/api/job/update
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(Job job) {
        JsonResult jsonResult = new JsonResult();

        if (StringUtils.isEmpty(job.getName())) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位名称不能为空!");
            return jsonResult;
        }
        if (null == job.getJobTypeId()) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位类型为空!");
            return jsonResult;
        }
        if (null == job.getJobGradeId()) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位等级不能为空!");
            return jsonResult;
        }

        try {
            jobService.updateJob(job);
        } catch (RuntimeException runtime) {
            runtime.printStackTrace();
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("职位已存在!");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }

        return jsonResult;
    }

    /**
     * 说明 : 删除职位信息
     *
     * @param ids
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月18日 上午11:27:22
     * @remark http://localhost:8080/hr/api/job/delete
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonResult delete(String ids) {
        JsonResult jsonResult = new JsonResult();

        if (StringUtils.isEmpty(ids)) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            jsonResult.setMsg("参数有误!");
            return jsonResult;
        }
        try {
            JsonResult result = jobService.delete(ids);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 说明 : 获取职位类型集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:30:52
     * @remark http://localhost:8080/hr/api/job/getJobType
     */
    @ResponseBody
    @RequestMapping(value = "getJobType", method = RequestMethod.GET)
    public JsonResult getJobType() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<JobType> jobTypeList = jobService.getJobType();
            if (null == jobTypeList || jobTypeList.isEmpty()) {
                jsonResult.setCode(HttpCode.NO_CONTENT);
                jsonResult.setMsg("查无信息!");
                return jsonResult;
            }
            jsonResult.setData(jobTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 说明 : 获取职位等级集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:30:52
     * @remark http://localhost:8080/hr/api/job/getJobGrade
     */
    @ResponseBody
    @RequestMapping(value = "getJobGrade", method = RequestMethod.GET)
    public JsonResult getJobGrade() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<JobGrade> jobGradeList = jobService.getJobGrade();
            if (null == jobGradeList || jobGradeList.isEmpty()) {
                jsonResult.setCode(HttpCode.NO_CONTENT);
                jsonResult.setMsg("查无信息!");
                return jsonResult;
            }
            jsonResult.setData(jobGradeList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 说明 : 获取薪资类型集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:35:52
     * @remark http://localhost:8080/hr/api/job/getSalaryType
     */
    @ResponseBody
    @RequestMapping(value = "getSalaryType", method = RequestMethod.GET)
    public JsonResult getSalaryType() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<SalaryType> salaryTypeList = jobService.getSalaryType();
            if (null == salaryTypeList || salaryTypeList.isEmpty()) {
                jsonResult.setCode(HttpCode.NO_CONTENT);
                jsonResult.setMsg("查无信息!");
                return jsonResult;
            }
            jsonResult.setData(salaryTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 说明 : 获取工作制集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午5:35:52
     * @remark http://localhost:8080/hr/api/job/getWorkSystem
     */
    @ResponseBody
    @RequestMapping(value = "getWorkSystem", method = RequestMethod.GET)
    public JsonResult getWorkSystem() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<WorkSystem> workSystemList = jobService.getWorkSystem();
            if (null == workSystemList || workSystemList.isEmpty()) {
                jsonResult.setCode(HttpCode.NO_CONTENT);
                jsonResult.setMsg("查无信息!");
                return jsonResult;
            }
            jsonResult.setData(workSystemList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 查询部门下的职位
     * 
     * @param jobDeptId 部门ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "simpleList", method = RequestMethod.GET)
    public JsonResult simpleList(String deptIds) {
        JsonResult jsonResult = new JsonResult();
        
        try {
            List<SimpleJobVo> list = jobService.findSimpleJobByDeptId(deptIds);
            jsonResult.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 查询新建编制的职位下拉列表.
     *
     * @author fuqihao
     * @return
     * @since 1.0-20170526
     */
    @ResponseBody
    @RequestMapping(value = "dropDownList", method = RequestMethod.GET)
    public JsonResult dropDownList() {
        JsonResult jsonResult = new JsonResult();

        try {
            List<SimpleJobVo> list = jobService.findDropDownList();
            jsonResult.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

}
