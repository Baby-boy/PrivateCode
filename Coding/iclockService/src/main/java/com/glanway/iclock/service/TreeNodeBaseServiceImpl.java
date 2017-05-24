package com.glanway.iclock.service;

import java.util.Map;

import org.ponly.webbase.service.support.PathTreeNodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.glanway.iclock.dao.TreeNodeBaseDao;
import com.glanway.iclock.entity.TreeNodeBaseEntity;




/**
 */
public abstract class TreeNodeBaseServiceImpl<N extends TreeNodeBaseEntity<N>> extends PathTreeNodeServiceImpl<N, Long> implements BaseService<N> {
    protected TreeNodeBaseDao<N> treeNodeBaseDao;

    @Autowired
    public void setTreeNodeBaseDao(TreeNodeBaseDao<N> baseDao) {
        this.treeNodeBaseDao = baseDao;
        super.setCrudDao(baseDao);
    }

    protected TreeNodeBaseServiceImpl(Long rootId) {
        super(rootId);
    }

    protected TreeNodeBaseServiceImpl(Long rootId, String pathSeparator) {
        super(rootId, pathSeparator);
    }

    @Override
    protected void deleteDescendantFor(N node) {
        // TODO 删除后代元素
        // super.deleteDescendantFor(node);
    }

    @Override
	protected void doInternalUpdateDescendantFor(N snapshot, N current) {
    	Map<String, Object> params = buildDescendantUpdateParams(snapshot, current);
        treeNodeBaseDao.updateDescendantPathAndDepthFor(params);
	}

    protected Map<String, Object> buildDescendantUpdateParams(N snapshot, N current) {
    	String oldPath = snapshot.getPath();
    	String newPath = current.getPath();
    	Integer oldLevel = snapshot.getDepth();
    	Integer newLevel = current.getDepth();

    	oldLevel = null != oldLevel ? oldLevel : 0;
    	newLevel = null != newLevel ? newLevel : 0;

        ParamsMap paramsMap = createParamsMap();
        paramsMap.put(TreeNodeBaseDao.OLD_PATH_PROP, oldPath + pathSeparator);
        paramsMap.put(TreeNodeBaseDao.NEW_PATH_PROP, newPath + pathSeparator);
        paramsMap.put(TreeNodeBaseDao.LEVEL_ICREMENT_PROP, newLevel - oldLevel);
        return paramsMap;
    }
}
