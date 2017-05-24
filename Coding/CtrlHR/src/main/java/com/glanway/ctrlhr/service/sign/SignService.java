package com.glanway.ctrlhr.service.sign;

import java.util.List;

import com.glanway.ctrlhr.entity.para.SignPara;
import com.glanway.ctrlhr.entity.sign.Sign;
import com.glanway.ctrlhr.entity.vo.SignVo;
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
	public List<SignVo> findMany(SignPara para);

}
