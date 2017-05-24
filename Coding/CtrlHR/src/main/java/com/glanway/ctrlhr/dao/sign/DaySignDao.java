package com.glanway.ctrlhr.dao.sign;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.sign.DaySign;

public interface DaySignDao extends BaseDao<DaySign> {
	public int deleteByPrimaryKey(Long id);

	public int insert(DaySign record);

	public int insertSelective(DaySign record);

	public DaySign selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(DaySign record);

	public int updateByPrimaryKey(DaySign record);

	/** 根据员工代码查询员工 */
	public DaySign findEmployeeById(@Param("employeeId") Long employeeId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd);
}