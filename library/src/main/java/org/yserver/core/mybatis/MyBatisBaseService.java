package org.yserver.core.mybatis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Description: ServiceApi API.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
public interface MyBatisBaseService<T, DAO extends MyBatisBaseDao> {

    DAO getDao();

    /**
     * insert
     *
     * @param entity
     */
    @CacheEvict(value = "baseCache", key = "#entity.code + 'find'")
    void insert(T entity);

    /**
     * update
     *
     * @param entity
     */
    @CacheEvict(value = "baseCache", key = "#entity.code + 'find'")
    void update(T entity);

    /**
     * delete
     *
     * @param entity
     */
    @CacheEvict(value = "baseCache", key = "#entity.code + 'find'")
    void delete(T entity);

    /**
     * deleteList
     *
     * @param list
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    void deleteList(List<T> list);

    /**
     * deleteList
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    void deleteAll();

    /**
     * get
     *
     * @param entity
     * @return
     */
    @Cacheable(value = "baseCache", key = "#entity.code + 'find'")
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
