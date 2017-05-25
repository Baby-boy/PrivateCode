package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.YdMangerSystemAdmin;
import com.yd.gcj.entity.vo.YdMangerFilesTaskVo;
import com.yd.gcj.entity.vo.YdMangerTaskVo;
import com.yd.gcj.system.service.YdMangerServiceSystemMessage;
import com.yd.gcj.system.service.YdMangerServiceSystemTask;
import com.yd.gcj.system.service.YdMangerserviceSystemFilesTask;
import com.yd.gcj.tool.ObjectMapperFactory;

/**
 * description(任务信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
@Transactional
public class YdMangerControllerSystemTask {

	@Autowired
	private YdMangerServiceSystemTask ydMangerServiceSystemTask;

	@Autowired
	private YdMangerServiceSystemMessage ydMangerServiceSystemMessage;

	@Autowired
	private YdMangerserviceSystemFilesTask ydMangerserviceSystemFilesTask;

	/**
	 * description(查询所有的任务信息)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllTask")
	public String queryAllTask(Integer p, String task_num, Integer task_state, Model model) {
		// 当前页
		if (p == null) {
			p = 1;
			PageHelper.startPage(p, 8);
		} else {
			PageHelper.startPage(p, 8);
		}
		;

		List<YdMangerTaskVo> taskList = ydMangerServiceSystemTask.queryAllTask(task_num, task_state);
		PageInfo<YdMangerTaskVo> pageInfo = new PageInfo<YdMangerTaskVo>(taskList);
		// 总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 8 != 0) {
			totalPage = total / 8 + 1;
		} else {
			totalPage = total / 8;
		}

		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("taskList", taskList);
		model.addAttribute("task_num", task_num);
		model.addAttribute("task_state", task_state);
		return "system/rwgl/task";
	}

	/**
	 * description(根据指定的task_id删除任务)
	 * 
	 * @param
	 * @param task_id
	 * @return
	 */
	@RequestMapping("/deleteTask")
	@ResponseBody
	public Object deleteTaskByTaskId(Integer task_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemTask.deleteTaskByTaskId(task_id);
		if (delNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(根据task_id查询当前任务的详情)
	 * 
	 * @param
	 * @param task_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryTaskByTaskId/{task_id}")
	public String queryTaskByTaskId(@PathVariable Integer task_id, Model model) {
		YdMangerTaskVo ydMangerTaskVo = ydMangerServiceSystemTask.queryTaskByTaskId(task_id);
		// 查询当前任务所对应的所有文件
		List<YdMangerFilesTaskVo> filesTaskList = ydMangerserviceSystemFilesTask.queryFilesTaskByTaskId(task_id);
		ObjectMapperFactory.doIt(filesTaskList);
		model.addAttribute("filesTaskList", filesTaskList);
		model.addAttribute("ydMangerTaskVo", ydMangerTaskVo);
		return "system/rwgl/taskdetails";
	}

	/**
	 * description(审核任务之前先查询当前任务)
	 * 
	 * @param
	 * @param task_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryTaskStateByTaskId/{task_id}")
	public String updateTaskStateByTaskId(@PathVariable Integer task_id, Model model) {
		List<YdMangerTaskVo> taskStateList = ydMangerServiceSystemTask.queryTaskAllState();
		YdMangerTaskVo ydMangerTaskVo = ydMangerServiceSystemTask.queryTaskByTaskId(task_id);
		model.addAttribute("ydMangerTaskVo", ydMangerTaskVo);
		model.addAttribute("taskStateList", taskStateList);
		return "system/rwgl/checkingtask";
	}

	/**
	 * description(修改任务状态)
	 * 
	 * @param
	 * @param ydMangerTaskVo
	 * @return
	 */
	@RequestMapping("/updateTaskStateByTaskId")
	@ResponseBody
	public Object updateTaskStateByTaskId(YdMangerTaskVo ydMangerTask, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (ydMangerTask != null) {
			if (ydMangerTask.getTask_state() != 0 && ydMangerTask.getTask_state() != 1
					&& ydMangerTask.getTask_state() != 9) {
				// 已经不需要审核
				map.put("msg", 300);
			} else {
				Integer updateNum = ydMangerServiceSystemTask.updateTaskStateByTaskId(ydMangerTask);
				if (updateNum > 0) {

					// 修改成功
					map.put("msg", 200);

					// 当修改任务状态为审核通过时添加系统消息
					if (ydMangerTask.getTask_state() == 1) {
						YdMangerMessage ydMangerMessage = new YdMangerMessage();
						// 审核成功添加系统消息给用户
						// 任务id
						Integer task_id = ydMangerTask.getTask_id();
						ydMangerMessage.setMsg_tid(task_id);
						// 发送方id
						Integer task_uid = ydMangerTask.getTask_uid();
						ydMangerMessage.setMsg_sbid(task_uid);
						YdMangerSystemAdmin admin = (YdMangerSystemAdmin) session.getAttribute("admin");
						// 接收方id
						Integer admin_id = admin.getAdmin_id();
						ydMangerMessage.setMsg_said(admin_id);
						// 消息类型
						ydMangerMessage.setMsg_type(0);
						ydMangerMessage.setMsg_contents("任务审核通过,任务名称 :" + ydMangerTask.getTask_pname());
						ydMangerServiceSystemMessage.addMessage(ydMangerMessage);
					} // 当修改任务状态为审核通过时添加系统消息
					else if (ydMangerTask.getTask_state() == 9) {
						YdMangerMessage ydMangerMessage = new YdMangerMessage();
						// 审核成功添加系统消息给用户
						// 任务id
						Integer task_id = ydMangerTask.getTask_id();
						ydMangerMessage.setMsg_tid(task_id);
						// 发送方id
						Integer task_uid = ydMangerTask.getTask_uid();
						ydMangerMessage.setMsg_sbid(task_uid);
						YdMangerSystemAdmin admin = (YdMangerSystemAdmin) session.getAttribute("admin");
						// 接收方id
						Integer admin_id = admin.getAdmin_id();
						ydMangerMessage.setMsg_said(admin_id);
						// 消息类型
						ydMangerMessage.setMsg_type(0);
						ydMangerMessage.setMsg_contents("任务审核失败,任务名称 :" + ydMangerTask.getTask_pname());
						ydMangerServiceSystemMessage.addMessage(ydMangerMessage);
					}

				} else {
					// 修改失败
					map.put("msg", 400);
				}
			}

		} else {
			// 没有查到当前任务信息
			map.put("msg", 100);
		}

		return map;
	}

}
