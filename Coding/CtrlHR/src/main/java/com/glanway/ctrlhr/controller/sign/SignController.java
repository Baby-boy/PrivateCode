package com.glanway.ctrlhr.controller.sign;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glanway.ctrlhr.controller.BaseController;
import com.glanway.ctrlhr.entity.para.SignPara;
import com.glanway.ctrlhr.entity.vo.SignDetailVo;
import com.glanway.ctrlhr.entity.vo.SignExportVo;
import com.glanway.ctrlhr.service.sign.SignService;
import com.glanway.ctrlhr.util.TimeUtil;

@Controller
@RequestMapping("/api/sign")
public class SignController extends BaseController {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "export")
    public void SignExportList(SignPara para, HttpServletResponse response) {
        if (StringUtils.isEmpty(para.getDateStart())) {
            para.setDateStart(TimeUtil.getMonthTimeStart());
        } else {
            if (13 != para.getDateStart().length()) {
                para.setDateStart(TimeUtil.getMonthTimeStart());
            } else {
                para.setDateStart(TimeUtil.format(new Date(Long.valueOf(para.getDateStart()))));
            }
        }

        if (StringUtils.isEmpty(para.getDateEnd())) {
            para.setDateEnd(TimeUtil.getDateEnd());
        } else {
            if (13 != para.getDateEnd().length()) {
                para.setDateEnd(TimeUtil.getDateEnd());
            } else {
                para.setDateEnd(TimeUtil.format(new Date(Long.valueOf(para.getDateEnd()))));
            }
        }

        BufferedWriter bw = null;
        try {
            // 获取考勤记录的列表
            List<SignExportVo> list = signService.findSignExportList(para);

            // 只查询对应条件的员工ID
            List<SignExportVo> employees = signService.findEmployeeList(para);

            response.setHeader("Content-Disposition", "attachment;filename=SignRecordDetail.csv");

            // 定义一个list用于存储生成好的时间
            List<SignExportVo> tempList = new ArrayList<>();

            for (SignExportVo signExportVo : employees) {
                Integer integer = 1;
                for (Date date = TimeUtil.StrToDate(para.getDateStart()); date
                    .compareTo(TimeUtil.StrToDate(para.getDateEnd())) < 0; date = TimeUtil
                        .getLastDay(TimeUtil.StrToDate(para.getDateStart()), integer++)) {
                    SignExportVo sign = new SignExportVo();
                    sign.setEmployeeCode(signExportVo.getEmployeeCode());
                    sign.setSignDate(TimeUtil.format(date, TimeUtil.FORMAT_YYYY_MM_DD));
                    tempList.add(sign);
                }
            }

            for (SignExportVo signExportVo : list) {
                for (SignExportVo exportVo : tempList) {
                    if (exportVo.getEmployeeCode().equals(signExportVo.getEmployeeCode())
                        && exportVo.getSignDate().equals(signExportVo.getSignDate())) {
                        exportVo.setTimeFrom(signExportVo.getTimeFrom());
                        exportVo.setTimeTo(signExportVo.getTimeTo());
                    }
                }
            }

            response.setCharacterEncoding("GBK");
            bw = new BufferedWriter(response.getWriter());
            if (null != list && list.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("职员代码").append(",");
                buffer.append("考勤日期").append(",");
                buffer.append("星期").append(",");
                buffer.append("第一阶段上班时间").append(",");
                buffer.append("第一阶段下班时间");
                bw.write(buffer.toString());
                bw.newLine();
                for (SignExportVo signExportVo : tempList) {
                    writeLine(getSignExportCvsList(signExportVo), bw);
                }
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private List<String> getSignExportCvsList(SignExportVo signExportVo) {
        List<String> cvsList = new ArrayList<>();
        cvsList.add(signExportVo.getEmployeeCode());
        if (null == signExportVo.getSignDate()) {
            cvsList.add(null);
            cvsList.add(null);
        } else {
            cvsList.add(signExportVo.getSignDate());
            cvsList.add(TimeUtil.getWeek(TimeUtil.StrToDateLong(signExportVo.getSignDate())));
        }
        if (null == signExportVo.getTimeFrom()) {
            cvsList.add(null);
        } else {
            cvsList.add(TimeUtil.format(signExportVo.getTimeFrom(), TimeUtil.FORMAT_HH_MM));
        }
        if (null == signExportVo.getTimeTo()) {
            cvsList.add(null);
        } else if (signExportVo.getTimeFrom().compareTo(signExportVo.getTimeTo()) == 0) {
            cvsList.add(null);
        } else {
            cvsList.add(TimeUtil.format(signExportVo.getTimeTo(), TimeUtil.FORMAT_HH_MM));
        }

        return cvsList;
    }

    /**
     * 说明 : 考勤记录的导出
     * 
     * @param para
     * @param response
     * @author 付其浩
     * @dateTime 2017年4月24日 上午11:59:27
     */
    @RequestMapping(value = "exportDetail")
    public void list(SignPara para, HttpServletResponse response) {

        if (StringUtils.isEmpty(para.getDateStart())) {
            para.setDateStart(TimeUtil.getMonthTimeStart());
        } else {
            if (13 != para.getDateStart().length()) {
                para.setDateStart(TimeUtil.getMonthTimeStart());
            } else {
                para.setDateStart(TimeUtil.format(new Date(Long.valueOf(para.getDateStart()))));
            }
        }

        if (StringUtils.isEmpty(para.getDateEnd())) {
            para.setDateEnd(TimeUtil.getDateEnd());
        } else {
            if (13 != para.getDateEnd().length()) {
                para.setDateEnd(TimeUtil.getDateEnd());
            } else {
                para.setDateEnd(TimeUtil.format(new Date(Long.valueOf(para.getDateEnd()))));
            }
        }

        BufferedWriter bw = null;
        try {
            // 获取考勤记录的列表
            List<SignDetailVo> list = signService.findMany(para);

            response.setHeader("Content-Disposition", "attachment;filename=SignRecordDetail.csv");

            response.setCharacterEncoding("GBK");
            bw = new BufferedWriter(response.getWriter());
            if (null != list && list.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("姓名").append(",");
                buffer.append("职员代码").append(",");
                buffer.append("部门名称").append(",");
                buffer.append("职位名称").append(",");
                buffer.append("考勤时间");
                bw.write(buffer.toString());
                bw.newLine();
                for (SignDetailVo signVo : list) {
                    writeLine(getSignDetailCvsList(signVo), bw);
                }
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<String> getSignDetailCvsList(SignDetailVo signVo) {
        List<String> cvsList = new ArrayList<>();
        cvsList.add(signVo.getEmployeeName());
        cvsList.add(signVo.getEmployeeCode());
        cvsList.add(signVo.getDeptName());
        cvsList.add(signVo.getJobName());
        if (null == signVo.getTime()) {
            cvsList.add(null);
        } else {
            cvsList.add(TimeUtil.format(signVo.getTime()));
        }

        return cvsList;
    }

}
