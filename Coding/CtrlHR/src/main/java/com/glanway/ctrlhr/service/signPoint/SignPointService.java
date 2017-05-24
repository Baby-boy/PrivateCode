package com.glanway.ctrlhr.service.signPoint;

import java.util.List;

import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.para.SignPointPara;
import com.glanway.ctrlhr.entity.signPoint.SignPoint;
import com.glanway.ctrlhr.entity.vo.SignPointVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignPointVo;
import com.glanway.ctrlhr.service.BaseService;

public interface SignPointService extends BaseService<SignPoint> {

	/**
	 * 说明 : 查询考勤点列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午1:48:11
	 */
	public Page<SignPoint> findList(SignPointPara para);

	/**
	 * 说明 : 新增考勤点
	 * 
	 * @param para
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午3:05:42
	 */
	public void save(SignPointPara para);

	/**
	 * 说明 : 根据ID获取考勤点的信息
	 * 
	 * @param id
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午5:35:49
	 */
	public SignPointVo getInfo(Long id);

	/**
	 * 说明 : 更新考勤点信息
	 * 
	 * @param para
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午5:47:04
	 */
	public void update(SignPointPara para);

	/**
	 * 说明 : 删除考勤点信息
	 * 
	 * @param ids
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午6:07:21
	 */
	public JsonResult delete(String ids);

	/**
	 * 说明 : 查询考勤点(精简)列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 上午9:41:31
	 */
	public List<SimpleSignPointVo> findSimpleList(KeywordPara para);

}
