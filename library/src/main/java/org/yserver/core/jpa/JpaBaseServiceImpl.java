package org.yserver.core.jpa;

import java.io.Serializable;
import java.util.List;

public abstract class JpaBaseServiceImpl<T extends JpaBaseEntity, ID extends Serializable, DAO extends JpaBaseDao<T, ID>> implements
        JpaBaseService<T, ID, DAO> {

    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteList(List<T> list) {
        getDao().delete(list);
    }

    @Override
    public void deleteAll() {
        getDao().deleteAllInBatch();
    }

    @Override
    public T find(ID id) {
        return getDao().findOne(id);
    }

    @Override
    public List<T> findList(List<ID> ids) {
        return getDao().findAll(ids);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }
}
