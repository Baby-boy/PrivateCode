package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerFiles;

public interface YdMangerMapperFiles {
	
	/***
	 * 根据sql多条件查询文件信息
	 * @param sql
	 * @return
	 */
	List<YdMangerFiles> $queryAllBySql(@Param("sql") String sql);
	
	/***
	 * 查询指定任务的文件信息
	 * @param taskId
	 * @return
	 */
	List<YdMangerFiles> $queryAllByTask(@Param("taskId") Integer taskId);
	
	/***
	 * 查询指定文件信息
	 * @param files_id
	 * @return
	 */
	YdMangerFiles $queryById(@Param("fileId") Integer fileId);
	
	/***
	 * 根据sql多条件查询文件信息数量
	 * @param sql
	 * @return
	 */
	Integer $queryCountNum(@Param("sql") String sql);
	
	/***
	 * 添加文件信息
	 * @param files
	 * @return
	 */
	Integer $insert(YdMangerFiles files);
	
	/***
	 * 修改文件信息
	 * @param files
	 * @return
	 */
	Integer $update(YdMangerFiles files);
	
	/***
	 * 删除指定文件信息
	 * @param files_id
	 * @return
	 */
	Integer $delete(@Param("files_id") Integer files_id);
}
