package com.glanway.hr.app.service;

import org.ponly.webbase.service.CrudService;

/**
 * 每个项目都有的 Base , 都应该继承, 方便后续增加逻辑
 *
 * @author yangchanghe
 */
public interface BaseService<E> extends CrudService<E, Long> {

}
