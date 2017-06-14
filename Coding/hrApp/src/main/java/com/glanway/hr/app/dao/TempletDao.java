/**
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.hr.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.hr.app.entity.Templet;
import com.glanway.hr.app.entity.para.TempletPara;
import com.glanway.hr.app.entity.vo.TempletVo;

/**
 * Dao层代码模板.
 * 
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
public interface TempletDao extends BaseDao<Templet> {

    /** 查询模板列表总数 */
    public int findListCount(@Param("para") TempletPara para);

    /** 查询模板列表 */
    public List<TempletVo> findList(@Param("para") TempletPara para);

}
