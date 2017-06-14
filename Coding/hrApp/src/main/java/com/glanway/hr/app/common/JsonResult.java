package com.glanway.hr.app.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口公共数据返回封装.
 *
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 2955969952867505921L;

    /** 状态码 */
    private String code = HttpCode.OK.toString();

    /** 提示信息 */
    private String msg = "操作成功!";

    /** 登录状态(1:未登录, 2:已登录, 3:未检查) */
    private Integer loginState = 2;

    /** 数据 */
    private Object data = new Object();

    /** 扩展数据 */
    private Map<String, Object> dataMap = new HashMap<>();

    public String getCode() {
        return code;
    }

    public void setCode(HttpCode httpCode) {
        this.code = httpCode.toString();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

}
