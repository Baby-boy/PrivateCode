/**
 * @author zhangshuang
 * 2017年4月17日 上午11:11:23
 */
package com.glanway.iclock.common;

import java.nio.charset.Charset;

/**
 * 说明 : 
 * 	考勤机命令常量-封装类
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月17日 上午11:11:23
 */
public class CommandWrapper {
	  
	/**
	 * UTF-8 encoding.
	 */
	public static final Charset GB2312 = Charset.forName("GB2312");
	/**
	 * UTF-8 encoding.
	 */
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	
    /****************************************** 命令 ****************************************************************/
    /**
     * 3.2.4-1 执行系统命令.
     * <p>
     * 返回格式:<br>
     * ID=11<br>
     * SN=xx<br>
     * Return=XX<br>
     * CMD=Shell<br>
     * FILENAME=shellout.txt<br>
     * Content=SSS
     * </p>
     */
    public static final String CMD_SHELL = "C:%s:SHELL %s";

    /**
     * 3.2.4-2 检查数据更新.
     * <p>
     * 要求设备从服务器读取设备的配置信息,然后检查设备内数据的更新情况,若有新的数据(考勤/用户信息)，立即传送到服务器.<br>
     * POST格式: ID=?&SN=?&Return=?&CMD=CHECK
     * </p>
     */
    public static final String CMD_CHECK = "C:%s:CHECK";

    /**
     * 3.2.4-3 清除数据 - 清除考勤记录.
     * <p>POST格式:ID=?&Return=?</p>
     */
    public static final String CMD_CLEAR_LOG = "C:%s:CLEAR LOG";

    /**
     * 3.2.4-3 清除数据 - 清除所有用户信息.
     */
    public static final String CMD_CLEAR_ALL_USERINFO = "C:%s:CLEAR ALL USERINFO";

    /**
     * 3.2.4-3 清除数据 - 清除所有数据.
     * <p>POST格式:ID=?&Return=?</p>
     */
    public static final String CMD_CLEAR_DATA = "C:%s:CLEAR DATA";

    /**
     * 3.2.4-3 清除数据 - 清除现场照片数据.
     * <p>POST格式:ID=?&Return=?</p>
     */
    public static final String CMD_CLEAR_PHOTO = "C:%s:CLEAR PHOTO";

    /**
     * 3.2.4-4 发送设备信息到服务器.
     * <p>
     * POST格式:<br>
     * ID=?&Return=?&CMD=INFO<br>
     * OPTIONS(每行为Item=Value)
     * </p>
     */
    public static final String CMD_INFO = "C:%s:INFO";

    /**
     * 3.2.4-5 设置设备选项.
     */
    public static final String CMD_SET_OPTION = "C:%s:SET OPTION %s=%s";

    /**
     * 3.2.4-6 重启设备.
     */
    public static final String CMD_REBOOT = "C:%s:REBOOT";

    /**
     * 3.2.4-7 数据命令.
     * <p>
     * 数据命令格式: DATA {subcmd}<br>
     * subcmd格式: UPDATE|DELETE|QUERY tablename value|key
     * </p>
     */
    public static final String CMD_DATA = "C:%s:DATA %s";

    /**
     * 3.2.4-8 重新加载系统选项.
     * <p>
     * 要求设备重新载入系统配置和选项，这样修改后的系统选项才能生效.<br>
     * POST格式: ID=iiii&SN=xxxx&Return=0
     * </p>
     */
    public static final String CMD_RELOAD_OPTIONS = "C:%s:RELOAD OPTIONS";

    /**
     * 3.2.4-9 登记用户指纹.
     */
    public static final String CMD_ENROLL_FINGER = "C:%s:ENROLL_FP %s";

    /**
     * 3.2.4-10 检查并传送考勤新数据.
     * <p>
     * 要求设备立即检查是否有新的考勤数据，并立即把新数据传送到服务器.
     * </p>
     */
    public static final String CMD_LOG = "C:%s:LOG";

    /**
     * 3.2.4-11 打开门锁信号.
     */
    public static final String CMD_UNLOCK = "C:%s:AC_UNLOCK";

    /**
     * 3.2.5.3 考勤对账.
     * Start=yyyy-MM-hh[ HH:mm:ss] 如果没有传递HH:mm:ss则为 00:00:00
     * End=yyyy-MM-hh[ HH:mm:ss] 如果没有传递HH:mm:ss则为 23:59:59
     * Count: 对账记录数, 如果实际考勤记录没有达到Count指定值, 则重新传递该时间内所有考勤记录.
     */
    public static final String CMD_ACCOUNT = "C%s:ACCOUNT Start=%s End=%s Count=%s";

    /**
     * 3.2.5.4 设备操作数据重传.
     */
    public static final String CMD_SYNC = "C:%s:SYNC Start=%s End=%s";

    /**
     * 3.2.4-12 取消报警信号.
     */
    public static final String CMD_UNALARM = "C:%s:AC_UNALARM";

    public static final String CMD_RELOAD_DATA = "C:%s:RELOAD DATA";
    
    public static final String CMD_DATA_QUERY_ATTLOG = "C:%s:DATA QUERY ATTLOG %s";
    public static final String CMD_DATA_QUERY_USER_ALL = "C:%s:DATA QUERY USERINFO";
    public static final String CMD_DATA_QUERY_FINGER_ALL = "C:%s:DATA QUERY FINGERTMP";
    public static final String CMD_DATA_QUERY_PHOTO_ALL = "C:%s:DATA QUERY USERPIC";
    public static final String CMD_DATA_QUERY_FACE_ALL = "C:%s:DATA QUERY FACE";

    public static final String CMD_DATA_UPDATE_USER = "C:%s:DATA UPDATE USERINFO %s";
    public static final String CMD_DATA_UPDATE_FINGER = "C:%s:DATA UPDATE FINGERTMP %s";
    public static final String CMD_DATA_UPDATE_PHOTO = "C:%s:DATA UPDATE USERPIC %s";
    public static final String CMD_DATA_UPDATE_FACE = "C:%s:DATA UPDATE FACE %s";

    public static final String CMD_DATA_DELETE_USER = "C:%s:DATA DELETE USERINFO %s";
    public static final String CMD_DATA_DELETE_FINGER = "C:%s:DATA DELETE FINGERTMP %s";
    public static final String CMD_DATA_DELETE_PHOTO = "C:%s:DATA DELETE USERPIC %s";
    public static final String CMD_DATA_DELETE_FACE = "C:%s:DATA DELETE FACE %s";

    
    
    
}
