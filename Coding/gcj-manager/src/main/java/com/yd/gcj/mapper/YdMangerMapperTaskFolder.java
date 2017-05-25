package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskFolder;
import com.yd.gcj.entity.vo.YdMangerTaskFolderVo;

public interface YdMangerMapperTaskFolder {
	
	/***
	 * 查询指定项目中指定文件夹名称的文件夹信息
	 * @param name
	 * @param taskId
	 * @return
	 */
	YdMangerTaskFolder queryByName(@Param("name") String name,@Param("taskId") Integer taskId);
	
	/***
	 * 查询指定项目中的所有文件夹信息
	 * @param taskId
	 * @return
	 */
	List<YdMangerTaskFolderVo> queryByTaskId(@Param("taskId") Integer taskId);
	
	/***
	 * 为任务添加文件夹
	 * @param folder
	 * @return
	 */
	Integer insert(YdMangerTaskFolder folder);
	
	/***
	 * 检查是否存在此名字的文件夹
	 * @param name
	 * @return
	 */
	Integer isExistByName(@Param("name") String name,@Param("taskId") Integer taskId);
	
	/***
	 * 删除任务中指定的文件夹
	 * @param folderId
	 * @return
	 */
	Integer del(@Param("folderId") Integer folderId);
	
	/***
	 * 删除指定的任务文件夹
	 * @param taskId
	 * @return
	 */
	Integer dels(@Param("taskId") Integer taskId);
	
}
