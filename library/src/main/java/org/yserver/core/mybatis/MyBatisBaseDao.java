package org.yserver.core.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MyBatisBaseDao<T, ID extends Serializable>
{
    int insert(T entity);

    int update(T entity);

    <S extends T> S findOne(S entity);

    boolean exists(ID id);

    List<T> findAll();

    List<T> findAll(T entity);

    List<T> findPage(Map<String, Object> map);

    long count();

    long count(Object obj);

    void delete(T entity);

    void deleteList(Iterable<? extends T> entities);

    void deleteAll();
}
