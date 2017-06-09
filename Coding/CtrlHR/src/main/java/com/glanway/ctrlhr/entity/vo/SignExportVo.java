/*
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.ctrlhr.entity.vo;

import java.util.Date;

/**
 * TODO - 2017-05-12 fuqihao complete Class JavaDoc.
 * 
 * @author fuqihao
 * @version 1.0-20170512
 * @since 1.0-20170512
 */
public class SignExportVo {

    private String employeeCode;// 员工代号

    private String signDate;// 考勤日期

    private String week;// 星期

    private Date timeFrom;// 第一阶段上班时间

    private Date timeTo;// 第一阶段下班时间

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

}
