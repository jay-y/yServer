package org.yserver.core.jpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yserver.utils.Log;
import org.yserver.utils.MessagesUtil;
import org.yserver.utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;

@NoRepositoryBean
public class JpaBaseDaoImpl<T extends JpaBaseEntity, ID extends Serializable> extends
        SimpleJpaRepository<T, ID> implements JpaBaseDao<T, ID> {
    private static final Log LOGGER = Log.getLogger(JpaBaseDaoImpl.class);

    private String sysErrMsg = MessagesUtil.getValue("common.error.runtime.system");

    @PersistenceContext
    private final EntityManager em;

    private final JpaEntityInformation<T, ID> entityInformation;

    // private Class<M> entityClass;
    private String entityName;
    private String idName;

    public JpaBaseDaoImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        // this.entityClass = this.entityInformation.getJavaType();
        this.entityName = this.entityInformation.getEntityName();
        this.idName = this.entityInformation.getIdAttributeNames().iterator().next();
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public <S extends T> S save(S entity) {
        if (StringUtils.isNotBlank(entity.getCode())) {
            entity.setUpdatedTime(new Date());
            getEntityManager().clear();//清理缓存
        } else {
            entity.setCreatedTime(new Date());
        }
        return super.save(entity);
    }
}
