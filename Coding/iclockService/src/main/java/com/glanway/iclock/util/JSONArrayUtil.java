/**
 * Copyright (c) 2016, www.glanway.com Inc. All rights reserved.
 * <p/>
 * ProjectName: hg
 * FileName: JSONArrayUtil.java
 * PackageName: com.glanway.hg.utils
 * Date: 2016/5/910:04
 **/
package com.glanway.iclock.util;

import net.sf.json.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>名称: </p>
 * <p>说明: 操作Json的工具类</p>
 * <p>修改记录：（修改日期 - 修改人 - 修改内容）</p>  
 *
 * @author：tianxuan
 * @date：2016/5/910:04
 * @version: 1.0
 */
public class JSONArrayUtil {

    /**
     * <p>名称：生成json</p>
     * <p>描述：转为json</p>
     * @author：tianxuan
     * @param object
     * @return
     */
    public static String stringify(Object object){
        String rtStr = null;
        if(null != object){
           rtStr = JSONArray.fromObject(object).toString();
        }
        return rtStr;
    }

    /**
     * <p>名称：解析json</p>
     * <p>描述：将josn转化为 List</p>
     * @author：tianxuan
     * @param json
     * @param tClass
     * @return  List<T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static <T> List<T> parse(String json, Class<T> tClass)  {
        List<T> rtList = new ArrayList<>();
        if(null != json && !json.isEmpty()){
            JSONArray array = JSONArray.fromObject(json);
            for(Object obj : array){
                T bean = BeanUtil.beanToBean(tClass,obj);
                rtList.add(bean);
            }
        }
        return rtList;
    }
}
