package com.glanway.ctrlhr.dao.sign;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.para.SignPara;
import com.glanway.ctrlhr.entity.sign.Sign;
import com.glanway.ctrlhr.entity.vo.SignRecordVo;
import com.glanway.ctrlhr.entity.vo.SignVo;

public interface SignDao extends BaseDao<Sign> {
	public int deleteByPrimaryKey(Long id);

	public int insert(Sign record);

	public int insertSelective(Sign record);

	public Sign selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(Sign record);

	public int updateByPrimaryKey(Sign record);

	/** 查询考勤记录详情导出列表 */
	public List<SignVo> findMany(@Param("para") SignPara para);

	/** 查询人员一天考勤的异常状态 (Task) */
	public List<SignRecordVo> findExList(@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);

	/** 根据员工代码查询员工 */
	public SignRecordVo findEmployeeByCode(@Param("employeeCode") String employeeCode,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);
}