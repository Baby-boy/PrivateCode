package com.glanway.ctrlhr.service.sign;

import java.util.List;

import com.glanway.ctrlhr.entity.para.SignPara;
import com.glanway.ctrlhr.entity.sign.Sign;
import com.glanway.ctrlhr.entity.vo.SignDetailVo;
import com.glanway.ctrlhr.entity.vo.SignExportVo;
import com.glanway.ctrlhr.service.BaseService;

public interface SignService extends BaseService<Sign> {

    /**
     * 说明 : 查询考勤记录表数据
     * 
     * @param para
     * @return
     * @author 付其浩
     * @dateTime 2017年4月24日 下午1:02:34
     */
    public List<SignDetailVo> findMany(SignPara para);

    /**
     * 导出考勤记录数据(格式变化).
     *
     * @author fuqihao
     * @param para
     * @return
     * @since 1.0-20170512
     */
    public List<SignExportVo> findSignExportList(SignPara para);

    /**
     * 根据条件查询对应考勤的员工
     *
     * @author fuqihao
     * @param para
     * @return
     * @since 1.0-20170523
     */
    public List<SignExportVo> findEmployeeList(SignPara para);

}
