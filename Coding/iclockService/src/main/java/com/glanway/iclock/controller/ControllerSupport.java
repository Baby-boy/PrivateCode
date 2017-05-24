package com.glanway.iclock.controller;

import org.ponly.webbase.entity.Persistable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author vacoor
 * @deprecated
 * @see com.glanway.eshop.admin.controller.AdminBaseController
 */
@Deprecated
public abstract class ControllerSupport {
    public static final String SUCCESS_MSG_KEY = "message";
    public static final String ERROR_MSG_KEY = "error";

    private String viewPrefix;

    protected ControllerSupport() {
        setViewPrefix(defaultViewPrefix());
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    public void setViewPrefix(String viewPrefix) {
        // 去除开头的 "/"
        viewPrefix = viewPrefix.startsWith("/") ? viewPrefix.substring(1) : viewPrefix;
        this.viewPrefix = !viewPrefix.endsWith("/") ? viewPrefix : viewPrefix.substring(0, viewPrefix.length() - 1);
    }

    protected String getViewPath(String path) {
        String prefix = getViewPrefix();

        if (!StringUtils.hasText(path)) {
            return prefix;
        }

        if (!StringUtils.hasLength(prefix)) {
            return (!"".equals(path) && path.startsWith("/") ? path.substring(1) : path);
        } else {
            return prefix + (path.startsWith("/") ? path : "/" + path);
        }
    }

    protected String redirectViewPath(String path) {
    	String uri = getViewPath(path);
    	if (!uri.startsWith("http:") && !uri.startsWith("https:")) {
    		uri = uri.startsWith("/") ? uri : "/" + uri;
    	}
        return "redirect:" + uri;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }
        return currentViewPrefix;
    }
    private static final String DEFAULT_STRING_BEAN_PROPERTY = "id";
    private static final String[] DEFAULT_IGNORE_VALUES = new String[]{"", null};

    protected <Bean> Bean toBean(String value, Class<Bean> beanType) {
        return toBean(value, beanType, DEFAULT_STRING_BEAN_PROPERTY);
    }

    protected <Bean> Bean toBean(String value, Class<Bean> beanType, String property) {
        return toBean(value, beanType, property, null);
    }

    @SuppressWarnings("unchecked")
    protected <Bean> Bean toBean(String value, Class<Bean> beanType, String property, String[] ignoreValues) {
        PropertyEditor editor = new CustomStringBeanPropertyEditor(beanType, property, ignoreValues);
        editor.setAsText(value);
        return (Bean) editor.getValue();
    }

    protected <Sequence extends Collection<Element>, Element> Sequence toBeans(
            String value,
            Class<Sequence> collectionType,
            Class<Element> elementType) {
        return toBeans(value, collectionType, elementType, DEFAULT_STRING_BEAN_PROPERTY);
    }

    protected <Sequence extends Collection<Element>, Element> Sequence toBeans(
            String value,
            Class<Sequence> collectionType,
            Class<Element> elementType,
            String property) {
        return toBeans(value, collectionType, elementType, property, null);
    }

    @SuppressWarnings("unchecked")
    protected <Sequence extends Collection<Element>, Element> Sequence toBeans(
            String value,
            Class<Sequence> collectionType,
            Class<Element> elementType,
            String property,
            String[] ignoreValues) {
        PropertyEditor editor = new CustomStringCollectionBeanCollectionPropertyEditor(collectionType, elementType, property, ignoreValues);
        editor.setAsText(value);
        return (Sequence) editor.getValue();
    }

    protected <Sequence extends Collection<Element>, Element> Sequence toBeans(
            String[] values,
            Class<Sequence> collectionType,
            Class<Element> elementType) {
        return toBeans(values, collectionType, elementType, DEFAULT_STRING_BEAN_PROPERTY);
    }

    protected <Sequence extends Collection<Element>, Element> Sequence toBeans(
            String[] values,
            Class<Sequence> collectionType,
            Class<Element> elementType,
            String property) {
        return toBeans(values, collectionType, elementType, property, null);
    }

    @SuppressWarnings("unchecked")
    protected <Sequence extends Collection<Element>, Element> Sequence toBeans(
            String[] values,
            Class<Sequence> collectionType,
            Class<Element> elementType,
            String property,
            String[] ignoreValues) {
        PropertyEditor editor = new CustomStringCollectionBeanCollectionPropertyEditor(collectionType, elementType, property, ignoreValues);
        editor.setValue(values);
        return (Sequence) editor.getValue();
    }

    /**
     * Collection&lt;String&gt; --> Collection&lt;Element&gt;, Collection&lt;Element&gt; --> Collection&lt;String&gt;
     */
    protected static class CustomStringCollectionBeanCollectionPropertyEditor extends CustomCollectionEditor {
        private final CustomStringBeanPropertyEditor propertyEditor;


        public CustomStringCollectionBeanCollectionPropertyEditor(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionType, Class<?> beanType) {
            this(collectionType, beanType, null, null);
        }

        public CustomStringCollectionBeanCollectionPropertyEditor(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionType, Class<?> beanType, String property, String[] skipValues) {
            super(collectionType);
            Assert.notNull(beanType);
            Assert.notNull(property);
            propertyEditor = new CustomStringBeanPropertyEditor(beanType, property, skipValues);
        }

        public CustomStringCollectionBeanCollectionPropertyEditor(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionType, boolean nullAsEmptyCollection, Class<?> beanType, String property, String[] skipValues) {
            super(collectionType, nullAsEmptyCollection);
            Assert.notNull(beanType);
            Assert.notNull(property);
            propertyEditor = new CustomStringBeanPropertyEditor(beanType, property, skipValues);
        }

        @Override
        public void setValue(Object value) {
            if (null != value && value instanceof String) {
                value = ((String) value).split(",\\s*");
            }
            super.setValue(value);
        }

        @Override
        protected Object convertElement(Object element) {
            propertyEditor.setValue(element);
            return propertyEditor.getValue();
        }

        @Override
        public String getAsText() {
            Object value = getValue();
            if (null == value) {
                return null;
            } else if (Collection.class.isInstance(value)) {
                StringBuilder buff = new StringBuilder();
                Collection<?> coll = Collection.class.cast(value);

                int i = 0;
                for (Object element : coll) {
                    if (null == element) {
                        continue;
                    }
                    if (0 < i) {
                        buff.append(",");
                    }
                    propertyEditor.setValue(element);
                    element = propertyEditor.getAsText();

                    if (null != element) {
                        buff.append(element);
                    }
                    i++;
                }
                return buff.toString();
            }
            return super.getAsText();
        }
    }

    /**
     * String --> Bean, Bean --> String
     */
    protected static class CustomStringBeanPropertyEditor extends PropertyEditorSupport {
        private final Class<?> clazz;
        private final String property;
        private final List<String> skipValues;

        public CustomStringBeanPropertyEditor(Class<?> clazz) {
            this(clazz, null);
        }

        public CustomStringBeanPropertyEditor(Class<?> clazz, String[] skipValues) {
            this(clazz, DEFAULT_STRING_BEAN_PROPERTY, skipValues);
        }

        public CustomStringBeanPropertyEditor(Class<?> clazz, String property, String[] skipValues) {
            Assert.notNull(clazz);
            Assert.notNull(property);
            this.clazz = clazz;
            this.property = property;
            this.skipValues = Arrays.asList(null != skipValues ? skipValues : DEFAULT_IGNORE_VALUES);
        }


        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            // super.setAsText(text);
            setValue(text);
        }

        @Override
        public void setValue(Object value) throws IllegalArgumentException {
            String text;
            if ((value instanceof String) && StringUtils.hasText(text = (String) value) && !skipValues.contains(text)) {
                Object bean = BeanUtils.instantiate(clazz);
                Class<?> type = BeanUtils.getPropertyDescriptor(clazz, property).getPropertyType();

                BeanMap beanMap = BeanMap.create(bean);
                beanMap.put(property, tryConvert(text, type));
                value = beanMap.getBean();
            } else if ((value instanceof String) && !StringUtils.hasText((String) value)) {
                value = null;
            }

            super.setValue(value);
        }

        @Override
        public String getAsText() {
            Object value = getValue();
            if (null == value) {
                return null;
            } else if (clazz.isInstance(value)) {
                value = BeanMap.create(value).get(property);
                return null != value ? value.toString() : null;
            }
            return super.getAsText();
        }
    }

    /**
     * 尝试使用 Spring ConversionService 进行类型转换
     * 如果不能转换返回原始值
     *
     * @param value  需要转换的值
     * @param target 目标类型
     * @return 转换后的值或原始值
     */
    protected static Object tryConvert(Object value, Class<?> target) {
        ConversionService cs = null;
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (null != attrs) {
            cs = (ConversionService) attrs.getAttribute("org.springframework.core.convert.ConversionService", RequestAttributes.SCOPE_REQUEST);
        }
        if (null != value && null != cs && cs.canConvert(value.getClass(), target)) {
            value = cs.convert(value, target);
        }
        return value;
    }

    public static void main(String[] args) {

    }
}
