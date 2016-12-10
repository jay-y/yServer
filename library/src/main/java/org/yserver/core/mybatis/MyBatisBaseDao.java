package org.yserver.core.mybatis;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public interface MyBatisBaseDao<T> {

    /**
     * 获取SqlSessionTemplate
     *
     * @return
     */
    SqlSessionTemplate getSqlSessionTemplate();

    /**
     * 获取映射名
     *
     * @return
     */
    String getMapperName();

    /**
     * 新增对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Throwable
     */
    int insert(String str, T obj) throws Throwable;

    /**
     * 修改对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Throwable
     */
    int update(String str, T obj) throws Throwable;

    /**
     * 删除对象
     *
     * @param str
     * @return
     * @throws Throwable
     */
    int delete(String str) throws Throwable;

    /**
     * 删除对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Throwable
     */
    int delete(String str, T obj) throws Throwable;

    /**
     * 批量删除
     *
     * @param str
     * @param objs
     * @throws Throwable
     */
    int delete(String str, Iterable<T> objs) throws Throwable;

    /**
     * 查找对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Throwable
     */
    T findOne(String str, T obj) throws Throwable;

    /**
     * 列表查询
     *
     * @param str
     * @return
     * @throws Throwable
     */
    List<T> findAll(String str) throws Throwable;

    /**
     * 列表查询
     *
     * @param str
     * @param obj
     * @return
     * @throws Throwable
     */
    List<T> findAll(String str, T obj) throws Throwable;

    /**
     * 查找对象封装成Map
     *
     * @param sql
     * @param obj
     * @param key
     * @param value
     * @return
     * @throws Throwable
     */
    Map findAllForMap(String sql, T obj, String key, String value) throws Throwable;

}
