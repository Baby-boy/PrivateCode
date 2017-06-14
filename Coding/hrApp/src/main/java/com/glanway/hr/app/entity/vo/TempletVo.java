package com.glanway.hr.app.entity.vo;

import java.util.Date;

/**
 * 响应参数封装类.
 * 
 * 冗余参数用Vo类进行定义封装.
 *
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
public class TempletVo {

    private Long id; // ID

    private String name;// 员工名称

    private String code;// 员工代号

    private Integer jobState;// 在职状态(1试用:, 2:正式, 3:离职)

    private Date entryDate;// 入职时间

    private Date formalDate;// 转正时间

    private Date quitDate;// 离职时间

    private Long deptId;// 部门ID

    private String deptName;// 部门名称

    private Long jobId;// 职位ID

    private String jobName;// 职位名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getJobState() {
        return jobState;
    }

    public void setJobState(Integer jobState) {
        this.jobState = jobState;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getFormalDate() {
        return formalDate;
    }

    public void setFormalDate(Date formalDate) {
        this.formalDate = formalDate;
    }

    public Date getQuitDate() {
        return quitDate;
    }

    public void setQuitDate(Date quitDate) {
        this.quitDate = quitDate;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}
