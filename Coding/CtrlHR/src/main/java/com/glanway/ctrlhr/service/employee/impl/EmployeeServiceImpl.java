package com.glanway.ctrlhr.service.employee.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.employee.EmployeeDao;
import com.glanway.ctrlhr.dao.employee.FingerFaceTemplateDao;
import com.glanway.ctrlhr.dao.signGroup.SignGroupDao;
import com.glanway.ctrlhr.dao.signPoint.SignPointDao;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.para.SignGroupPara;
import com.glanway.ctrlhr.entity.signGroup.SignGroup;
import com.glanway.ctrlhr.entity.signPoint.SignPoint;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.entity.vo.SimpleEmployeeVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.employee.EmployeeService;
import com.glanway.ctrlhr.service.signGroup.SignGroupService;

/**
 * 说明 :
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午9:25:03
 */
@Transactional
@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private FingerFaceTemplateDao fingerFaceTemplateDao;

	@Autowired
	private SignPointDao signPointDao;

	@Autowired
	private SignGroupDao signGroupDao;

	@Autowired
	private SignGroupService signGroupService;

	@Override
	public Page<EmployeeVo> findList(EmployeePara para) {
		if (StringUtils.isNotEmpty(para.getGatherMsgStates())) {
			String[] gatherMsgStateList = StringUtils.split(para.getGatherMsgStates(), ",");
			para.setGatherMsgStateList(gatherMsgStateList);
		}
		if (StringUtils.isNotEmpty(para.getJobStates())) {
			String[] jobStateList = StringUtils.split(para.getJobStates(), ",");
			para.setJobStateList(jobStateList);
		}
		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdList = StringUtils.split(para.getDeptIds(), ",");
			para.setDeptIdList(deptIdList);
		}
		int count = employeeDao.findListCount(para);
		Page<EmployeeVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<EmployeeVo> list = employeeDao.findList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public void saveEmployee(Employee employee) {
		Employee emp = employeeDao.findByCode(employee.getCode());
		if (null != emp) {
			throw new RuntimeException();
		}
		employee.setId(null);
		employee.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		employee.setCreatedBy(1L);
		employee.setCreatedDate(new Date());
		// TODO 创建程序ID写死,后期需要更改
		employee.setCreProId(1L);
		// TODO 最后更新人ID写死,后期需要更改
		employee.setLastModifiedBy(1L);
		employee.setLastModifiedDate(employee.getCreatedDate());
		// TODO 更新程序ID写死,后期需要更改
		employee.setModProId(1L);
		employeeDao.insertSelective(employee);
	}

	@Override
	public EmployeeVo getInfo(Long id) {
		return employeeDao.findOne(id);
	}

	@Override
	public List<Employee> getEmployeeByJob(Long jobId) {
		return employeeDao.findEmployeeByJob(jobId);
	}

	@Override
	public void updateEmployee(Employee employee) {
		if (StringUtils.isNotEmpty(employee.getCode())) {
			Employee emp = employeeDao.findByCode(employee.getCode());
			if (null == emp) {
				throw new RuntimeException();
			}
		}
		// TODO 最后更新人ID写死,后期需要更改
		employee.setLastModifiedBy(1L);
		employee.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		employee.setModProId(1L);
		employeeDao.updateByPrimaryKeySelective(employee);
	}

	@Override
	public Page<DeviceToEmployeeVo> findDeviceToEmployeeList(DeviceToEmployeePara para) {
		if (StringUtils.isNotEmpty(para.getGatherMsgStates())) {
			String[] gatherMsgStateList = StringUtils.split(para.getGatherMsgStates(), ",");
			para.setGatherMsgStateList(gatherMsgStateList);
		}

		int count = employeeDao.findListDeviceToEmployeeCount(para);
		Page<DeviceToEmployeeVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<DeviceToEmployeeVo> list = employeeDao.findDeviceToEmployeeList(para);
			page.setList(list);
		}
		return page;
	}

	@Override
	public EmployeeSignInfoVo findSignInfo(Long id, String code) {
		return employeeDao.findSignInfo(id, code);
	}

	@Override
	public void dimission(String codes, Date quitDate) {
		String[] codeArr = StringUtils.split(codes, ",");
		employeeDao.updateQuitDateAndJobState(codeArr, quitDate, 3);

		// 当员员工离职后,删除数据库表中的指纹模板,并通知IClock系统进行数据更新
		fingerFaceTemplateDao.delete(codeArr);
	}

	@Override
	public List<EmployeeVo> findStaff(String codes) {
		if (StringUtils.isBlank(codes)) {
			return new ArrayList<EmployeeVo>();
		}
		String[] codeArr = StringUtils.split(codes, ",");
		return employeeDao.findByCodeAndJobState(codeArr, new Integer[] { 1, 2 });
	}

	@Override
	public List<SimpleEmployeeVo> findSimpleListByDeptId(String deptIds, KeywordPara para) {
		String[] deptIdList = null;
		if (StringUtils.isNotEmpty(deptIds)) {
			deptIdList = StringUtils.split(deptIds, ",");
		}

		return employeeDao.findSimpleListByDeptId(deptIdList, para);
	}

	@Override
	public List<EmployeeVo> findMany(EmployeePara para) {
		if (StringUtils.isNotEmpty(para.getGatherMsgStates())) {
			String[] gatherMsgStateList = StringUtils.split(para.getGatherMsgStates(), ",");
			para.setGatherMsgStateList(gatherMsgStateList);
		}
		if (StringUtils.isNotEmpty(para.getJobStates())) {
			String[] jobStateList = StringUtils.split(para.getJobStates(), ",");
			para.setJobStateList(jobStateList);
		}
		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdList = StringUtils.split(para.getDeptIds(), ",");
			para.setDeptIdList(deptIdList);
		}
		return employeeDao.findMany(para);
	}

	@Override
	public void remoteSetManager(String codes, String pwd, String sns) {
		String pri = "14";// 设置权限,14:管理员,0:普通用户
		// String deptName = "IT";// 设置哪些部门的人员为管理员,单项模糊查询 'deptName%'
		// String signGroupName = "IT管理员群组";// 新建的考勤群组的名称
		String signGroupName = "管理员群组";// 新建的考勤群组的名称

		String[] codeArr = StringUtils.split(codes, ",");

		// 设置IT部门的员工都为管理员
		// employeeDao.remoteSetManager(pri, pwd, deptName);

		// 设置职员代码为30000031的员工为管理员
		employeeDao.remoteSetManager(pri, pwd, codeArr);

		// 把这些人创建一个考勤群组(关系为人员和所有的考勤点关联)
		SignGroup signGroup = signGroupDao.findByName(signGroupName);

		// 查询所有需要被设置为管理员的人
		List<Employee> employees = employeeDao.findAllManager(codeArr);
		String employeeIds = "";
		if (null != employees && employees.size() > 0) {
			StringBuffer empBuffer = new StringBuffer();
			for (Employee employee : employees) {
				empBuffer.append(employee.getId()).append(",");
			}
			employeeIds = empBuffer.deleteCharAt(empBuffer.length() - 1).toString();
		}
		// 查询所有的考勤点
		List<SignPoint> signPoints = null;
		if (StringUtils.isEmpty(sns)) {
			signPoints = signPointDao.findAllSignPoint();
		} else {
			String[] snArr = StringUtils.split(sns, ",");
			signPoints = signPointDao.findSignPointBySn(snArr);
		}
		String signPointIds = "";
		if (null != signPoints && signPoints.size() > 0) {
			StringBuffer spBuffer = new StringBuffer();
			for (SignPoint signPoint : signPoints) {
				spBuffer.append(signPoint.getId()).append(",");
			}
			signPointIds = spBuffer.deleteCharAt(spBuffer.length() - 1).toString();
		}

		// 判断考勤群组状态,有则更新,无则新建
		SignGroupPara para = new SignGroupPara();
		if (null == signGroup) {
			para.setEmployeeIds(employeeIds);
			para.setSignPointIds(signPointIds);
			para.setName(signGroupName);
			signGroupService.save(para);
		} else {
			para.setId(signGroup.getId());
			para.setName(signGroupName);
			para.setEmployeeIds(employeeIds);
			para.setSignPointIds(signPointIds);
			signGroupService.update(para);
		}
	}

}
