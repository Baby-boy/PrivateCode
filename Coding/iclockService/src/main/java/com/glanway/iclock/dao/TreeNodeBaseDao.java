package com.glanway.iclock.dao;

import org.ponly.webbase.service.support.CrudServiceImpl;

import java.util.Map;

/**
 */
public interface TreeNodeBaseDao<E> extends BaseDao<E> {
    String OLD_PATH_PROP = "_oldPath";      // 之前路径
    String NEW_PATH_PROP = "_newPath";      // 新路径
    String LEVEL_ICREMENT_PROP = "_levelIncrement"; // 层级增量


    void updateDescendantPathAndDepthFor(Map<String, Object> paramsMap);

}
