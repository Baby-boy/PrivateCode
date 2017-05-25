package com.lingang.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于从同实体类中获取简单对象
 * 
 * @author cloud_service
 * 
 */
public class SimpleObjectGeneratorUtil {

	private static final Logger log = LoggerFactory
			.getLogger(SimpleObjectGeneratorUtil.class);

	/**
	 * 用源对象对目标对象填充数据 注意：(简单对象中所有属性必须在同实体类的存在)
	 * @param sourceObject
	 * @param targetObject
	 * @param excludTargetObjectFields 【字段的首字段大写，不然不能过滤】
	 */
	public static void fillSimpleObject(Object sourceObject,
			Object targetObject, Set<String> excludTargetObjectFields) {
		Method[] sourceMethods = sourceObject.getClass().getDeclaredMethods();
		List<Method> sourceMethodList = Arrays.asList(sourceMethods);
		Class<?> superClass= targetObject.getClass().getSuperclass();
		Method[] targetMethods=null;
		//如果目标对象父类不是OBJECT,   把父类和当前对象合并
		if (!superClass.getName().equals(Object.class.getName())) {
			Method[] targetParentMethods =null;
			Method[] targetChildMethods =null;
			targetParentMethods = targetObject.getClass().getSuperclass().getDeclaredMethods();
			targetChildMethods=targetObject.getClass().getDeclaredMethods();
			targetMethods = new Method[targetParentMethods.length + targetChildMethods.length];
			int j = 0;
			for (int i = 0; i < targetParentMethods.length; i++) {
				targetMethods[j++] =  targetParentMethods[i];
			}
			for (int i = 0; i < targetChildMethods.length; i++) {
				targetMethods[j++] =  targetChildMethods[i];
			}
		} else {
			targetMethods=targetObject.getClass().getDeclaredMethods();
		}
		
		List<Method> targetMethodList = Arrays.asList(targetMethods);
		Map<String,Method> sourceMethodMap = new HashMap<String,Method>();
		for (Method method : sourceMethodList) {
			if (method.getName().startsWith("get")) {
				sourceMethodMap.put(method.getName().substring(3), method);
			}
		}
		for (Method method : targetMethodList) {
			if (method.getName().startsWith("set")) {
				String fieldName = method.getName().substring(3);
				if ((excludTargetObjectFields == null || !excludTargetObjectFields.contains(fieldName))
						&& sourceMethodMap.containsKey(fieldName)) {
					try {
						Method sourceMethod = sourceMethodMap.get(fieldName);
						String targetMethodParameterType = method.getParameterTypes()[0].getName();
						String sourceMethodResultType = sourceMethod.getReturnType().getName();
						Object methodParameter  = sourceMethod.invoke(sourceObject);
						//解决类型不一致问题
						if(methodParameter == null || targetMethodParameterType.equalsIgnoreCase(sourceMethodResultType)){
							method.invoke(targetObject,methodParameter);
						}else if(targetMethodParameterType.equalsIgnoreCase(String.class.getName())){
							if(sourceMethodResultType.equalsIgnoreCase(Date.class.getName())){
								method.invoke(targetObject,DateTimeUtils.format((Date)methodParameter));
							}else{
								method.invoke(targetObject,methodParameter.toString());
							}
						}else{
							if(targetMethodParameterType.equalsIgnoreCase(Integer.class.getName())&&sourceMethodResultType.equalsIgnoreCase(Long.class.getName())){
								method.invoke(targetObject,((Long)methodParameter).intValue());
							}else if(targetMethodParameterType.equalsIgnoreCase(BigDecimal.class.getName())&&sourceMethodResultType.equalsIgnoreCase(String.class.getName())){
								BigDecimal targetValue = new BigDecimal(methodParameter.toString());
								method.invoke(targetObject,targetValue);
							}else if(targetMethodParameterType.equalsIgnoreCase(Byte.class.getName())&&sourceMethodResultType.equalsIgnoreCase(Boolean.class.getName())){
								Byte targetValue = (byte) (((Boolean)methodParameter).booleanValue()?1:0);
								method.invoke(targetObject,targetValue);
							}else{
								throw new RuntimeException("some field leaked!");
							}
						}
						
					} catch (Exception e) {
						log.error("leak-fieldName:"+fieldName);
						log.error("leak-type :" +method.getParameterTypes()[0].getName());
						log.error("leak-value type:" +sourceMethodMap.get(fieldName).getReturnType().getName());
						log.error("简单对象填充数据异常", e);
					}
				}
			}
		}
	}

}
