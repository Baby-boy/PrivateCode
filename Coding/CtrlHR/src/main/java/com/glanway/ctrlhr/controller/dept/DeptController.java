package com.glanway.ctrlhr.controller.dept;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleCapDeptVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.entity.vo.TreeDeptVo;
import com.glanway.ctrlhr.service.dept.DeptService;
import com.glanway.ctrlhr.util.StringUtil;
import com.google.common.collect.Maps;

/**
 * 说明 : 部门管理
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月26日 上午2:40:32
 */
@Controller
@RequestMapping("api/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 说明 : 考勤设备下的部门
     *
     * @param sn
     * @return
     * @author 高伟南
     * @dateTime 2017年4月26日 上午2:40:51
     */
    @ResponseBody
    @RequestMapping(value = "simpleList", method = RequestMethod.GET)
    public JsonResult simpleList(String sn) {
        JsonResult jsonResult = new JsonResult();
        try {
            List<SimpleDeptVo> simpleList = deptService.findsimpleList(sn);
            jsonResult.setData(simpleList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg("操作失败");
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        }

        return jsonResult;

    }

    /**
     * 说明 : 获得部门层级树
     *
     * @param kw 部门名称或者代码模糊筛选
     * @param isParent 过滤是否存在上级部门条件 只要不为null 条件and parentId is null
     * @return
     * @author 王晨
     * @dateTime 2017年5月16日 下午10:50:50
     */
    @ResponseBody
    @RequestMapping(value = "getDeptTree", method = RequestMethod.GET)
    public JsonResult getDeptTree(@RequestParam(required = false, defaultValue = "") String kw) {
        JsonResult jsonResult = new JsonResult();
        try {

            if (StringUtil.notEmpty(kw)) {
                Map<String, Object> parMap = Maps.newHashMap();
                parMap.put("kw", kw);
                List<Dept> listDept = new ArrayList<>();
                listDept.add(deptService.getDeptTreeByPar(parMap));
                jsonResult.setData(listDept);
            } else {
                jsonResult.setData(deptService.getDeptTree(null));
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg("操作失败");
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        }
        return jsonResult;
    }

    /**
     * 说明 : 获得下级部门
     *
     * @return
     * @author 王晨
     * @dateTime 2017年5月16日 下午10:50:50
     */
    @ResponseBody
    @RequestMapping(value = "getNotParentDept", method = RequestMethod.GET)
    public JsonResult getNotParentDept() {
        JsonResult jsonResult = new JsonResult();
        try {
            Map<String, Object> parMap = Maps.newHashMap();
            parMap.put("notExistsParent", true);
            List<Dept> simpleList = deptService.getDeptByPar(parMap);
            jsonResult.setData(simpleList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg("操作失败");
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        }
        return jsonResult;
    }

    /**
     * 说明 : 获得部门
     *
     * @return
     * @author 王晨
     * @dateTime 2017年5月16日 下午10:50:50
     */
    @ResponseBody
    @RequestMapping(value = "getDept", method = RequestMethod.GET)
    public JsonResult getDept(@RequestParam(required = false, defaultValue = "") String id) {
        JsonResult jsonResult = new JsonResult();
        try {
            Map<String, Object> parMap = Maps.newHashMap();
            parMap.put("id", id);
            Dept dept = deptService.getDept(parMap);
            jsonResult.setData(dept);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg("操作失败");
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        }
        return jsonResult;
    }

    /**
     * 说明 : 添加部门
     *
     * @return
     * @author 王晨
     * @dateTime 2017年5月16日 下午10:50:50
     */
    @ResponseBody
    @RequestMapping(value = "addOrUpdateDept")
    public JsonResult addOrUpdateDept(Dept dept, @RequestParam(required = false, defaultValue = "") String childDeptIds,
                                      @RequestParam(required = false, defaultValue = "") String jobTypeIds

    ) {
        JsonResult jsonResult = new JsonResult();
        try {
            deptService.addOrUpdateDept(dept, childDeptIds, jobTypeIds);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg("操作失败");
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        }
        return jsonResult;
    }

    /**
     * 说明 : 删除
     *
     * @return
     * @author 王晨
     * @dateTime 2017年5月16日 下午10:50:50
     */
    @ResponseBody
    @RequestMapping(value = "delDept")
    public JsonResult delDept(@RequestParam(required = false, defaultValue = "") String ids) {
        JsonResult jsonResult = new JsonResult();
        try {
            deptService.batchDelDept(ids);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg("操作失败");
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping(value = "simpleCapList", method = RequestMethod.GET)
    public JsonResult simpleCapList(Long companyId, Long parentId) {
        JsonResult jsonResult = new JsonResult();
        if (companyId == null) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            return jsonResult;
        }

        if (parentId == null) {
            parentId = 0l;
        }

        try {
            List<SimpleCapDeptVo> list = deptService.deptCap(companyId, parentId);
            jsonResult.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping(value = "treeDept", method = RequestMethod.GET)
    public JsonResult findTreeDept(Long companyId, Long parentId) {
        JsonResult jsonResult = new JsonResult();
        if (companyId == null) {
            jsonResult.setCode(HttpCode.BAD_REQUEST);
            return jsonResult;
        }

        if (parentId == null) {
            parentId = 0l;
        }

        try {
            List<TreeDeptVo> list = deptService.findTreeDept(companyId, parentId);
            jsonResult.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }

    /**
     * 获取所有部门的下拉列表.
     *
     * @author fuqihao
     * @return
     * @since 1.0-20170527
     */
    @ResponseBody
    @RequestMapping(value = "dropDownList", method = RequestMethod.GET)
    public JsonResult dropDownList() {
        JsonResult jsonResult = new JsonResult();

        try {
            List<SimpleDeptVo> list = deptService.findDropDownList();
            jsonResult.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
            jsonResult.setMsg("操作失败!");
        }
        return jsonResult;
    }
}
