package com.glanway.ctrlhr.service.signGroup.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.dept.DeptDao;
import com.glanway.ctrlhr.dao.signGroup.SignGroupSignPointDao;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.signGroup.SignGroupSignPoint;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.signGroup.SignGroupSignPointService;

@Transactional
@Service("signGroupSignPointService")
public class SignGroupSignPointServiceImpl extends BaseServiceImpl<SignGroupSignPoint>
		implements SignGroupSignPointService {

	@Autowired
	private SignGroupSignPointDao signGroupSignPointDao;

	// TODO 由于没有部门的Service,这里只能注入部门的Dao,后期需要改成部门的Service
	@Autowired
	private DeptDao deptDao;

	@Override
	public void saveSignPointAndSignGroup(Long id, String[] signPointIdArr) {
		SignGroupSignPoint signGroupSignPoint = new SignGroupSignPoint();

		for (String signPointId : signPointIdArr) {
			signGroupSignPoint.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
			signGroupSignPoint.setDeptId(null);// 这里的组织机构不能和考勤点同时存在,所以设置为null,也是标记一下
			signGroupSignPoint.setSignGroupId(id);
			signGroupSignPoint.setType(1);// 1:代表选择考勤点
			signGroupSignPoint.setSignPointId(Long.valueOf(signPointId));
			signGroupSignPoint.setBatchDate(new Date());
			// TODO 创建人ID写死,后期需要更改
			signGroupSignPoint.setCreatedBy(1L);
			signGroupSignPoint.setCreatedDate(new Date());
			// TODO 创建程序ID写死,后期需要更改
			signGroupSignPoint.setCreProId(1L);
			// TODO 最后更新人ID写死,后期需要更改
			signGroupSignPoint.setLastModifiedBy(1L);
			signGroupSignPoint.setLastModifiedDate(signGroupSignPoint.getCreatedDate());
			// TODO 更新程序ID写死,后期需要更改
			signGroupSignPoint.setModProId(1L);

			// 保存考勤点和考勤组关联
			signGroupSignPointDao.insertSelective(signGroupSignPoint);
		}
	}

	@Override
	public void savePlaceDeptAndSignGroup(Long id, String[] placeDeptIdArr) {
		SignGroupSignPoint signGroupSignPoint = new SignGroupSignPoint();

		for (String placeDeptId : placeDeptIdArr) {
			// 根据部门ID判断选择的部门是否为组织架构的部门
			Dept dept = deptDao.selectByPrimaryKey(Long.valueOf(placeDeptId));
			if (null != dept && "0".equals(dept.getIsIndependent())) {
				// 说明选择的部门不是组织架构的部门 TODO 这里临时抛异常(有没有必要考虑部门是否是组织架构的问题有待商榷)
				throw new RuntimeException(dept.getName() + "不是一个组织架构部门!");
			}

			signGroupSignPoint.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
			signGroupSignPoint.setSignPointId(null);// 这里的考勤点不能和组织机构同时存在,所以设置为null,也是标记一下
			signGroupSignPoint.setSignGroupId(id);
			signGroupSignPoint.setType(2);// 2:代表选择组织架构
			signGroupSignPoint.setDeptId(Long.valueOf(placeDeptId));
			signGroupSignPoint.setBatchDate(new Date());
			// TODO 创建人ID写死,后期需要更改
			signGroupSignPoint.setCreatedBy(1L);
			signGroupSignPoint.setCreatedDate(new Date());
			// TODO 创建程序ID写死,后期需要更改
			signGroupSignPoint.setCreProId(1L);
			// TODO 最后更新人ID写死,后期需要更改
			signGroupSignPoint.setLastModifiedBy(1L);
			signGroupSignPoint.setLastModifiedDate(signGroupSignPoint.getCreatedDate());
			// TODO 更新程序ID写死,后期需要更改
			signGroupSignPoint.setModProId(1L);

			// 保存组织架构和考勤组关联
			signGroupSignPointDao.insertSelective(signGroupSignPoint);
		}

	}

	@Override
	public void updateSignPointAndSignGroup(Long id, String[] signPointIdArr) {
		// 关联表的编辑是先删除在新建的思想
		// 根据考勤群组的ID和类型获取获取关联表的所有信息
		List<SignGroupSignPoint> signGroupSignPointList = signGroupSignPointDao.findMany(id);
		for (SignGroupSignPoint signGroupSignPoint : signGroupSignPointList) {
			signGroupSignPoint.setDeleted("1");// 表示删除,逻辑删除
			signGroupSignPointDao.updateByPrimaryKeySelective(signGroupSignPoint);
		}

		saveSignPointAndSignGroup(id, signPointIdArr);
	}

	@Override
	public void updatePlaceDeptAndSignGroup(Long id, String[] placeDeptIdArr) {
		// 关联表的编辑是先删除在新建的思想
		// 根据考勤群组的ID和类型获取获取关联表的所有信息
		List<SignGroupSignPoint> signGroupSignPointList = signGroupSignPointDao.findMany(id);
		for (SignGroupSignPoint signGroupSignPoint : signGroupSignPointList) {
			signGroupSignPoint.setDeleted("1");// 表示删除,逻辑删除
			signGroupSignPointDao.updateByPrimaryKeySelective(signGroupSignPoint);
		}

		savePlaceDeptAndSignGroup(id, placeDeptIdArr);

	}

	@Override
	public void delete(String[] idArr) {
		for (String id : idArr) {
			List<SignGroupSignPoint> signGroupSignPointList = signGroupSignPointDao.findMany(Long.valueOf(id));
			for (SignGroupSignPoint signGroupSignPoint : signGroupSignPointList) {
				signGroupSignPoint.setDeleted("1");// 表示删除,逻辑删除
				signGroupSignPointDao.updateByPrimaryKeySelective(signGroupSignPoint);
			}

		}

	}

}
