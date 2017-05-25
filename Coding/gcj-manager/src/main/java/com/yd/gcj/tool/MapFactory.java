package com.yd.gcj.tool;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapFactory {
	public static Object toObject(Map<String, Object> map,Class c){
		
		Object obj = null;
		try {
			
			obj = c.newInstance();
			Field[] fields = c.getDeclaredFields();
			
			for(Field f : fields) {
				
			   Object object = map.get(f.getName());
			   if(object!=null){
				   /*System.out.println(f.getName());*/
				   PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
				   	//调用set方法
					Method method = pd.getWriteMethod();
					method.invoke(obj, object);
					
					//获得get方法
					/*Method get = pd.getReadMethod();
					Object getValue = get.invoke(obj, new Object[]{});
					System.out.println("field:"+f.getName()+"---getValue:"+getValue);*/
			   }
			   
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		return obj;
		
	}
	
	/***
	 * 过滤map参数
	 * @param map 原map
	 * @param keys 需要删除的元素key数组
	 * @return
	 */
	public static HashMap<String, Object> removeByKeys(HashMap<String,Object> map,String... keys){
		if(keys.length>0&&map!=null){
			for(String key:keys){
				if(map.get(key)!=null){
					map.remove(key);
				}
			}
		}
		return map;
	}
}
