package org.yserver.core.jpa;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.List;

/**
 * Description: JpaBaseService.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
public interface JpaBaseService<T extends JpaBaseEntity, ID extends Serializable, DAO extends JpaBaseDao<T, ID>> extends Remote {
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
     * @param <S>
     * @return
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    <S extends T> List<S> save(List<S> entities);

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
     * deleteAll
     */
    @CacheEvict(value = "baseCache", allEntries = true)
    void deleteAll();

    /**
     * get
     *
     * @param id
     * @return
     */
    @Cacheable(value = "baseCache", key = "#id + 'find'")
    T find(ID id);

    /**
     * findAll
     *
     * @return
     */
    List<T> findAll();

    /**
     * findAll(by ids)
     *
     * @param ids
     * @return
     */
    List<T> findAll(List<ID> ids);

    /**
     * findAll
     *
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * findAll(by json params)
     *
     * @param jsonParams
     * @param pageable
     * @return
     */
    Page<T> findAll(String jsonParams, Pageable pageable);
}
