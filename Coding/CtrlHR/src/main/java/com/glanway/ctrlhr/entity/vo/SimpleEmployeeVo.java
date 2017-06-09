/*
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.ctrlhr.entity.vo;

/**
 * 员工简单列表封装
 * 
 * @author fuqihao
 * @version 1.0-20170526
 * @since 1.0-20170526
 */
public class SimpleEmployeeVo {

    private Long id;// 员工Id

    private String name;// 员工名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
