package com.glanway.ctrlhr.service.dept;

import java.util.List;
import java.util.Map;

import com.glanway.ctrlhr.entity.dept.ComVO;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleCapDeptVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.entity.vo.TreeDeptVo;
import com.glanway.ctrlhr.service.BaseService;

public interface DeptService extends BaseService<Dept> {

    public List<SimpleDeptVo> findsimpleList(String sn);

    public List<Dept> getDeptTree(Map<String, Object> parMap);

    public Dept getDeptTreeByPar(Map<String, Object> parMap);

    public void addOrUpdateDept(Dept dept, String childDeptIds, String jobTypeIds);

    public void batchDelDept(String ids);

    public Dept getDept(Map<String, Object> parMap);

    public List<Dept> getDeptByPar(Map<String, Object> parMap);

    public ComVO getCompanys(String companyIds);

    public List<SimpleCapDeptVo> deptCap(Long companyId, Long parentId);

    public List<TreeDeptVo> findTreeDept(Long companyId, Long parentId);

    /**
     * 获取所有部门的下拉列表.
     *
     * @author fuqihao
     * @return
     * @since 1.0-20170527
     */
    public List<SimpleDeptVo> findDropDownList();
}
