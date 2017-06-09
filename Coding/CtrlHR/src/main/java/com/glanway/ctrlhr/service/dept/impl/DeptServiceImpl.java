package com.glanway.ctrlhr.service.dept.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ponly.common.json.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.glanway.ctrlhr.dao.dept.DeptDao;
import com.glanway.ctrlhr.entity.dept.ComVO;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.job.JobType;
import com.glanway.ctrlhr.entity.vo.SimpleCapDeptVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.entity.vo.TreeDeptVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.dept.DeptService;
import com.glanway.ctrlhr.util.StringUtil;
import com.google.common.collect.Maps;

@Transactional
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<SimpleDeptVo> findsimpleList(String sn) {

        return deptDao.findDeptList(sn);
    }

    @Override
    public List<Dept> getDeptTree(Map<String, Object> parMap) {
        List<Dept> retReptList = deptDao.findAllDept(parMap);
        List<Dept> deptTree = new ArrayList<>();
        for (Dept dept : retReptList) {
            // 如果是顶层节点null 或者返回List有父节点ID但找不到也当作父节点处理(为了处理模糊搜索 父子节点都可以搜索出来)
            Boolean flag = true;
            for (Dept dept1 : retReptList) {
                if (dept1.getId().equals(dept.getParentId())) {
                    flag = false;
                }
            }
            if (null == dept.getParentId() || flag) {
                deptTree.add(dept);
                getChildDeptById(retReptList, dept);
            }
        }
        return deptTree;
    }

    private void getChildDeptById(List<Dept> retReptList, Dept dept) {
        for (Dept rDept : retReptList) {
            if (dept.getId().equals(rDept.getParentId())) {
                if (null == dept.getChildDept()) {
                    List<Dept> childDept = new ArrayList<>();
                    childDept.add(rDept);
                    dept.setChildDept(childDept);
                } else {
                    dept.getChildDept().add(rDept);
                }
                getChildDeptById(retReptList, rDept);
            }
        }
    }

    @Override
    public Dept getDeptTreeByPar(Map<String, Object> parMap) {
        List<Dept> depts = this.getDeptTree(null);
        Dept d = new Dept();
        getDeptTreeByParRec(parMap, depts, d);
        return d;
    }

    private void getDeptTreeByParRec(Map<String, Object> parMap, List<Dept> depts, Dept d) {

        Object id = parMap.get("id");// 部门ID 搜索
        Object kw = parMap.get("kw");// 关键字搜搜 搜索 包含 部门名称和编码

        for (Dept dept : depts) {
            if (
                 ( null != id && Long.parseLong(id.toString()) == dept.getId() )  ||                            // ID 搜索
                 ( null != kw && null != dept.getName() && dept.getName().indexOf(kw.toString()) != -1 )    ||  // kw 部门名称 搜索
                 ( null != kw && null != dept.getCode() && dept.getCode().indexOf(kw.toString()) != -1 )        // kw 部门代码 搜索
               ) {
                d.setId(dept.getId());
                d.setName(dept.getName());
                d.setCode(dept.getCode());
                d.setDeptJobORGVOs(dept.getDeptJobORGVOs());
                d.setJobTypes(dept.getJobTypes());
                d.setChildDept(dept.getChildDept());
            } else {
                if (null != dept.getChildDept()) {
                    getDeptTreeByParRec(parMap, dept.getChildDept(), d);
                }
            }
        }
    }

    @Transactional
    @Override
    public void addOrUpdateDept(Dept dept, String childDeptIds, String jobTypeIds) {

        // TODO 写死
        dept.setCompanyId(1L);
        dept.setCompanyName("雅玛多");

        if (null != dept.getId()) {
            dept.setLastModifiedDate(new Date());
            deptDao.updateByPrimaryKeySelective(dept);
        } else {
            dept.setCreatedDate(new Date());
            dept.setLastModifiedDate(new Date());
            deptDao.insertSelective(dept);
        }

        Map<String, Object> parMap = Maps.newHashMap();

        if (StringUtil.notEmpty(childDeptIds)) { // 传入下级部门 则修改
            parMap.put("id", dept.getId());
            parMap.put("childDeptIds", childDeptIds.split(","));
            deptDao.updateDeptParent(parMap);
        }

        if (StringUtil.notEmpty(jobTypeIds)) {// 传入职位类型 则删除以前关联关系 加入新的
            parMap.clear();
            parMap.put("deptId", dept.getId());
            deptDao.delJobType(parMap);
            for (String jobTypeId : jobTypeIds.split(",")) {
                parMap.clear();
                parMap.put("jobTypeId", jobTypeId);
                parMap.put("deptId", dept.getId());
                deptDao.addDeptJobType(parMap);
            }
        }
        
        Dept deptPath = new Dept(); //设置PATH 以及PATH_NAME
        deptPath.setId(dept.getId());
        deptPath.setPathNames("/"+dept.getName());
        deptPath.setPath("/"+dept.getId());
        getDeptPathRec(deptPath,dept.getParentId(),this.getDeptByPar(null));
        
        deptDao.updateByPrimaryKeySelective(deptPath);
        
    }
    
    private void getDeptPathRec(Dept dept, Long parentId, List<Dept> depts){
    	if(null != parentId){
    		for(Dept d : depts){
    			if(d.getId().equals(parentId)){
    				dept.setPathNames("/"+d.getName()+dept.getPathNames());
    				dept.setPath("/"+d.getId()+dept.getPath());
    				getDeptPathRec(dept, d.getParentId(), depts);
    			}
    		}
    	}
    }

    @Transactional
    @Override
    public void batchDelDept(String ids) {
        if (StringUtil.notEmpty(ids)) {
            for (String id : ids.split(",")) {
                deptDao.delJob(Long.parseLong(id));
            }
        }
    }

    @Override
    public Dept getDept(Map<String, Object> parMap) {
        Dept dept = deptDao.selectByPrimaryKey(Long.parseLong(parMap.get("id").toString()));

        Map<String, Object> newParMap = Maps.newHashMap();
        newParMap.clear();
        
        // 获得父节点
        if(null != dept.getParentId()){
        	 newParMap.put("id", dept.getParentId());
             List<Dept> retPReptList = deptDao.findAllDept(newParMap);
             if (null != retPReptList && retPReptList.size() > 0) {
                 dept.setParentDept(retPReptList.get(0));
             }
        }
       
        // 获得子节点
        newParMap.clear();
        newParMap.put("parentId", dept.getId());
        List<Dept> retDReptList = deptDao.findAllDept(newParMap);
        if (null != retDReptList && retDReptList.size() > 0) {
            dept.setChildDept(retDReptList);
        }

        // 加入对应职位类型
        List<JobType> jobTypes = deptDao.getJobTypesByDeptId(dept.getId());
        dept.setJobTypes(jobTypes);

        return dept;
    }

    @Override
    public List<Dept> getDeptByPar(Map<String, Object> parMap) {
        return deptDao.getDeptByPar(parMap);
    }

    @Override
    public ComVO getCompanys(String companyIds) {

        JsonNode jn = Jacksons.readTree(companyIds);
        ComVO com = new ComVO();
        Dept d = deptDao.selectByPrimaryKey(jn.path("id").asLong());
        com.setId(d.getId());
        com.setName(d.getName());

        if (null != jn.path("child")) {
            getCompanysRec(jn.path("child").iterator(), com);
        }

        return com;
    }

    private void getCompanysRec(Iterator<JsonNode> jn, ComVO com) {

        if (null != jn) {
            List<ComVO> listComVo = new ArrayList<>();
            while (jn.hasNext()) {
                JsonNode nextNode = jn.next();
                Dept d = deptDao.selectByPrimaryKey(nextNode.path("id").asLong());
                ComVO comRec = new ComVO();
                comRec.setId(d.getId());
                comRec.setName(d.getName());
                listComVo.add(comRec);
                getCompanysRec(nextNode.path("child").iterator(), comRec);
            }
            com.setComVO(listComVo);

        }

    }

    @Override
    public List<SimpleCapDeptVo> deptCap(Long companyId, Long parentId) {
        if (parentId == null) {
            parentId = 0l;
        }
        return deptDao.findByCompanyAndParent(companyId, parentId);
    }

    @Override
    public List<TreeDeptVo> findTreeDept(Long companyId, Long parentId) {
        if (parentId == null) {
            parentId = 0l;
        }
        return deptDao.findTreeDept(companyId, parentId);
    }

    @Override
    public List<SimpleDeptVo> findDropDownList() {
        return deptDao.findDropDownList();
    }

}
