package com.glanway.ctrlhr.service.signGroup;

import java.util.List;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.para.SignGroupPara;
import com.glanway.ctrlhr.entity.signGroup.SignGroup;
import com.glanway.ctrlhr.entity.vo.SignGroupVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignGroupVo;
import com.glanway.ctrlhr.service.BaseService;

public interface SignGroupService extends BaseService<SignGroup> {

	/**
	 * 说明 : 查询考勤群组列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 上午10:32:33
	 */
	public Page<SignGroupVo> findList(BasePara para);

	/**
	 * 说明 :新增考勤群组
	 * 
	 * @param para
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午7:08:36
	 */
	public void save(SignGroupPara para);

	/**
	 * 说明 : 根据ID获取考勤群组信息
	 * 
	 * @param id
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:12:10
	 */
	public SignGroupVo getInfo(Long id);

	/**
	 * 说明 : 编辑考勤群组信息
	 * 
	 * @param para
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:28:04
	 */
	public void update(SignGroupPara para);

	/**
	 * 说明 : 根据ID删除考勤群组
	 * 
	 * @param ids
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午11:20:09
	 */
	public void delete(String ids);

	/**
	 * 说明 : 根据考勤组ID修改考勤组的状态(1:开启,2:关闭)
	 * 
	 * @param id
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午5:06:08
	 */
	public void updateState(Long id);

	/**
	 * 说明 : 查询此设备属于的考勤组(精简)
	 *
	 * @param sn
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月21日 下午2:03:48
	 */
	public List<SimpleSignGroupVo> findsimpleList(String sn);

}
