package org.yserver.core.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class JpaBaseServiceImpl<T extends BaseEntity, ID extends Serializable, DAO extends JpaBaseDao<T, ID>> implements
        JpaBaseService<T, ID, DAO> {

    public T save(T entity) {
        return getDao().save(entity);
    }

    public <S extends T> List<S> save(List<S> entities) {
        return getDao().save(entities);
    }

    public void delete(T entity) {
        getDao().delete(entity);
    }

    public void deleteList(List<T> list) {
        getDao().delete(list);
    }

    public void deleteAll() {
        getDao().deleteAllInBatch();
    }

    public T find(ID id) {
        return getDao().findOne(id);
    }

    public List<T> findAll(List<ID> ids) {
        return getDao().findAll(ids);
    }

    public List<T> findAll() {
        return getDao().findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return getDao().findAll(pageable);
    }

    public Page<T> findAll(String jsonParams, Pageable pageable) {
        return getDao().findAll(jsonParams, pageable);
    }
}
