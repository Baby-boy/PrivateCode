/**
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.hr.app.service;

import com.glanway.hr.app.common.Page;
import com.glanway.hr.app.entity.Templet;
import com.glanway.hr.app.entity.para.TempletPara;
import com.glanway.hr.app.entity.vo.TempletVo;

/**
 * Service层代码模板.
 *
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
public interface TempletService extends BaseService<Templet> {

    /**
     * 查询模板列表(注释仅注释接口,实现类不进行注释). 
     *
     * @param para
     * @return 
     * @author fuqihao
     * @since 2017年6月9日
     */
    public Page<TempletVo> findList(TempletPara para);

}
