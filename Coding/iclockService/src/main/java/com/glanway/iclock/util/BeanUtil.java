package com.glanway.iclock.util;

import net.sf.json.JSONNull;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.util.Date;
import java.util.Map;

/**
 * @author tianxuan
 * @Time 2016/4/19
 */
public class BeanUtil {
    static{
        //注册日期类型转换期  仅仅支转换 yyyy-MM-dd 格式字符串
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if (null == value || value instanceof JSONNull) return null;
                if (!(value instanceof String)) {
                    return value instanceof Date ? value : null;
                }
                String val = (String) value;
                return TimeUtil.StrToDateLong(val);
            }
        }, Date.class);
    }
    /**
     * 用Map 填充bean
     */
    public static <T> T mapToBean(Class<T> beanClass, Map<String, Object> map) {
        try {
            T bean = beanClass.newInstance();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>名称： 用一个对象填充另一个对象</p>
     * <p>描述： 用一个对象填充另一个对象</p>
     * @author：tianxuan
     * @param beanClass
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T beanToBean(Class<T> beanClass, Object object) {
        try {
            T bean = beanClass.newInstance();
            BeanUtils.copyProperties(bean, object);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
