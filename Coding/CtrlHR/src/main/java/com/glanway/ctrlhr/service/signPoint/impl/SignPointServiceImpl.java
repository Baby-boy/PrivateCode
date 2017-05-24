package com.glanway.ctrlhr.service.signPoint.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.signPoint.SignPointDao;
import com.glanway.ctrlhr.entity.device.Device;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.para.SignPointPara;
import com.glanway.ctrlhr.entity.signPoint.SignPoint;
import com.glanway.ctrlhr.entity.signPoint.SignPointDept;
import com.glanway.ctrlhr.entity.vo.SignPointVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignPointVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.device.DeviceService;
import com.glanway.ctrlhr.service.signPoint.SignPointDeptService;
import com.glanway.ctrlhr.service.signPoint.SignPointService;

@Transactional
@Service("signPointService")
public class SignPointServiceImpl extends BaseServiceImpl<SignPoint> implements SignPointService {

	@Autowired
	private SignPointDao signPointDao;

	@Autowired
	private SignPointDeptService signPointDeptService;

	@Autowired
	private DeviceService deviceService;

	@Override
	public Page<SignPoint> findList(SignPointPara para) {
		int count = signPointDao.findListCount(para);
		Page<SignPoint> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<SignPoint> list = signPointDao.findList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public void save(SignPointPara para) {
		SignPoint signPoint = new SignPoint();
		signPoint.setId(null);// 虽然new出来的对象本身ID就为null,但这里写出来是为了提示标记一下
		signPoint.setName(para.getName());
		signPoint.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		signPoint.setCreatedBy(1L);
		signPoint.setCreatedDate(new Date());
		// TODO 创建程序ID写死,后期需要更改
		signPoint.setCreProId(1L);
		// TODO 最后更新人ID写死,后期需要更改
		signPoint.setLastModifiedBy(1L);
		signPoint.setLastModifiedDate(signPoint.getCreatedDate());
		// TODO 更新程序ID写死,后期需要更改
		signPoint.setModProId(1L);
		signPointDao.insertSelective(signPoint);

		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdArr = StringUtils.split(para.getDeptIds(), ",");
			// 存储考勤点和部门关系
			signPointDeptService.save(signPoint.getId(), deptIdArr);
		}

		if (StringUtils.isNotEmpty(para.getDeviceIds())) {
			String[] deviceIdArr = StringUtils.split(para.getDeviceIds(), ",");
			// 更新未绑定考勤点的考勤机的数据
			deviceService.update(signPoint.getId(), deviceIdArr);
		}

	}

	@Override
	public SignPointVo getInfo(Long id) {
		return signPointDao.findOne(id);
	}

	@Override
	public void update(SignPointPara para) {
		SignPoint signPoint = new SignPoint();
		signPoint.setId(para.getId());
		signPoint.setName(para.getName());
		// TODO 最后更新人ID写死,后期需要更改
		signPoint.setLastModifiedBy(1L);
		signPoint.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		signPoint.setModProId(1L);
		signPointDao.updateByPrimaryKeySelective(signPoint);

		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdArr = StringUtils.split(para.getDeptIds(), ",");
			// 更新考勤点和部门关系
			signPointDeptService.updateDept(signPoint.getId(), deptIdArr);
		}

		if (StringUtils.isNotEmpty(para.getDeviceIds())) {
			String[] deviceIdArr = StringUtils.split(para.getDeviceIds(), ",");
			// 更新未绑定考勤点的考勤机的数据
			deviceService.updateDevice(signPoint.getId(), deviceIdArr);
		}

	}

	@Override
	public JsonResult delete(String ids) {
		JsonResult result = new JsonResult();

		// 数据处理
		String[] idArr = StringUtils.split(ids, ",");

		// 删除之前需要查询
		for (String id : idArr) {
			Long signPointId = Long.valueOf(id);

			// 根据考勤点ID查询设备
			List<Device> deviceList = deviceService.findDevice(signPointId);
			// 根据考勤点ID查询部门
			List<SignPointDept> signPointDeptList = signPointDeptService.findDept(signPointId);
			// TODO 提示优化,哪个考勤点存在不可删除的原因
			if ((deviceList.size() < 1 || null == deviceList)
					&& (signPointDeptList.size() < 1 || null == signPointDeptList)) {

				// 此处为逻辑删除,更新删除字段为1
				signPointDao.delete(signPointId);
			} else {
				result.setCode(HttpCode.FORBIDDEN);
				result.setMsg("考勤点存在部门或绑定设备,不可删除!");
				return result;
			}

		}

		return result;
	}

	@Override
	public List<SimpleSignPointVo> findSimpleList(KeywordPara para) {
		return signPointDao.findSimpleList(para);
	}

}
