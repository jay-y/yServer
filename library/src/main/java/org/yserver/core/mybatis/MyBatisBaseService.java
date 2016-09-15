package org.yserver.core.mybatis;

import java.util.List;

/**
 * Description: ServiceApi API.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
public interface MyBatisBaseService<T,DAO extends MyBatisBaseDao> {

    DAO getDao();

    /**
     * insert
     *
     * @param entity
     */
    void insert(T entity);

    /**
     * delete
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * deleteList
     *
     * @param list
     */
    void deleteList(List<T> list);

    /**
     * deleteList
     */
    void deleteAll();

    /**
     * update
     *
     * @param entity
     */
    void update(T entity);

    /**
     * get
     *
     * @param entity
     * @return
     */
    T find(T entity);

    /**
     * findList
     *
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * findAll
     *
     * @return
     */
    List<T> findAll();
}
