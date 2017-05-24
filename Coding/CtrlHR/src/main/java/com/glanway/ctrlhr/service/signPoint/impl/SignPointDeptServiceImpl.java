package com.glanway.ctrlhr.service.signPoint.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.signPoint.SignPointDeptDao;
import com.glanway.ctrlhr.entity.signPoint.SignPointDept;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.signPoint.SignPointDeptService;

@Transactional
@Service("signPointDeptService")
public class SignPointDeptServiceImpl extends BaseServiceImpl<SignPointDept> implements SignPointDeptService {

	@Autowired
	private SignPointDeptDao signPointDeptDao;

	@Override
	public void save(Long id, String[] deptIdArr) {
		SignPointDept signPointDept = new SignPointDept();

		for (String deptIdStr : deptIdArr) {
			signPointDept.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
			signPointDept.setSignPointId(id);
			signPointDept.setDeptId(Long.valueOf(deptIdStr));
			signPointDept.setBatchDate(new Date());
			// TODO 创建人ID写死,后期需要更改
			signPointDept.setCreatedBy(1L);
			signPointDept.setCreatedDate(new Date());
			// TODO 创建程序ID写死,后期需要更改
			signPointDept.setCreProId(1L);
			// TODO 最后更新人ID写死,后期需要更改
			signPointDept.setLastModifiedBy(1L);
			signPointDept.setLastModifiedDate(signPointDept.getCreatedDate());
			// TODO 更新程序ID写死,后期需要更改
			signPointDept.setModProId(1L);
			signPointDeptDao.insertSelective(signPointDept);
		}

	}

	@Override
	public List<SignPointDept> findDept(Long signPointId) {
		return signPointDeptDao.findMany(signPointId);
	}

	@Override
	public void updateDept(Long id, String[] deptIdArr) {
		// 部门的更新是先把中间表的所有数据先删除,然后在重新创建的思路
		// 先通过考勤点ID查询中间表中所有相关的数据进行删除处理
		List<SignPointDept> signPointDeptList = signPointDeptDao.findMany(id);
		// 执行删除操作
		for (SignPointDept signPointDept : signPointDeptList) {
			signPointDept.setDeleted("1");// 逻辑删除
			signPointDeptDao.updateByPrimaryKeySelective(signPointDept);
		}
		// 重新创建
		save(id, deptIdArr);
	}

}
