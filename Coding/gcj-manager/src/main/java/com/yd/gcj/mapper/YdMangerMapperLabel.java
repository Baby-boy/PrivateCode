package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerLabel;

public interface YdMangerMapperLabel {
	
	/***
	 * 查询所有标签信息
	 * @return
	 */
	List<YdMangerLabel> $queryAll();
	
	/**
	 * 查询所有标签信息带任务标记
	 * @param taskId
	 * @return
	 */
	List<YdMangerLabel> $queryAllToTaskUpdate(Integer taskId);
	
	/***
	 * 查询标签所有标签数量
	 * @return
	 */
	Integer $queryCountNum();
	
	/***
	 * 根据类型查询该类型的标签数量
	 * @param label_type
	 * @return
	 */
	Integer $queryCountNumByType(@Param("label_type") Integer label_type);
	
	/***
	 * 添加新标签
	 * @param label
	 * @return
	 */
	Integer $insert(YdMangerLabel label);
	
	/***
	 * 修改标签信息
	 * @param label
	 * @return
	 */
	Integer $update(YdMangerLabel label);
	
	/***
	 * 删除标签
	 * @param label_id
	 * @return
	 */
	Integer $delete(@Param("label_id") Integer label_id);
	
	
}
