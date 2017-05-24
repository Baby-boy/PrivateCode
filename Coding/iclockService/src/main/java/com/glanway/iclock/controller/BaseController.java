package com.glanway.iclock.controller;

import com.google.common.collect.ImmutableMap;
import org.ponly.webbase.controller.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 每个项目都有的 Base Controller, 项目中 Controller 都应该继承, 方便后续增加逻辑
 *
 * @author yangchanghe
 * @deprecated
 * @see com.glanway.eshop.admin.controller.AdminBaseController
 */
@Deprecated
public abstract class BaseController extends org.ponly.webbase.controller.ControllerSupport {
    /*
    @ResponseBody
    @ExceptionHandler
    public Map<String, Object> handleException(Exception e) {
        e.printStackTrace();
        return ImmutableMap.<String, Object>of("success", false, "message", e.getLocalizedMessage());
    }
    */

    protected String getViewPath(String path) {
        return getRelativeViewPath(path);
    }
}
