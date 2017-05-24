package com.glanway.ctrlhr.service.signGroup.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.job.JobDao;
import com.glanway.ctrlhr.dao.signGroup.SignGroupDeptDao;
import com.glanway.ctrlhr.entity.job.Job;
import com.glanway.ctrlhr.entity.signGroup.SignGroupDept;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.signGroup.SignGroupDeptService;

@Transactional
@Service("signGroupDeptService")
public class SignGroupDeptServiceImpl extends BaseServiceImpl<SignGroupDept> implements SignGroupDeptService {

	@Autowired
	private SignGroupDeptDao signGroupDeptDao;

	// TODO 由于没有职位的Service,这里只能注入职位的Dao,后期需要改成职位的Service
	@Autowired
	private JobDao jobDao;

	@Override
	public void saveEmployeeAndSignGroup(Long id, String[] employeeIdArr) {
		SignGroupDept signGroupDept = new SignGroupDept();

		for (String employeeId : employeeIdArr) {
			signGroupDept.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
			signGroupDept.setDeptId(null);// 这里的部门不能和人员同时存在,所以设置为null,也是标记一下
			signGroupDept.setJobId(null);// 这里的职位不能和人员同时存在,所以设置为null,也是标记一下
			signGroupDept.setSignGroupId(id);
			signGroupDept.setType(2);// 2:代表选择的是人员
			signGroupDept.setEmployeeId(Long.valueOf(employeeId));
			signGroupDept.setBatchDate(new Date());
			// TODO 创建人ID写死,后期需要更改
			signGroupDept.setCreatedBy(1L);
			signGroupDept.setCreatedDate(new Date());
			// TODO 创建程序ID写死,后期需要更改
			signGroupDept.setCreProId(1L);
			// TODO 最后更新人ID写死,后期需要更改
			signGroupDept.setLastModifiedBy(1L);
			signGroupDept.setLastModifiedDate(signGroupDept.getCreatedDate());
			// TODO 更新程序ID写死,后期需要更改
			signGroupDept.setModProId(1L);

			// 保存特定员工和考勤组关联
			signGroupDeptDao.insertSelective(signGroupDept);
		}

	}

	@Override
	public void saveJobDeptAndSignGroup(Long id, String[] jobDeptIdArr, String[] jobIdArr) {
		SignGroupDept signGroupDept = new SignGroupDept();

		// 这里需要找到对应部门的对应职位
		for (String jobDeptId : jobDeptIdArr) {
			// 首先需要根据部门确定部门对应的职位
			List<Job> jobList = jobDao.findJobByDeptId(Long.valueOf(jobDeptId));
			if (null != jobList && jobList.size() > 0) {
				for (Job job : jobList) {
					for (String jobId : jobIdArr) {
						if (job.getId() == Long.valueOf(jobId)) {
							// 说明该部门下有该职位 -- 可以进行存储
							signGroupDept.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
							signGroupDept.setEmployeeId(null);// 这里的人员不能和职位部门同时存在,所以设置为null,也是标记一下
							signGroupDept.setSignGroupId(id);
							signGroupDept.setType(1);// 1:代表选择的是职位部门
							signGroupDept.setDeptId(Long.valueOf(jobDeptId));
							signGroupDept.setJobId(Long.valueOf(jobId));
							signGroupDept.setBatchDate(new Date());
							// TODO 创建人ID写死,后期需要更改
							signGroupDept.setCreatedBy(1L);
							signGroupDept.setCreatedDate(new Date());
							// TODO 创建程序ID写死,后期需要更改
							signGroupDept.setCreProId(1L);
							// TODO 最后更新人ID写死,后期需要更改
							signGroupDept.setLastModifiedBy(1L);
							signGroupDept.setLastModifiedDate(signGroupDept.getCreatedDate());
							// TODO 更新程序ID写死,后期需要更改
							signGroupDept.setModProId(1L);

							// 保存职位部门和考勤组关联
							signGroupDeptDao.insertSelective(signGroupDept);
						}
					}
				}
			}
		}
	}

	@Override
	public void updateEmployeeAndSignGroup(Long id, String[] employeeIdArr) {
		// 关联表的编辑是先删除在新建的思想
		// 根据考勤群组的ID和类型获取获取关联表的所有信息
		List<SignGroupDept> signGroupDeptLis = signGroupDeptDao.findMany(id);
		for (SignGroupDept signGroupDept : signGroupDeptLis) {
			signGroupDept.setDeleted("1");// 表示删除,逻辑删除
			signGroupDeptDao.updateByPrimaryKeySelective(signGroupDept);
		}

		saveEmployeeAndSignGroup(id, employeeIdArr);
	}

	@Override
	public void updateJobDeptAndSignGroup(Long id, String[] jobDeptIdArr, String[] jobIdArr) {
		// 关联表的编辑是先删除在新建的思想
		// 根据考勤群组的ID和类型获取获取关联表的所有信息
		List<SignGroupDept> signGroupDeptLis = signGroupDeptDao.findMany(id);
		for (SignGroupDept signGroupDept : signGroupDeptLis) {
			signGroupDept.setDeleted("1");// 表示删除,逻辑删除
			signGroupDeptDao.updateByPrimaryKeySelective(signGroupDept);
		}

		saveJobDeptAndSignGroup(id, jobDeptIdArr, jobIdArr);
	}

	@Override
	public void delete(String[] idArr) {
		for (String id : idArr) {
			List<SignGroupDept> signGroupDeptLis = signGroupDeptDao.findMany(Long.valueOf(id));
			for (SignGroupDept signGroupDept : signGroupDeptLis) {
				signGroupDept.setDeleted("1");// 表示删除,逻辑删除
				signGroupDeptDao.updateByPrimaryKeySelective(signGroupDept);
			}

		}
	}

}
