package com.glanway.ctrlhr.dao.sign;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.para.MonthlySignPara;
import com.glanway.ctrlhr.entity.sign.MonthlySign;
import com.glanway.ctrlhr.entity.vo.MonthlySignVo;

public interface MonthlySignDao extends BaseDao<MonthlySign> {
	int deleteByPrimaryKey(Long id);

	int insert(MonthlySign record);

	int insertSelective(MonthlySign record);

	MonthlySign selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MonthlySign record);

	int updateByPrimaryKey(MonthlySign record);

	/** 查询考勤记录列表总数 */
	public int findListCount(@Param("para") MonthlySignPara para);

	/** 查询考勤记录列表 */
	public List<MonthlySignVo> findList(@Param("para") MonthlySignPara para);

	/** 获取需要导出的月考勤记录列表 */
	public List<MonthlySignVo> findMany(@Param("para") MonthlySignPara para);
}