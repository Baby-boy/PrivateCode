/**
 ***********************************************
 * function: 常量类
 * date: 2014年8月7日 下午03:00:30 <br/>
 * @author huahaitao
 * @version 1.2
 * Log:delete useless Code modify by chenguang 20160127
 *************************************************
 **/
package com.glanway.iclock.common;

public interface Constant {

	/***************************************
	 * COMMON 通用
	 ***********************************************/

	
	
	/**
	 * @Fields DEFAULT_HEAD_IMAGE : head图标
	 */
	String DEFAULT_HEAD_IMAGE = "storage/images/head.png";
	
	/**
	 * 删除
	 */
	boolean DELETED = Boolean.TRUE;

	/**
	 * 未删除
	 */
	boolean NOT_DELETED = Boolean.FALSE;
	/**
	 * 激活
	 */
	boolean ACTIVE = Boolean.TRUE;

	/**
	 * 未激活
	 */
	boolean NOT_ACTIVE = Boolean.FALSE;

	

	/****************************************** 表名 ****************************************************************/
	 /**
     * 3.2.2 考勤记录表名.
     */
	public static final String TAB_ATTLOG = "ATTLOG";

    /**
     * 3.2.2 操作日志表名.
     */
    public static final String TAB_OPERLOG = "OPERLOG";

    /**
     * 3.2.2 考勤照片表名.
     */
    public static final String TAB_ATTPHOTO = "ATTPHOTO";

    /**
     * 3.2.2 短信息.
     */
    public static final String TAB_SMS = "SMS";

    /**
     * 3.2.2 个人短信息.
     */
    public static final String TAB_USER_SMS = "USER_SMS";

    /**
     * 3.2.2 用户信息.
     */
    public static final String TAB_USERINFO = "USERINFO";

    /**
     * 3.2.2 指纹模板表名.
     */
    public static final String TAB_FINGERTMP = "FINGERTMP";

    /**
     * 3.2.2 人脸模板表名.
     */
    public static final String TAB_FACE = "FACE";

    /**
     * 3.2.2 用户照片表名.
     */
    public static final String TAB_USERPIC = "USERPIC";
    
	
 
    /****************************************** 用户类型 ****************************************************************/
    

	/**
	 * 普通用户
	 */
	public final String USER_TYPE_NORMAL = "1";

	/**
	 * 商家用户
	 */
	public final String USER_TYPE_MERCHANT = "2";

	//后台路径统一路径
//	String ADMIN_PREFIX = "admin";
	String ADMIN_PREFIX = "";
}
