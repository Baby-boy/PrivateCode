package com.lingang.common.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.lingang.common.constants.CommonConstants;



/**
 * 属性工具类
 * @author Tommy
 *
 */
public class PropertyUtils extends PropertyPlaceholderConfigurer{

	private static Map<String, String> ctxPropertiesMap;  
	  
    @Override  
    protected void processProperties(  
            ConfigurableListableBeanFactory beanFactoryToProcess,  
            Properties props) throws BeansException {  
        super.processProperties(beanFactoryToProcess, props);  
        ctxPropertiesMap = new HashMap<String, String>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
    }  
  
    public static String getString(String key) { 
    	if(ctxPropertiesMap == null) {
    		ctxPropertiesMap = new HashMap<String, String>(); 
    	}
        return ctxPropertiesMap.get(key);  
    }
    
    /**
     * 根据键值获取Property文件中的值
     * @author 吴鹏
     * @param key 键值
     * @param defaultValue 默认值
     * @return 根据键值取得的结构
     */
    public static String getString(String key, String defaultValue){
    	if(ctxPropertiesMap == null) {
    		ctxPropertiesMap = new HashMap<String, String>(); 
    	}
    	
    	String value = ctxPropertiesMap.get(key);
    	
        return (value == null) ? defaultValue : value ;  
    }
    
    public static int getIntValue(String key, int defaultValue) {
    	try {
    		int r = Integer.parseInt(getString(key));
    		return r;
    	} catch (Exception e) {
    		return defaultValue;
    	}
    }
    
    /**
     * 取出复合表达式的keys
     * @param express
     * @return
     */
    public static Set<String> getkeys(String express){
    	Set<String> selectedkey = new HashSet<String>();
    	Set<String> keys =  ctxPropertiesMap.keySet();
    	for(String item : keys){
    		if(item.matches(express)){
    			selectedkey.add(item);
    		}
    	}
    	return selectedkey;
    }
    
    
    public static boolean isDebug(){
    	String debug = PropertyUtils.getString(CommonConstants.DEBUG);
    	if( debug != null && debug.equalsIgnoreCase("true"))
    		return true;
    	return false;
    }
}

