/**
 * @author zhangshuang
 * 2017年4月17日 上午11:14:05
 */
package com.glanway.iclock.common;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 说明 : 
 * 考勤接口参数-封装类
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月17日 上午11:14:05
 */
public class SignParam {
	/****************************************** url ****************************************************************/
	 /**
     * PUSH API 接口命名空间.
     */
    public static final String PUSH_NS_URI = "/iclock";

    /**
     * PUSH API 配置查看地址.
     */
    public static final String PUSH_CFG_URI = "/iclock/cfg";

    /**
     * 设备获取初始化配置URI.
     */
    public static final String PUSH_INIT_URI = "/iclock/cdata";

    /**
     * 设备获取指令URI.
     */
    public static final String PULL_COMMANDS_URI = "/iclock/getrequest";

    /**
     * 设备推送数据URI.
     */
    public static final String PUSH_DATA_URI = "/iclock/cdata";

    /**
     * 设备推送指令执行结果URI.
     */
    public static final String PUSH_COMMAND_RETURN_URI = "/iclock/devicecmd";


	
	/****************************************** 语言 ****************************************************************/
    /**
     * 语言: 中文.
     */
    public static final String LANG_CN = "83";

    /**
     * 语言: 英文.
     */
    public static final String LANG_EN = "69";
    
  
    
    /******************************************  encoding ****************************************************************/

    /**
     * UTF-8 encoding.
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    /******************************************  配置文件位置参数 ****************************************************************/
    public static final String CFG_LOCATION_PARAM = "cfg-location";
    public static final String CLASSPATH_PREFIX = "classpath:";
    public static final String DEFAULT_CFG_LOCATION = "iclock.cfg.properties";
    
    
    
    /**
     * 一分钟的毫秒数.
     */
    public static final long MINUTE_MILLIS = 60 * 1000;

    public static final String DEV_INIT_OVER_ID_PREFIX = "INIT_OVER-";
    public static final String DEV_DIRTY_CHECK_ID_PREFIX = "DIRTY_CHECK-";
    public static final String DEV_DIRTY_CHECK_USER_ID_PREFIX = "DIRTY_CHECK_U-";
    public static final String DEV_DIRTY_CHECK_FINGER_ID_PREFIX = "DIRTY_CHECK_FP-";
    public static final String DEV_DIRTY_CHECK_PHOTO_ID_PREFIX = "DIRTY_CHECK_PT-";
    public static final String DEV_DIRTY_CHECK_FACE_ID_PREFIX = "DIRTY_CHECK_FE-";
    public static final String DEV_INIT_CLEAR_LOG_ID_PREFIX = "INIT_CAU-";
    public static final String DEV_INIT_CLEAR_PHOTO_ID_PREFIX = "INIT_CAU-";
    public static final String DEV_INIT_CLEAR_ALL_USER_ID_PREFIX = "INIT_CAU-";
    public static final String DEV_RELOAD_DATA_ID_PREFIX = "INIT_RD-";
    public static final String DEV_RELOAD_OPTIONS_ID_PREFIX = "INIT_RO-";
    
    
    

    public Charset charset = UTF_8;

    /**
     * 脏检查间隔, 单位分钟.
     * <p>
     * 用于检查设备删除对象, 如果该值 < 1 则表示禁用, 不检查设备删除对象.
     * </p>
     */
    public int dirtyCheckPeriod = 1;     // minutes

    /**
     * 新设备接入时, 是否清空新设备数据.
     */
    public boolean clearNewDevice = false;

    /**
     * 已接入过的设备, 是否支持离线更新.
     */
    public boolean offlineUpdate = true;

    /**
     * iclock 配置.
     */
    public Properties props;

    public Map<String, Boolean> devices = new LinkedHashMap<>();

    /**
     * 设备命令队列.
     */
    public final Map<String, List<String>> commandQueue = new LinkedHashMap<>();

    /**
     * 设备及表最后访问时间.
     */
    public final Map<String/*sn-table*/, String> devicesTableLastStamp = new LinkedHashMap<>();

    public final Map<String, Long> devicesLastDirtyTimestamp = new ConcurrentHashMap<>();

    /**
     * 设备数据服务器端缓存.
     */
    public final Map<String/*sn-table*/, Map<String, String>> deviceDataCache = new LinkedHashMap<>();
    
    
}
