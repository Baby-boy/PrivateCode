/*
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.hr.app.utils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.ponly.common.util.StringUtils;

/**
 * 毫秒时间戳转日期属性编辑器.
 * 
 * @author fuqihao
 * @version 1.0-20170522
 * @since 1.0-20170522
 */
public class TimestampDateEditor extends PropertyEditorSupport {
    /**
     * 是否允许空.
     */
    private boolean allowEmpty;

    /**
     * 构建一个默认允许为空的属性编辑器.
     *
     * @author fuqihao
     * @since  1.0-20170522
     */
    public TimestampDateEditor() {
        this(true);
    }

    /**
     * 构建一个属性编辑器, 根据给定参数确定是否允许为空.
     *
     * @author fuqihao
     * @param allowEmpty
     * @since  1.0-20170522
     */
    public TimestampDateEditor(final boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    /**
     * {@inheritDoc}
     * 
     * @author fuqihao
     * @see java.beans.PropertyEditorSupport#getAsText()
     * @since  1.0-20170522
     */
    @Override
    public String getAsText() {
        final Object value = getValue();
        if (null == value) {
            return null;
        } else if (value instanceof Date){
            return String.valueOf(((Date) value).getTime());
        } else {
            throw new IllegalStateException("value is not a date: " + value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @author fuqihao
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     * @since  1.0-20170522
     */
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            try { 
                final long timestamp = Long.parseLong(text);
                setValue(new Date(timestamp));
            } catch (final NumberFormatException nfe) {
                throw new IllegalArgumentException(nfe);
            }
        } else if (allowEmpty) {
            setValue(null);
        } else {
            throw new IllegalArgumentException("not allow empty at here");
        }
    }
}
