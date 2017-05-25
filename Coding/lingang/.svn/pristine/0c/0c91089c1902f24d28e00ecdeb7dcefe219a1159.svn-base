package com.lingang.api.domain.basic;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于从同实体类中获取简单对象
 * 注意：(简单对象中所有属性必须在同实体类的存在)
 * @author Tommy
 *
 * @param <T>
 */
public abstract class SimpleObjectGenerator<T> {
	private static final Logger logger = LoggerFactory.getLogger(SimpleObjectGenerator.class);
	
	/**
	 * 简单对象填充数据-->对本身对象填充数据
	 * 注意：(简单对象中所有属性必须在同实体类的存在)
	 * @param object
	 */
	public void fillSimpleObject(T object){
	    Method[] methods = this.getClass().getDeclaredMethods();
	    List<Method> methodList = Arrays.asList(methods);
	    for(Method method : methodList){
	    	if(method.getName().startsWith("set")){
	    		try {
	    			method.invoke(this, object.getClass().getMethod(method.getName().replaceFirst("set", "get"),method.getParameterTypes()).invoke(object));
				} catch (Exception e) {
					logger.error("简单对象填充数据异常", e);
				}
	    	}
	    }
	}
}
