package org.yserver.core.mybatis;

import org.yserver.core.model.BaseEntity;

import java.util.List;
import java.util.Map;

public abstract class MyBatisBaseDaoImpl<T extends BaseEntity> implements MyBatisBaseDao<T> {

    public int insert(String str, T obj) throws Throwable {
        return getSqlSessionTemplate().insert(str, obj);
    }

    public int update(String str, T obj) throws Throwable {
        return getSqlSessionTemplate().update(str, obj);
    }

    public int delete(String str) throws Throwable {
        return getSqlSessionTemplate().delete(str);
    }

    public int delete(String str, T obj) throws Throwable {
        return getSqlSessionTemplate().delete(str, obj);
    }

    public int delete(String str, Iterable<T> objs) throws Throwable {
        return getSqlSessionTemplate().delete(str, objs);
    }

    public T findOne(String str, T obj) throws Throwable {
        return getSqlSessionTemplate().selectOne(str, obj);
    }

    public List<T> findAll(String str) throws Throwable {
        return getSqlSessionTemplate().selectList(str);
    }

    public List<T> findAll(String str, T obj) throws Throwable {
        return getSqlSessionTemplate().selectList(str, obj);
    }

    public Map findAllForMap(String str, T obj, String key, String value) throws Throwable {
        return getSqlSessionTemplate().selectMap(str, obj, key);
    }
}


