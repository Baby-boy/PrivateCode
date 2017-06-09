package com.glanway.ctrlhr.dao.dept;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.job.JobType;
import com.glanway.ctrlhr.entity.vo.DeptOrgTreeVO;
import com.glanway.ctrlhr.entity.vo.SimpleCapDeptVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.entity.vo.TreeDeptVo;

public interface DeptDao extends BaseDao<Dept> {

    public int deleteByPrimaryKey(Long id);

    public int insert(Dept record);

    public int insertSelective(Dept record);

    public Dept selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(Dept record);

    public int updateByPrimaryKey(Dept record);

    public List<SimpleDeptVo> findsimpleList(String sn);

    public List<SimpleDeptVo> findDeptList(@Param("sn") String sn);

    public List<Dept> findAllDept(Map<String, Object> parMap);

    public void updateDeptParent(Map<String, Object> parMap);

    public void addDeptJobType(Map<String, Object> parMap);

    public void delJobType(Map<String, Object> parMap);

    public void delJob(long id);

    public List<Dept> getDeptByPar(Map<String, Object> parMap);

    public List<JobType> getJobTypesByDeptId(long id);

    public List<SimpleCapDeptVo> findByCompanyAndParent(@Param("companyId") Long companyId,
                                                        @Param("parentId") Long parentId);

    public List<TreeDeptVo> findTreeDept(@Param("companyId") Long companyId, @Param("parentId") Long parentId);

    public List<DeptOrgTreeVO> getDeptRetDeptOrgTreeVO(Map<String, Object> parMap);

    /** 获取所有部门的下拉列表 */
    public List<SimpleDeptVo> findDropDownList();
}