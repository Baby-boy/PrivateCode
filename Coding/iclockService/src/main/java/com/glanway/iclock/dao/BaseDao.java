package com.glanway.iclock.dao;

import org.ponly.webbase.dao.CrudDao;

/**
 * 每个项目都有的 Base , 都应该继承, 方便后续增加逻辑
 *
 * @author yangchanghe
 */
public interface BaseDao<E> extends CrudDao<E, Long> {
}
