package com.glanway.ctrlhr.dao.employee;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.employee.FingerFaceTemplate;

/**
 * 员工指纹模板和脸纹模板
 * 
 * @author fuqihao
 * @version 1.0-20170531
 * @since 1.0-20170531
 */
public interface FingerFaceTemplateDao extends BaseDao<FingerFaceTemplate> {

    /** 删除指纹模板和脸纹模板 */
    public void delete(@Param("codeArr") String[] codeArr);

}
