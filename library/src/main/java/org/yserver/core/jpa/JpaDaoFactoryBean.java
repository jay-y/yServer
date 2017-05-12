package org.yserver.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Description: JpaDaoFactoryBean.<br>
 * Date: 2016/9/8 0:03<br>
 * Author: ysj
 */
public class JpaDaoFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID>
{

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager)
    {
        return new JpaDaoFactory(entityManager);
    }
}
