package com.glanway.ctrlhr.service.job.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.dept.DeptDao;
import com.glanway.ctrlhr.dao.job.JobDao;
import com.glanway.ctrlhr.dao.job.JobOrgDao;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.dept.DeptJobORGVO;
import com.glanway.ctrlhr.entity.dept.ORGChildDetp;
import com.glanway.ctrlhr.entity.job.Job;
import com.glanway.ctrlhr.entity.job.JobOrg;
import com.glanway.ctrlhr.entity.job.OrgDept;
import com.glanway.ctrlhr.entity.para.JobOrgPara;
import com.glanway.ctrlhr.entity.para.JobPara;
import com.glanway.ctrlhr.entity.vo.DeptOrgTreeVO;
import com.glanway.ctrlhr.entity.vo.JobOrgVo;
import com.glanway.ctrlhr.entity.vo.JobVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.dept.DeptService;
import com.glanway.ctrlhr.service.job.JobOrgService;
import com.glanway.ctrlhr.service.job.JobService;
import com.jhlabs.image.KaleidoscopeFilter;
import org.apache.commons.lang3.StringUtils;
import net.sf.json.JSONObject;

import org.apache.commons.collections.IteratorUtils;
import org.ponly.common.json.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 说明 :
 *
 * @author 于瑞智
 * @version 1.0.0
 * @dateTime 2017年5月16日 下午9:51:18
 */
@Transactional
@Service("jobOrgService")
public class JobOrgServiceImpl extends BaseServiceImpl<JobOrg> implements JobOrgService {

	@Autowired
	private JobOrgDao jobOrgDao;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DeptDao deptDao;

	@Override
	public Page<JobOrgVo> findList(JobOrgPara para) {

		int count = jobOrgDao.findListCount(para);
		Page<JobOrgVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<JobOrgVo> list = jobOrgDao.findList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public void saveJobOrg(JobOrg jobOrg) {
		if (null == jobOrg) {
			throw new RuntimeException();
		}

		jobOrg.setId(null);
		jobOrg.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		jobOrg.setCreatedBy(1L);
		jobOrg.setCreatedDate(new Date());
		// TODO 创建程序ID写死,后期需要更改
		jobOrg.setCreProId(1L);
		// TODO 最后更新人ID写死,后期需要更改
		jobOrg.setLastModifiedBy(1L);
		jobOrg.setLastModifiedDate(jobOrg.getCreatedDate());
		// TODO 更新程序ID写死,后期需要更改
		jobOrg.setModProId(1L);

		jobOrgDao.insertSelective(jobOrg);

		if (StringUtils.isNotEmpty(jobOrg.getDeptIds())) {
			String[] deptIdList = StringUtils.split(jobOrg.getDeptIds(), ",");
			Long jobOrgId = jobOrg.getId();
			for(String deptId : deptIdList){
				OrgDept orgDept = new OrgDept();
				orgDept.setId(null);
				orgDept.setJobOrgId(jobOrgId);
				orgDept.setDeptId(Long.parseLong(deptId));
				orgDept.setBatchDate(new Date());
				// TODO 创建人ID写死,后期需要更改
				orgDept.setCreatedBy(1L);
				orgDept.setCreatedDate(new Date());
				// TODO 创建程序ID写死,后期需要更改
				orgDept.setCreProId(1L);
				// TODO 最后更新人ID写死,后期需要更改
				orgDept.setLastModifiedBy(1L);
				orgDept.setLastModifiedDate(orgDept.getCreatedDate());
				// TODO 更新程序ID写死,后期需要更改
				orgDept.setModProId(1L);
				jobOrgDao.insertOrgDept(orgDept);
			}
		}
	}

	@Override
	public JobOrgVo getInfo(Long id) {
		return jobOrgDao.findOne(id);
	}

	@Override
	public void updateJobOrg(JobOrg jobOrg) {

		// TODO 最后更新人ID写死,后期需要更改
		jobOrg.setLastModifiedBy(1L);
		jobOrg.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		jobOrg.setModProId(1L);

		jobOrgDao.updateByPrimaryKeySelective(jobOrg);

	}

	@Override
	public List<OrgDept> getChindDept(Long id) {
		return jobOrgDao.findChindDept(id);
	}

	@Override
	public List<DeptOrgTreeVO> jobOrgList(Map<String, Object> parMap) {
		
		//获得所有部门 不是树
		List<DeptOrgTreeVO> depts = deptDao.getDeptRetDeptOrgTreeVO(parMap);
		
		for(int i= 0;i<depts.size();i++){
			List<DeptOrgTreeVO> djos = new ArrayList<>();

			//根据部门 获得对应 职位层级关系
			List<DeptOrgTreeVO> retDjos = jobOrgDao.getJobOrgByDeptId(Long.parseLong(depts.get(i).getId()));
			if(null != retDjos && retDjos.size()>0){

				for(DeptOrgTreeVO retD : retDjos){
					
					List<DeptOrgTreeVO> djosInner = new ArrayList<>();
					if(null == djos || djos.size()==0){ //第一次
						
						djosInner.add(retD);
						djos.addAll(djosInner); 
					}else{
						getDjosLevleRec(djos,retD);
					}
				}
			}
			
			depts.get(i).setChild(djos);
			
			//叶子节点 加入对应子部门和编制信息
			getDjosLevleRec(djos);
		}
		
		//删除没有编制的部门	
		List<DeptOrgTreeVO> retList = new ArrayList<>();
		
		for(DeptOrgTreeVO d: depts){
			if(null != d.getChild() && d.getChild().size() > 0){
				retList.add(d);
			}
		}
		
		return retList;
	}
	
								
	private void getDjosLevleRec(List<DeptOrgTreeVO> djos,DeptOrgTreeVO nowDjo){
		if(djos.get(0).getJobGradeId().equals(nowDjo.getJobGradeId())){ //级别相同
			djos.add(nowDjo);	
		}else if(djos.get(0).getJobGradeId() < nowDjo.getJobGradeId()){ //如果小于 并且 下级存在 继续循环   下级不存在 则添加到下级
			if(null != djos.get(0).getChild()){
				getDjosLevleRec(djos.get(0).getChild(),nowDjo);
			}else{
				List<DeptOrgTreeVO> nowDjos = new ArrayList<>();
				nowDjos.add(nowDjo);
				djos.get(0).setChild(nowDjos);
			}
		}
//		else{
//			if(null == djos.get(0).getChild()){
//				getDjosLevleRec(djos,nowDjo);
//			}else{
//				getDjosLevleRec(djos.get(0).getChild(),nowDjo);
//			}
//		}
	}
	
	
	private void getDjosLevleRec(List<DeptOrgTreeVO> djos){
		for(DeptOrgTreeVO d : djos){
			if(null != d.getChild()){
				getDjosLevleRec(d.getChild());
			}else{
				List<DeptOrgTreeVO> retOCD =  jobOrgDao.getOrgDeptByOrgId(Long.parseLong(d.getOrgId()));
				d.setChild(retOCD);
			}
		}
	}
	
	@Override
	public List<JobOrg> getParentJobOrg(Long deptId, Long jobGradeId) {
		return jobOrgDao.findParentJobOrg(deptId, jobGradeId);
	}	
	
	@Override
	public List<JobOrg> getChildJobOrg(Long deptId, Long jobGradeId) {
		return jobOrgDao.findChildJobOrg(deptId, jobGradeId);
	}
	
	@Override
	public List<JobOrg> getSiblingsJobOrg(Long deptId, Long jobGradeId) {
		return jobOrgDao.findSiblingsJobOrg(deptId, jobGradeId);
	}

}
