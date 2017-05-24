package com.glanway.iclock.service;


import org.ponly.common.util.StringUtils;
import org.ponly.webbase.domain.Filters;
import org.ponly.webbase.domain.Sort;
import org.ponly.webbase.entity.Auditable;
import org.springframework.beans.factory.annotation.Autowired;

import com.glanway.iclock.dao.BaseDao;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yangchanghe
 */
public abstract class BaseServiceImpl<E> extends org.ponly.webbase.service.support.CrudServiceImpl<E, Long> implements BaseService<E> {

    private BaseDao<E> bd;

    public static final String RECORD_BEEN_USED_MSG = "Records have been used, Can't delete it";
   /* @Autowired
    private UserDao userDao;*/

    @Autowired
    public void setBaseDao(BaseDao<E> baseDao) {
        super.setCrudDao(baseDao);
        bd = baseDao;
    }

  /*  @Override
    public void save(E e) {
        if (e instanceof Auditable<?, ?>) {
            @SuppressWarnings("unchecked")
            Auditable<String, ?> a = (Auditable<String, ?>) e;
            a.setCreatedDate(new Date());
            a.setLastModifiedDate(new Date());
            User user = getCurrentAdminUserIfHas();
            if (null != user) {
                a.setCreatedBy(user.getLoginName());
                a.setLastModifiedBy(user.getLoginName());
            }
        }
        super.save(e);
    }*/

    @Override
    public void update(E e) {
        if (e instanceof Auditable<?, ?>) {
            Auditable<?, ?> a = (Auditable<?, ?>) e;
            a.setLastModifiedDate(new Date());
        }
        super.update(e);
    }

 /*   protected User getCurrentAdminUserIfHas() {
        Long id = UserUtils.getCurrentUserId();
        return null != id ? userDao.find(id) : null;
    }*/

    @Override
    protected boolean needTransformDynamicProperty() {
        // 暂时始终返回 true 以便兼容
        return true;
    }

    @Override
    protected boolean needSplitCascadeProperty() {
        // 暂时始终返回 true 以便兼容
        return true;
        //return super.needSplitCascadeProperty();
    }

    /* ***************************************************
     *         过时，但是为了兼容采用的方法
     * ***************************************************/

    /**
     * @deprecated
     */
    @Deprecated
    protected class IterateNamingTransformFilters extends Filters {
        public IterateNamingTransformFilters(Filters filters) {
            super(filters);
        }

       /* @Override
        public Iterator<Filter> iterator() {
            return new IteratorWrapper<Filter>(super.iterator()) {
                @Override
                public Filters.Filter next() {
                    Filter f = super.next();
                    return new Filter(transformDynamicProperty(f.getProperty()), f.getOperator(), f.getValues());
                }
            };
        }*/
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected String transformDynamicProperty(String property) {
        return StringUtils.camelCaseToUnderscore(property, false);
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected class IterateNamingTransformSort extends Sort {
        private static final long serialVersionUID = -8648623635266511773L;

        public IterateNamingTransformSort(Sort sort) {
            super(sort);
        }

       /* @Override
        public Iterator<Order> iterator() {
            return new IteratorWrapper<Order>(super.iterator()) {
                @Override
                public Order next() {
                    Order order = super.next();
                    return new Order(transformDynamicProperty(order.getProperty()), order.getDirection());
                }
            };
        }*/
    }
}
