package org.yserver.core.jpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Description: JpaDaoFactory.<br>
 * Date: 2016/9/7 23:53<br>
 * Author: ysj
 */
public class JpaDaoFactory<T extends JpaBaseEntity, ID extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public JpaDaoFactory(EntityManager entityManager) {
        super(entityManager);
        Assert.notNull(entityManager);
        this.entityManager = entityManager;

    }

    protected Object getTargetRepository(RepositoryInformation information) {
        Class<?> repositoryInterface = information.getRepositoryInterface();
        if (isBaseRepository(repositoryInterface)) {
            JpaEntityInformation<T, ID> entityInformation = getEntityInformation((Class<T>) information
                    .getDomainType());
            return new JpaBaseDaoImpl<>(entityInformation, entityManager);
        }
        return super.getTargetRepository(information);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata information) {
        if (isBaseRepository(information.getRepositoryInterface())) {
            return JpaBaseDaoImpl.class;
        }
        return super.getRepositoryBaseClass(information);
    }

    private boolean isBaseRepository(Class<?> repositoryInterface) {
        return JpaBaseDao.class.isAssignableFrom(repositoryInterface);
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key, EvaluationContextProvider evaluationContextProvider) {
        return super.getQueryLookupStrategy(key, evaluationContextProvider);
    }
}
