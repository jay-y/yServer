package org.yserver.core.mybatis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.yserver.core.model.Pagination;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

/**
 * Description: MyBatisBaseService.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
public interface MyBatisBaseService<T, DAO extends MyBatisBaseDao> extends Remote
{

    DAO getDao();

    /**
     * save
     *
     * @param entity
     */
    @CacheEvict(value = "baseCache", key = "#entity.code + 'find'")
    T save(T entity);

    /**
     * batch save
     *
     * @param entities
     * @return
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    List<T> save(List<T> entities);

    /**
     * delete
     *
     * @param entity
     */
    @CacheEvict(value = "baseCache", key = "#entity.code + 'find'")
    void delete(T entity);

    /**
     * batch delete
     *
     * @param list
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    void delete(List<T> list);

    /**
     * deleteList
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    void deleteAll();

    /**
     * find
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
    List<T> findAll(T entity);

    /**
     * findAll
     *
     * @return
     */
    List<T> findAll();

    /**
     * findAll
     *
     * @return
     */
    Map<String, Object> findPage(Pagination<T> pagination);
}
