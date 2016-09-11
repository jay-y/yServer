package org.yserver.core.jpa;

import java.io.Serializable;

public abstract class JpaBaseServiceImpl<T, ID extends Serializable> implements
        JpaBaseService<T, ID> {

    protected JpaBaseDao<T, ID> dao;

    public JpaBaseServiceImpl() {
        super();
    }

    public JpaBaseServiceImpl(JpaBaseDao<T, ID> JpaBaseDao) {
        this.dao = JpaBaseDao;
    }
}
