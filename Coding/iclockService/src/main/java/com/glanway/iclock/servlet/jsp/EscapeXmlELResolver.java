package com.glanway.iclock.servlet.jsp;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.servlet.jsp.JspContext;
import java.beans.FeatureDescriptor;
import java.util.Iterator;

/**
 * {@link javax.el.ELResolver} which escapes XML in String values.
 * 修改自 https://github.com/pukkaone/webappenhance
 * 修改不转义从
 * <enhance:out escapeXml="false">
 * I hope this expression returns safe HTML: ${member.name}
 * </enhance:out>
 * 为
 * ${member.name$$}
 * 注: 该类仅能起到一定作用 (仅对类型为 String 的进行处理, 如果通过toString() 输出则不会进行转义,eg: list&lt;String&gt;)
 * {@see http://pukkaone.github.io/2011/01/03/jsp-cross-site-scripting-elresolver.html}
 *
 * @author vacoor
 */
public class EscapeXmlELResolver extends ELResolver {
    public static final String ESCAPE_XML_ATTRIBUTE = EscapeXmlELResolver.class.getName() + ".escape";
    public static final String UNESCAPE_SUFFIX = "$$";

    private ThreadLocal<Boolean> excludeMe = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return null;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        JspContext pageContext = (JspContext) context.getContext(JspContext.class);
        Boolean escape = (Boolean) pageContext.getAttribute(ESCAPE_XML_ATTRIBUTE);

        if (null != escape && !escape) {
            return null;
        }

        try {
            if (excludeMe.get()) {
                return null;
            }

            // This resolver is in the original resolver chain. To prevent
            // infinite recursion, set a flag to prevent this resolver from
            // invoking the original resolver chain again when its turn in the
            // chain comes around.
            excludeMe.set(Boolean.TRUE);

            escape = null == escape || escape;
            // 没有设置转义, 尝试根据后缀判断
            if (escape && property instanceof String) {
                String prop = (String) property;
                if (prop.endsWith(UNESCAPE_SUFFIX)) {
                    escape = false;
                    property = prop.substring(0, prop.length() - UNESCAPE_SUFFIX.length());
                }
            }

            Object value = context.getELResolver().getValue(context, base, property);

            if (escape && value instanceof String) {
                value = EscapeXml.escape((String) value);
            }
            return value;

        } finally {
            excludeMe.remove();
        }
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        return true;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
    }
}