package com.yd.gcj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerLabel;
import com.yd.gcj.entity.vo.YdMangerLabelVo;

public interface YdMangerServiceLabel {

	/***
	 * 查询所有标签信息
	 * @return
	 */
	List<YdMangerLabelVo> $queryAll();
	
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
	@Transactional
	Integer $insert(YdMangerLabel label);
	
	/***
	 * 修改标签信息
	 * @param label
	 * @return
	 */
	@Transactional
	Integer $update(YdMangerLabel label);
	
	/***
	 * 删除标签
	 * @param label_id
	 * @return
	 */
	@Transactional
	Integer $delete(@Param("label_id") Integer label_id);
	
}
