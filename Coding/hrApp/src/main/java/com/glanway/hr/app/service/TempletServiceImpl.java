/**
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.hr.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.hr.app.common.Page;
import com.glanway.hr.app.dao.TempletDao;
import com.glanway.hr.app.entity.Templet;
import com.glanway.hr.app.entity.para.TempletPara;
import com.glanway.hr.app.entity.vo.TempletVo;

/**
 * Service层实现类模板
 * 
 * @author fuqihao
 * @version 1.0
 * @since 2017年6月9日
 */
@Transactional
@Service("templetService")
public class TempletServiceImpl extends BaseServiceImpl<Templet>implements TempletService {

    @Autowired
    private TempletDao templetDao;

    @Override
    public Page<TempletVo> findList(TempletPara para) {
        int count = templetDao.findListCount(para);
        Page<TempletVo> page = new Page<>(para.getPage(), count, para.getPageSize());
        if (para.getPage() <= page.getCountPage()) {
            para.setStartIndex(page.getStartIndex());
            List<TempletVo> list = templetDao.findList(para);
            page.setList(list);
        }

        return page;
    }

}
