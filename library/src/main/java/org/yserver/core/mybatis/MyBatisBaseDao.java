package org.yserver.core.mybatis;

import java.io.Serializable;
import java.util.Collection;

public interface MyBatisBaseDao<T, ID extends Serializable> {
    <S extends T> S insert(S entity);

    <S extends T> S update(S entity);

    <S extends T> S findOne(S entity);

    boolean exists(ID id);

    Collection<T> findAll();

    Collection<T> findAll(T entity);

    long count();

    void delete(T entity);

    void deleteList(Iterable<? extends T> entities);

    void deleteAll();
}
