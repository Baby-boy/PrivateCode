package com.glanway.ctrlhr.dao.signGroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.signGroup.SignGroupSignPoint;

public interface SignGroupSignPointDao extends BaseDao<SignGroupSignPoint> {

	public int deleteByPrimaryKey(Long id);

	public int insert(SignGroupSignPoint record);

	public int insertSelective(SignGroupSignPoint record);

	public SignGroupSignPoint selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SignGroupSignPoint record);

	public int updateByPrimaryKey(SignGroupSignPoint record);

	/** 根据考勤群组ID查询关联信息 */
	public List<SignGroupSignPoint> findMany(@Param("id") Long id);
}