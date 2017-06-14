/**
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.hr.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.service.employee.EmployeeService;
import com.glanway.hr.app.common.HttpCode;
import com.glanway.hr.app.common.JsonResult;
import com.glanway.hr.app.common.Page;
import com.glanway.hr.app.entity.para.TempletPara;
import com.glanway.hr.app.entity.vo.TempletVo;
import com.glanway.hr.app.service.TempletService;

import oracle.net.aso.e;

/**
 * Controller层代码模板.
 *
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
@Controller
@RequestMapping("api/templet")
public class TempletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TempletController.class);

    @Autowired
    private TempletService templetService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询模板列表. 
     *
     * @param para
     * @return 
     * @author fuqihao
     * @since 2017年6月9日
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public JsonResult list() {
        JsonResult jsonResult = new JsonResult();

        try {
            // Page<TempletVo> page = templetService.findList(para);
            EmployeePara para = new EmployeePara();
            jsonResult.setData(employeeService.findList(para));
        } catch (Exception e) {
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
            LOGGER.info("查询模板列表时异常信息为: {}", e.getMessage());
        }

        return jsonResult;
    }
}
