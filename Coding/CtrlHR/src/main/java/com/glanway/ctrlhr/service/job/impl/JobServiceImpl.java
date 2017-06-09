package com.glanway.ctrlhr.service.job.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.employee.EmployeeDao;
import com.glanway.ctrlhr.dao.job.JobDao;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.job.Job;
import com.glanway.ctrlhr.entity.job.JobGrade;
import com.glanway.ctrlhr.entity.job.JobType;
import com.glanway.ctrlhr.entity.job.SalaryType;
import com.glanway.ctrlhr.entity.job.WorkSystem;
import com.glanway.ctrlhr.entity.para.JobPara;
import com.glanway.ctrlhr.entity.vo.JobVo;
import com.glanway.ctrlhr.entity.vo.SimpleJobVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.job.JobService;

/**
 * 说明 :
 *
 * @author 于瑞智
 * @version 1.0.0
 * @dateTime 2017年5月16日 下午9:15:18
 */
@Transactional
@Service("jobService")
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Page<JobVo> findList(JobPara para) {
        if (StringUtils.isNotEmpty(para.getKeyword())) {
            para.setKeyword(para.getKeyword());
        }
        if (StringUtils.isNotEmpty(para.getJobTypeIds())) {
            String[] jobTypeList = StringUtils.split(para.getJobTypeIds(), ",");
            para.setJobTypeIdList(jobTypeList);
        }
        if (StringUtils.isNotEmpty(para.getJobGradeIds())) {
            String[] jobGradeList = StringUtils.split(para.getJobGradeIds(), ",");
            para.setJobGradeIdList(jobGradeList);
        }
        if (StringUtils.isNotEmpty(para.getSalaryTypeIds())) {
            String[] salaryTypeList = StringUtils.split(para.getSalaryTypeIds(), ",");
            para.setSalaryTypeIdList(salaryTypeList);
        }
        int count = jobDao.findListCount(para);
        Page<JobVo> page = new Page<>(para.getPage(), count, para.getPageSize());
        if (para.getPage() <= page.getCountPage()) {
            para.setStartIndex(page.getStartIndex());
            List<JobVo> list = jobDao.findList(para);
            page.setList(list);
        }
        return page;
    }

    @Override
    public void saveJob(Job job) {
        /*
         * Job emp = jobDao.findByName(job.getName());
         * if (null != emp) {
         * throw new RuntimeException();
         * }
         */
        job.setId(null);
        job.setState(1);
        job.setBatchDate(new Date());
        // TODO 创建人ID写死,后期需要更改
        job.setCreatedBy(1L);
        job.setCreatedDate(new Date());
        // TODO 创建程序ID写死,后期需要更改
        job.setCreProId(1L);
        // TODO 最后更新人ID写死,后期需要更改
        job.setLastModifiedBy(1L);
        job.setLastModifiedDate(job.getCreatedDate());
        // TODO 更新程序ID写死,后期需要更改
        job.setModProId(1L);

        jobDao.insertSelective(job);
    }

    @Override
    public JobVo getInfo(Long id) {
        return jobDao.findOne(id);
    }

    @Override
    public void updateJob(Job job) {
        /*
         * if (StringUtils.isNotEmpty(job.getName())) {
         * Job emp = jobDao.findByName(job.getName());
         * if (null != emp) {
         * throw new RuntimeException();
         * }
         * }
         */
        // TODO 最后更新人ID写死,后期需要更改
        job.setLastModifiedBy(1L);
        job.setLastModifiedDate(new Date());
        // TODO 更新程序ID写死,后期需要更改
        job.setModProId(1L);
        jobDao.updateByPrimaryKeySelective(job);
    }

    @Override
    public JsonResult delete(String ids) {
        JsonResult result = new JsonResult();
        // 数据处理
        String[] idArr = StringUtils.split(ids, ",");
        // 删除要查询职位信息
        for (String id : idArr) {
            JobVo jobVo = jobDao.findOne(Long.parseLong(id));
            if (jobVo != null) {
                // 判断职位是否已被使用，如果已使用不能删除
                List<Employee> employeeList = employeeDao.findEmployeeByJob(Long.parseLong(id));
                if (null != employeeList && !employeeList.isEmpty()) {
                    result.setCode(HttpCode.FORBIDDEN);
                    result.setMsg("职位已被使用");
                    return result;
                }
                // 此处为逻辑删除,更新删除字段为1
                jobDao.delete(id);
            } else {
                result.setCode(HttpCode.FORBIDDEN);
                result.setMsg("参数有误");
                return result;
            }
        }
        return result;
    }

    @Override
    public List<JobType> getJobType() {
        return jobDao.findJobType();
    }

    @Override
    public List<JobGrade> getJobGrade() {
        return jobDao.findJobGrade();
    }

    @Override
    public List<SalaryType> getSalaryType() {
        return jobDao.findSalaryType();
    }

    @Override
    public List<WorkSystem> getWorkSystem() {
        return jobDao.findWorkSystem();
    }

    @Override
    public List<SimpleJobVo> findSimpleJobByDeptId(String deptIds) {
        String[] deptIdArr = null;
        if (StringUtils.isNotEmpty(deptIds)) {
            deptIdArr = StringUtils.split(deptIds, ",");
        }
        return jobDao.findSimpleJobByDeptId(deptIdArr);
    }

    @Override
    public List<SimpleJobVo> findDropDownList() {
        return jobDao.findDropDownList();
    }
}
