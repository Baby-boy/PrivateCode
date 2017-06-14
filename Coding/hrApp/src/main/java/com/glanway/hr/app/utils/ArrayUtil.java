package com.glanway.hr.app.utils;

import org.springframework.util.StringUtils;

public class ArrayUtil {
	
	public static String arrayToString(String[] args){
		StringBuffer result = new StringBuffer();
		for (int i=0; i<args.length; i++) {
			result.append(args[i]);
			if(i != args.length-1){
				result.append(",");
			}
		}
		return result.toString();
	}
	
	public static String[] stringToArray(String arg){
		if (!StringUtils.hasText(arg)){
			return new String[0];
		}
		String[] result = arg.split(",");
		return result;
	}

    public static boolean hasNotEmpty(String[] array) {
        if (null == array) {
            return false;
        }
        for (String s : array) {
            if (!StringUtils.isEmpty(s)) {
                return true;
            }
        }
        return false;
    }
}
