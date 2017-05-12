package org.yserver.core.jpa;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yserver.core.model.BaseEntity;
import org.yserver.utils.Log;
import org.yserver.utils.MessagesUtil;
import org.yserver.utils.StringUtils;
import org.yserver.utils.ex.SystemException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@NoRepositoryBean
public class JpaBaseDaoImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaBaseDao<T, ID>
{
    private static final Log LOG = Log.getLogger(JpaBaseDaoImpl.class);
    @PersistenceContext
    private final EntityManager em;
    private final JpaEntityInformation<T, ID> entityInformation;
    private String sysErrMsg = MessagesUtil.getValue("common.error.runtime.system");
    // private Class<M> entityClass;
    private String entityName;
//    private String idName;

    public JpaBaseDaoImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager)
    {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        // this.entityClass = this.entityInformation.getJavaType();
        this.entityName = this.entityInformation.getEntityName();
//        this.idName = this.entityInformation.getIdAttributeNames().iterator().next();
        this.em = entityManager;
    }

    public EntityManager getEntityManager()
    {
        return this.em;
    }

    public <S extends T> S save(S entity)
    {
        if (StringUtils.isNotBlank(entity.getCode()))
        {
            entity.setUpdatedTime(new Date());
            getEntityManager().clear();//清理缓存
        }
        else
        {
            entity.setCreatedTime(new Date());
        }
        return super.save(entity);
    }

    @Override
    public List<T> findAll(String jsonParams)
    {
        String cond = buildCond(jsonParams);
        String jpql = "SELECT obj FROM " + this.entityName + " obj WHERE 1=1 " + cond;
        Query query = getEntityManager().createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public List<T> findAll(String jsonParams, Sort sort)
    {
        String condSql = buildCond(jsonParams);
        String sortSql = (null != sort ? buildSort(sort) : "");
        String jpql = "SELECT obj FROM " + this.entityName + " obj WHERE 1=1 " + condSql + sortSql;
        Query query = getEntityManager().createQuery(jpql);
        return query.getResultList();
    }

    public Page<T> findAll(String jsonParams, Pageable pageable)
    {
        String condSql = buildCond(jsonParams);
        String sortSql = (null != pageable.getSort() ? buildSort(pageable.getSort()) : "");
        String jpql = "SELECT obj FROM " + this.entityName + " obj WHERE 1=1 " + condSql + sortSql;
        Query query = getEntityManager().createQuery(jpql);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl(query.getResultList(), pageable, getCount(condSql));
    }

    private String buildCond(String jsonParams) throws SystemException
    {
        if (StringUtils.isEmpty(jsonParams))
        {
            return "";
        }
        try
        {
            StringBuilder frgmt = new StringBuilder();
            if (StringUtils.isNotEmpty(jsonParams))
            {// 拼接查询条件
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonParams);
                Iterator<String> iter = rootNode.fieldNames();
                while (iter.hasNext())
                {
                    String key = iter.next();
                    String val = rootNode.get(key).asText();

                    frgmt.append(" AND ");
                    frgmt.append(key);
                    frgmt.append(" like '%");
                    frgmt.append(val);
                    frgmt.append("%'");
                }
            }
            return frgmt.toString();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new SystemException(sysErrMsg, e);
        }
    }

    private String buildSort(Sort sort) throws SystemException
    {
        if (null == sort)
        {
            return "";
        }
        try
        {
            StringBuilder frgmt = new StringBuilder();
            frgmt.append(" ORDER BY ");
            Iterator<Sort.Order> iter = sort.iterator();
            boolean flag = false;
            while (iter.hasNext())
            {
                Sort.Order order = iter.next();
                String key = order.getProperty();
                String val = order.getDirection().name();
                if (flag)
                {
                    frgmt.append(",");
                }
                frgmt.append(key);
                frgmt.append(" ");
                frgmt.append(val);
                flag = true;
            }
            return frgmt.toString();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new SystemException(sysErrMsg, e);
        }
    }

    private long getCount(String wheres)
    {
        String sql = this.getCountQueryString(wheres);
        return this.getEntityManager().createQuery(sql, Long.class).getSingleResult().longValue();
    }

    private String getCountQueryString(String wheres)
    {
        String jpql = "select count(*) from " + this.entityName + " obj where 1=1 ";
        if (StringUtils.isNotBlank(wheres))
        {
            jpql += wheres;
        }
        return jpql;
    }
}
