package com.glanway.ctrlhr.service.job;

import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.job.*;
import com.glanway.ctrlhr.entity.para.JobPara;
import com.glanway.ctrlhr.entity.vo.JobVo;
import com.glanway.ctrlhr.entity.vo.SimpleJobVo;
import com.glanway.ctrlhr.service.BaseService;

import java.util.List;

public interface JobService extends BaseService<Job> {

    /**
     * 说明 : 查询职位分页列表
     * 
     * @param para
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月16日 下午9:11:18
     */
    public Page<JobVo> findList(JobPara para);

    /**
     * 说明 : 新增职位
     * 
     * @param job
     * @author 于瑞智
     * @dateTime 2017年5月16日 下午9:11:18
     */
    public void saveJob(Job job);

    /**
     * 说明 : 根据ID获取职位信息
     * 
     * @param id
     * @return
     * @author 于瑞智
     * @dateTime 2017年5月16日 下午9:11:18
     */
    public JobVo getInfo(Long id);

    /**
     * 说明 : 编辑职位信息
     * 
     * @param job
     * @author 于瑞智
     * @dateTime 2017年5月16日 下午9:11:18
     */
    public void updateJob(Job job);

    /**
     * 说明 : 删除职位信息
     *
     * @param ids
     * @author 于瑞智
     * @dateTime 2017年5月18日 上午11:31:18
     */
    public JsonResult delete(String ids);

    /**
     * 说明 : 获取职位类型集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:31:18
     */
    public List<JobType> getJobType();

    /**
     * 说明 : 获取职位等级集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:33:18
     */
    public List<JobGrade> getJobGrade();

    /**
     * 说明 : 获取薪资类型集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午2:34:18
     */
    public List<SalaryType> getSalaryType();

    /**
     * 说明 : 获取工作制集合
     *
     * @author 于瑞智
     * @dateTime 2017年5月18日 下午5:34:18
     */
    public List<WorkSystem> getWorkSystem();

    /**
     * 说明 : 吴景松
     *
     * @author wujingong
     * @param deptIds
     * @return
     * @since 1.0-20170522
     */
    public List<SimpleJobVo> findSimpleJobByDeptId(String deptIds);

    /**
     * 查询新建编制的职位下拉列表.
     *
     * @author fuqihao
     * @return
     * @since 1.0-20170526
     */
    public List<SimpleJobVo> findDropDownList();
}
