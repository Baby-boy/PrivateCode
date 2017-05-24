package com.glanway.ctrlhr.service.sign;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.MonthlySignPara;
import com.glanway.ctrlhr.entity.sign.MonthlySign;
import com.glanway.ctrlhr.entity.vo.MonthlySignVo;
import com.glanway.ctrlhr.service.BaseService;

@Transactional
public interface MonthlySignService extends BaseService<MonthlySign> {

	/**
	 * 说明 : 查询月考勤记录列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月18日 下午5:37:10
	 */
	public Page<MonthlySignVo> findList(MonthlySignPara para);

	/**
	 * 说明 : 获取需要导出的月考勤记录列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 上午10:04:09
	 */
	public List<MonthlySignVo> findMany(MonthlySignPara para);

}
