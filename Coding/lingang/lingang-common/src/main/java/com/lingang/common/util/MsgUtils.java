package com.lingang.common.util;
/**
 * Msg工具类
 * @author LiRui
 */
public class MsgUtils {
	//用户
	public static MsgPropertyUtil user = new MsgPropertyUtil("userMsg.properties");
	/**
	* @Fields shop : 店铺消息
	*/
	//public static MsgPropertyUtil shop = new MsgPropertyUtil("shopMsg.properties");
	
	//private static final String SHOP_MSG_START_CHAR = "S";
	private static final String USER_MSG_START_CHAR = "U";
	
	
	public static String getMsg(String key, Object... params) {
		String value = "";
		if (key == null || key.trim().length() == 0) {
			return value;
		}
	/*	if (key.startsWith(SHOP_MSG_START_CHAR)) {
			value = shop.getMsg(key, params);
		} else */
			if (key.startsWith(USER_MSG_START_CHAR)) {
			value = user.getMsg(key, params);
		} else {
			value = key;
		}
		
		return value;
	}
}
