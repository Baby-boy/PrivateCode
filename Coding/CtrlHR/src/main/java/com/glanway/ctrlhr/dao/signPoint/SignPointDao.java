package com.glanway.ctrlhr.dao.signPoint;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.para.SignPointPara;
import com.glanway.ctrlhr.entity.signPoint.SignPoint;
import com.glanway.ctrlhr.entity.vo.SignPointVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignPointVo;

public interface SignPointDao extends BaseDao<SignPoint> {
	public int deleteByPrimaryKey(Long id);

	public int insert(SignPoint record);

	public int insertSelective(SignPoint record);

	public SignPoint selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SignPoint record);

	public int updateByPrimaryKey(SignPoint record);

	/** 查询考勤点列表总数 */
	public int findListCount(@Param("para") SignPointPara para);

	/** 查询考勤点列表 */
	public List<SignPoint> findList(@Param("para") SignPointPara para);

	/** 根据ID获取考勤点的信息 */
	public SignPointVo findOne(@Param("id") Long id);

	/** 此处为逻辑删除,更新删除字段为1 */
	public void delete(@Param("signPointId") Long signPointId);

	/** 查询(精简)考勤点列表 */
	public List<SimpleSignPointVo> findSimpleList(@Param("para") KeywordPara para);

}