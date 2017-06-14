package com.glanway.hr.app.entity.para;

/**
 * 请求参数封装类,所有和表对应的实体类不可修改及变动,需要和表结构一一对应.
 * 
 * 冗余参数用Para类进行定义封装.
 *
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
public class TempletPara extends BasePara {

    private String keyword;// 搜索关键字

    private Long companyId;// 公司ID

    private Long deptId; // 部门id

    private String deptIds;// 部门ID(备注: 多个ID使用","分隔)

    private Integer jobId;// 职位ID

    private String jobStates;// 在职状态(1试用:, 2:正式, 3:离职)(备注: 多个ID使用","分隔)

    private String entryDateFrom;// 入职日期检索-开始

    private String entryDateTo;// 入职日期检索-结束

    private String formalDateFrom;// 转正日期检索-开始

    private String formalDateTo;// 转正日期检索-结束

    private String quitDateFrom;// 离职日期检索-开始

    private String quitDateTo;// 离职日期检索-结束

    private String gatherMsgStates;// 员工信息采集状态

    private String[] gatherMsgStateList;// 员工信息采集数组

    private String[] deptIdList;// id数组

    private String[] jobStateList;// 状态数组

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobStates() {
        return jobStates;
    }

    public void setJobStates(String jobStates) {
        this.jobStates = jobStates;
    }

    public String getEntryDateFrom() {
        return entryDateFrom;
    }

    public void setEntryDateFrom(String entryDateFrom) {
        this.entryDateFrom = entryDateFrom;
    }

    public String getEntryDateTo() {
        return entryDateTo;
    }

    public void setEntryDateTo(String entryDateTo) {
        this.entryDateTo = entryDateTo;
    }

    public String getFormalDateFrom() {
        return formalDateFrom;
    }

    public void setFormalDateFrom(String formalDateFrom) {
        this.formalDateFrom = formalDateFrom;
    }

    public String getFormalDateTo() {
        return formalDateTo;
    }

    public void setFormalDateTo(String formalDateTo) {
        this.formalDateTo = formalDateTo;
    }

    public String getQuitDateFrom() {
        return quitDateFrom;
    }

    public void setQuitDateFrom(String quitDateFrom) {
        this.quitDateFrom = quitDateFrom;
    }

    public String getQuitDateTo() {
        return quitDateTo;
    }

    public void setQuitDateTo(String quitDateTo) {
        this.quitDateTo = quitDateTo;
    }

    public String getGatherMsgStates() {
        return gatherMsgStates;
    }

    public void setGatherMsgStates(String gatherMsgStates) {
        this.gatherMsgStates = gatherMsgStates;
    }

    public String[] getGatherMsgStateList() {
        return gatherMsgStateList;
    }

    public void setGatherMsgStateList(String[] gatherMsgStateList) {
        this.gatherMsgStateList = gatherMsgStateList;
    }

    public String[] getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(String[] deptIdList) {
        this.deptIdList = deptIdList;
    }

    public String[] getJobStateList() {
        return jobStateList;
    }

    public void setJobStateList(String[] jobStateList) {
        this.jobStateList = jobStateList;
    }

}
