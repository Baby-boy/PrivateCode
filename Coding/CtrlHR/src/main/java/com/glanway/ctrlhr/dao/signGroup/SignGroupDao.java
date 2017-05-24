package com.glanway.ctrlhr.dao.signGroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.signGroup.SignGroup;
import com.glanway.ctrlhr.entity.vo.SignGroupVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignGroupVo;

public interface SignGroupDao extends BaseDao<SignGroup> {

	public int deleteByPrimaryKey(Long id);

	public int insert(SignGroup record);

	public int insertSelective(SignGroup record);

	public SignGroup selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SignGroup record);

	public int updateByPrimaryKey(SignGroup record);

	/** 查询考勤群组列表总数 */
	public int findListCount(@Param("para") BasePara para);

	/** 查询考勤群组列表 */
	public List<SignGroupVo> findList(@Param("para") BasePara para);

	/** 根据ID获取考勤群组信息 */
	public SignGroupVo getInfo(@Param("id") Long id);

	/** 批量删除考勤群组 */
	public void deleteBatch(@Param("idArr") String[] idArr);

	/** 根据考勤组ID修改考勤组的状态 */
	public void updateState(@Param("id") Long id, @Param("state") Integer state);

	/** 查询此设备上的群组 */
	public List<SimpleSignGroupVo> findSimpleList(@Param("sn") String sn);

}