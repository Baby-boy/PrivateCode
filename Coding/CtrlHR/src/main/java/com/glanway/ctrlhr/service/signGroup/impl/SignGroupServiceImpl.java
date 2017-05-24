package com.glanway.ctrlhr.service.signGroup.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.signGroup.SignGroupDao;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.para.SignGroupPara;
import com.glanway.ctrlhr.entity.signGroup.SignGroup;
import com.glanway.ctrlhr.entity.vo.SignGroupVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignGroupVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.signGroup.SignGroupDeptService;
import com.glanway.ctrlhr.service.signGroup.SignGroupService;
import com.glanway.ctrlhr.service.signGroup.SignGroupSignPointService;

@Transactional
@Service("signGroupService")
public class SignGroupServiceImpl extends BaseServiceImpl<SignGroup> implements SignGroupService {

	@Autowired
	private SignGroupDao signGroupDao;

	@Autowired
	private SignGroupDeptService signGroupDeptService;

	@Autowired
	private SignGroupSignPointService signGroupSignPointService;

	@Override
	public Page<SignGroupVo> findList(BasePara para) {

		int count = signGroupDao.findListCount(para);
		Page<SignGroupVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<SignGroupVo> list = signGroupDao.findList(para);
			page.setList(list);
		}
		return page;
	}

	@Override
	public void save(SignGroupPara para) {
		SignGroup signGroup = new SignGroup();
		signGroup.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
		signGroup.setName(para.getName());
		signGroup.setRemark(para.getRemark());
		signGroup.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		signGroup.setCreatedBy(1L);
		signGroup.setCreatedDate(new Date());
		// TODO 创建程序ID写死,后期需要更改
		signGroup.setCreProId(1L);
		// TODO 最后更新人ID写死,后期需要更改
		signGroup.setLastModifiedBy(1L);
		signGroup.setLastModifiedDate(signGroup.getCreatedDate());
		// TODO 更新程序ID写死,后期需要更改
		signGroup.setModProId(1L);

		signGroupDao.insertSelective(signGroup);

		// 选择员工(直接选人)
		if (StringUtils.isNotEmpty(para.getEmployeeIds()) && StringUtils.isEmpty(para.getJobDeptIds())
				&& StringUtils.isEmpty(para.getJobIds())) {
			String[] employeeIdArr = StringUtils.split(para.getEmployeeIds(), ",");
			// 存部门和考勤组的关联
			signGroupDeptService.saveEmployeeAndSignGroup(signGroup.getId(), employeeIdArr);
			// 选择考勤点
			if (StringUtils.isNotEmpty(para.getSignPointIds()) && StringUtils.isEmpty(para.getPlaceDeptIds())) {
				String[] signPointIdArr = StringUtils.split(para.getSignPointIds(), ",");
				// 存考勤点和考勤组关联
				signGroupSignPointService.saveSignPointAndSignGroup(signGroup.getId(), signPointIdArr);
			}
			// 选择组织架构
			if (StringUtils.isEmpty(para.getSignPointIds()) && StringUtils.isNotEmpty(para.getPlaceDeptIds())) {
				String[] placeDeptIdArr = StringUtils.split(para.getPlaceDeptIds(), ",");
				// 存组织架构和考勤组关联
				signGroupSignPointService.savePlaceDeptAndSignGroup(signGroup.getId(), placeDeptIdArr);
			}
		}
		// 选择职位部门
		if (StringUtils.isEmpty(para.getEmployeeIds()) && StringUtils.isNotEmpty(para.getJobDeptIds())
				&& StringUtils.isNotEmpty(para.getJobIds())) {
			String[] jobDeptIdArr = StringUtils.split(para.getJobDeptIds(), ",");
			String[] jobIdArr = StringUtils.split(para.getJobIds(), ",");
			// 存职位部门和考勤组关联
			signGroupDeptService.saveJobDeptAndSignGroup(signGroup.getId(), jobDeptIdArr, jobIdArr);
			// 选择考勤点
			if (StringUtils.isNotEmpty(para.getSignPointIds()) && StringUtils.isEmpty(para.getPlaceDeptIds())) {
				String[] signPointIdArr = StringUtils.split(para.getSignPointIds(), ",");
				// 存考勤点和考勤组关联
				signGroupSignPointService.saveSignPointAndSignGroup(signGroup.getId(), signPointIdArr);
			}
			// 选择组织架构
			if (StringUtils.isEmpty(para.getSignPointIds()) && StringUtils.isNotEmpty(para.getPlaceDeptIds())) {
				String[] placeDeptIdArr = StringUtils.split(para.getPlaceDeptIds(), ",");
				// 存组织架构和考勤组关联
				signGroupSignPointService.savePlaceDeptAndSignGroup(signGroup.getId(), placeDeptIdArr);
			}
		}
	}

	@Override
	public SignGroupVo getInfo(Long id) {
		return signGroupDao.getInfo(id);
	}

	@Override
	public void update(SignGroupPara para) {
		SignGroup signGroup = new SignGroup();
		signGroup.setId(para.getId());
		signGroup.setName(para.getName());
		signGroup.setRemark(para.getRemark());
		// TODO 最后更新人ID写死,后期需要更改
		signGroup.setLastModifiedBy(1L);
		signGroup.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		signGroup.setModProId(1L);

		signGroupDao.updateByPrimaryKeySelective(signGroup);

		// 选择员工(直接选人)
		if (StringUtils.isNotEmpty(para.getEmployeeIds()) && StringUtils.isEmpty(para.getJobDeptIds())
				&& StringUtils.isEmpty(para.getJobIds())) {
			String[] employeeIdArr = StringUtils.split(para.getEmployeeIds(), ",");
			// 存部门和考勤组的关联
			signGroupDeptService.updateEmployeeAndSignGroup(signGroup.getId(), employeeIdArr);
			// 选择考勤点
			if (StringUtils.isNotEmpty(para.getSignPointIds()) && StringUtils.isEmpty(para.getPlaceDeptIds())) {
				String[] signPointIdArr = StringUtils.split(para.getSignPointIds(), ",");
				// 存考勤点和考勤组关联
				signGroupSignPointService.updateSignPointAndSignGroup(signGroup.getId(), signPointIdArr);
			}
			// 选择组织架构
			if (StringUtils.isEmpty(para.getSignPointIds()) && StringUtils.isNotEmpty(para.getPlaceDeptIds())) {
				String[] placeDeptIdArr = StringUtils.split(para.getPlaceDeptIds(), ",");
				// 存组织架构和考勤组关联
				signGroupSignPointService.updatePlaceDeptAndSignGroup(signGroup.getId(), placeDeptIdArr);
			}
		}
		// 选择职位部门
		if (StringUtils.isEmpty(para.getEmployeeIds()) && StringUtils.isNotEmpty(para.getJobDeptIds())
				&& StringUtils.isNotEmpty(para.getJobIds())) {
			String[] jobDeptIdArr = StringUtils.split(para.getJobDeptIds(), ",");
			String[] jobIdArr = StringUtils.split(para.getJobIds(), ",");
			// 存职位部门和考勤组关联
			signGroupDeptService.updateJobDeptAndSignGroup(signGroup.getId(), jobDeptIdArr, jobIdArr);
			// 选择考勤点
			if (StringUtils.isNotEmpty(para.getSignPointIds()) && StringUtils.isEmpty(para.getPlaceDeptIds())) {
				String[] signPointIdArr = StringUtils.split(para.getSignPointIds(), ",");
				// 存考勤点和考勤组关联
				signGroupSignPointService.updateSignPointAndSignGroup(signGroup.getId(), signPointIdArr);
			}
			// 选择组织架构
			if (StringUtils.isEmpty(para.getSignPointIds()) && StringUtils.isNotEmpty(para.getPlaceDeptIds())) {
				String[] placeDeptIdArr = StringUtils.split(para.getPlaceDeptIds(), ",");
				// 存组织架构和考勤组关联
				signGroupSignPointService.updatePlaceDeptAndSignGroup(signGroup.getId(), placeDeptIdArr);
			}
		}

	}

	@Override
	public void delete(String ids) {
		String[] idArr = StringUtils.split(ids, ",");
		signGroupDao.deleteBatch(idArr);
		signGroupDeptService.delete(idArr);
		signGroupSignPointService.delete(idArr);
	}

	@Override
	public void updateState(Long id) {
		// 为了确保后期因为冻结和开启考勤组的原因,根据不同状态进行不同的考勤数据同步,这里采用从数据库查出状态
		SignGroup signGroup = signGroupDao.selectByPrimaryKey(id);
		if (null != signGroup) {
			if (1L == signGroup.getState()) {
				// 说明原状态为开启,更新状态为关闭
				signGroupDao.updateState(id, 2);
			}
			if (2L == signGroup.getState()) {
				// 说明原状态为关闭,更新状态为开启
				signGroupDao.updateState(id, 1);
			}
		}
	}

	@Override
	public List<SimpleSignGroupVo> findsimpleList(String sn) {
		return signGroupDao.findSimpleList(sn);
	}

}