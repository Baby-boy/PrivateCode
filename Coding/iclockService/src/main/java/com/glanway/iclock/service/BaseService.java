package com.glanway.iclock.service;

import org.ponly.webbase.service.CrudService;

import java.util.Map;

/**
 * 每个项目都有的 Base , 都应该继承, 方便后续增加逻辑
 *
 * @author yangchanghe
 */
public interface BaseService<E> extends CrudService<E, Long> {
}
