package org.yserver.core.mybatis;

import org.yserver.y;

import java.util.List;

/**
 * ClassName: BaseService <br>
 * Reason: BaseService. <br>
 * date: 2016年7月26日 下午1:40:44 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public abstract class MyBatisBaseServiceImpl<T,DAO extends MyBatisBaseDao> implements MyBatisBaseService<T,DAO> {

    /**
     * insert:(新增). <br>
     *
     * @param entity
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:49:21 <br>
     */
    public void insert(T entity) {
        try {
            getDao().insert(getDao().getMapperName() + MyBatisDaoConstant.METHOD_INSERT, entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    /**
     * update:(修改). <br>
     *
     * @param entity
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:49:57 <br>
     */
    public void update(T entity) {
        try {
            getDao().update(getDao().getMapperName() + MyBatisDaoConstant.METHOD_UPDATE, entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    /**
     * delete:(删除). <br>
     *
     * @param entity
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:49:44 <br>
     */
    public void delete(T entity) {
        try {
            getDao().delete(getDao().getMapperName() + MyBatisDaoConstant.METHOD_DELETE, entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    public void deleteList(List<T> list) {
        try {
            getDao().batchDelete(getDao().getMapperName() + MyBatisDaoConstant.METHOD_DELETE_LIST, list);
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    public void deleteAll() {
        try {
            getDao().delete(getDao().getMapperName() + MyBatisDaoConstant.METHOD_DELETE_ALL, new Object());
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
    }

    /**
     * get:(获取). <br>
     *
     * @param entity
     * @return
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:41 <br>
     */
    public T find(T entity) {
        try {
            return (T) getDao().findForObject(getDao().getMapperName() + MyBatisDaoConstant.METHOD_FIND, entity);
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
    public List<T> findList(T entity) {
        try {
            return (List<T>) getDao().findForList(getDao().getMapperName() + MyBatisDaoConstant.METHOD_FIND_LIST, entity);
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
            return (List<T>) getDao().findForList(getDao().getMapperName() + MyBatisDaoConstant.METHOD_FIND_ALL, new Object());
        } catch (Throwable e) {
            y.log().error(e.getMessage(), e);
        }
        return null;
    }
}
