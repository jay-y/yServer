package org.yserver.core.mybatis;

import org.yserver.core.model.BaseEntity;
import org.yserver.utils.StringUtils;
import org.yserver.utils.SynUtils;
import org.yserver.y;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: MyBatisBaseServiceImpl <br>
 * Reason: MyBatisBaseServiceImpl. <br>
 * date: 2016年7月26日 下午1:40:44 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public abstract class MyBatisBaseServiceImpl<T extends BaseEntity, DAO extends MyBatisBaseDao> implements MyBatisBaseService<T, DAO> {

    public T save(T entity) {
        try {
            if (StringUtils.isNotBlank(entity.getCode())) {
                entity.setUpdatedTime(new Date());
                getDao().update(getDao().getMapperName() + MyBatisDaoConstant.METHOD_UPDATE, entity);
            } else {
                //生成主键
                entity.setCode(SynUtils.get32UUID());
                entity.setCreatedTime(new Date());
                getDao().insert(getDao().getMapperName() + MyBatisDaoConstant.METHOD_INSERT, entity);
            }
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
        return entity;
    }

    public List<T> save(List<T> entities) {
        Iterator<T> iterator = entities.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            this.save(entity);
        }
        return entities;
    }

    public void delete(T entity) {
        try {
            getDao().delete(getDao().getMapperName() + MyBatisDaoConstant.METHOD_DELETE, entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    public void delete(List<T> list) {
        try {
            getDao().delete(getDao().getMapperName() + MyBatisDaoConstant.METHOD_DELETE_LIST, list);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    public void deleteAll() {
        try {
            getDao().delete(getDao().getMapperName() + MyBatisDaoConstant.METHOD_DELETE_ALL);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    /**
     * find:(获取). <br>
     *
     * @param entity
     * @return
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:41 <br>
     */
    public T find(T entity) {
        try {
            return (T) getDao().findOne(getDao().getMapperName() + MyBatisDaoConstant.METHOD_FIND, entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * list:(列表). <br>
     *
     * @param entity
     * @return
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:14 <br>
     */
    public List<T> findAll(T entity) {
        try {
            return (List<T>) getDao().findAll(getDao().getMapperName() + MyBatisDaoConstant.METHOD_FIND_LIST, entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * listAll:(列表(全部)). <br>
     *
     * @return
     * @throws Exception
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:27 <br>
     */
    public List<T> findAll() {
        try {
            return (List<T>) getDao().findAll(getDao().getMapperName() + MyBatisDaoConstant.METHOD_FIND_ALL);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
        return null;
    }
}
