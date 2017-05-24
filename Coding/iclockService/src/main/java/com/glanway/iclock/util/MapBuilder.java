package com.glanway.iclock.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * <p>名称: MapBuilder</p>
 * <p>说明: java实体对象生成对应Map工具类</p>
 * <p>修改记录：（修改日期 - 修改人 - 修改内容）</p>  
 * 
 * @author：ChenGuang
 * @date：2017年3月2日下午2:41:28   
 * @version: 1.0
 */
public class MapBuilder {

	/**
	 * 处理父类从父类继承的ID
	 */
    private final static String ID_FIELD_NAME = "id";
    
    /**
     * 打印日志
     */
    private final static Logger LOG = LoggerFactory.getLogger(MapBuilder.class);


    /**
     * <p>名称：fromList</p> 
     * <p>描述：用实体列表创建Map列表</p>
     * @author：ChenGuang
     * @param sources
     * @param properties
     * @return
     */
    public static <E> List<Map<String, Object>> fromList(List<E> sources, String[] properties)
    {
        List<Map<String, Object>> resultList = Lists.newArrayList();
        if(sources == null || sources.size() == 0){
        	LOG.debug("---------MapBuilder---fromList-------------------list为null或长度为0");
            return resultList;
        }

        for(Object source:sources) {
            Map<String, Object> result = fromObject(source,properties);
            resultList.add(result);
        }

        return resultList;
    }

    /**
     * <p>名称：fromObject</p> 
     * <p>描述：实体对象创建指定字段的Map</p>
     * @author：ChenGuang
     * @param source
     * @param properties
     * @return
     */
    public static Map<String, Object> fromObject(Object source, String[] properties)
    {
        Map<String, Object> result = Maps.newHashMap();
        java.util.Set<Field> fieldSet = new HashSet<Field>();
        Class<?> clazz = source.getClass() ; 
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {
            	CollectionUtils.addAll(fieldSet, clazz.getDeclaredFields()); 
            } catch (Exception e) {  
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了   
            }   
        }
        for (Field field:fieldSet) {
            String fieldName = field.getName();
            if (checkFieldName(fieldName, properties)) {
                Object value = getFieldValueByName(fieldName, source);

                if (value instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dateValue = (Date) value;
                    value = sdf.format(dateValue);
                }

                result.put(field.getName(), value);
            }
        }

        if(!result.containsKey(ID_FIELD_NAME)){
            try {
                Object value = getFieldValueByName(ID_FIELD_NAME, source);
                result.put(ID_FIELD_NAME, value);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     * <p>名称：putFromObject</p> 
     * <p>描述：把source对象按properties中属性生成Map放入sourceMap中</p>
     * @author：ChenGuang
     * @param sourceMap
     * @param source
     * @param properties
     */
    public static void putFromObject(Map<String, Object> sourceMap, Object source, String[] properties)
    {
        sourceMap.putAll(fromObject(source,properties));
    }

    /**
     * <p>名称：checkFieldName</p> 
     * <p>描述：判断实体对象中是否有对应的字段</p>
     * @author：ChenGuang
     * @param sourceFieldname
     * @param properties
     * @return
     */
    private static boolean checkFieldName(String sourceFieldname, String[] properties) {
        for (String property:properties){
            if(property.equals(sourceFieldname)){
                return true;
            }
        }

        return false;
    }

    /**
     * <p>名称：getFieldValueByName</p> 
     * <p>描述：通过字段名获取对应值</p>
     * @author：ChenGuang
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o)
    {
        try
        {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e)
        {
        	LOG.debug("属性不存在");
            return null;
        }
    } 
}
