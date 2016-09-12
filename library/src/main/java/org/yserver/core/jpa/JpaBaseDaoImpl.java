package org.yserver.core.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yserver.utils.exception.SystemException;
import org.yserver.utils.Log;
import org.yserver.utils.MessagesUtil;
import org.yserver.y;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@NoRepositoryBean
public class JpaBaseDaoImpl<T, ID extends Serializable> extends
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

    /**
     * 构建查询字段
     * @param jsonFlds
     * @return
     * @throws SystemException
     */
    private String buildFlds(String jsonFlds) {
        if (StringUtils.isEmpty(jsonFlds)) {// 必须指定查询字段
            String errMSg = MessagesUtil
                    .getValue("common.error.empty.search.fields");
            throw new SystemException(errMSg);
        }
        String[] flds;
        try {
            flds = y.json().jsonToObject(jsonFlds, String[].class);
            StringBuilder frgmt = new StringBuilder("");
            for (int i = 0; i < flds.length; i++) {
                if (i > 0) {
                    frgmt.append(",");
                }
                // 替换字段中的“_”为“.”
                String strFld = flds[i].indexOf("_") >= 0 ? StringUtils
                        .replace(flds[i], "_", ".") : flds[i];
                String strAs = flds[i];

                frgmt.append("obj.");
                frgmt.append(strFld);
                frgmt.append(" AS ");
                frgmt.append(strAs);
            }
            return frgmt.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new SystemException(sysErrMsg, e);
        }
    }

    /**
     * 构建查询条件
     * @param jsonParams
     * @return
     * @throws SystemException
     */
    private String buildCond(String jsonParams) throws SystemException {
        try {
            StringBuilder frgmt = new StringBuilder();
            if (StringUtils.isNotEmpty(jsonParams)) {// 拼接查询条件
                Map<String, Object> map = y.json().jsonToMap(jsonParams);
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    String val = String.valueOf(map.get(key));
                    frgmt.append(" AND ");
                    frgmt.append(key);
                    frgmt.append("='");
                    frgmt.append(val);
                    frgmt.append("'");
                }
            }
            return frgmt.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new SystemException(sysErrMsg, e);
        }
    }

    /**
     * 构建排序字段
     * @param jsonSort
     * @return
     * @throws SystemException
     */
    private String buildSort(String jsonSort) throws SystemException {
        try {
            StringBuilder frgmt = new StringBuilder();
            if (StringUtils.isNotEmpty(jsonSort)) {
                frgmt.append(" ORDER BY ");
                Map<String, Object> map = y.json().jsonToMap(jsonSort);
                Set<String> keys = map.keySet();
                boolean flag = false;
                for (String key : keys) {
                    String val = String.valueOf(map.get(key));

                    if (flag) {
                        frgmt.append(",");
                    }

                    frgmt.append(key);
                    frgmt.append(" ");
                    frgmt.append(val);
                    flag = true;
                }
            }
            return frgmt.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new SystemException(sysErrMsg, e);
        }
    }

    /**
     * 构建连接查询
     * @param jsonParams
     * @return
     */
    private Specification<T> buildSpecification(final String jsonParams) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                if (StringUtils.isEmpty(jsonParams)) {
                    return null;
                }
                List<Predicate> predicates = new ArrayList<>();

                try {
                    Map<String, Object> map = y.json().jsonToMap(jsonParams);
                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        String val = String.valueOf(map.get(key));
                        if (key.indexOf(".") > 0
                                && !StringUtils.substringBefore(key, ".")
                                .equals(idName)) {// 简单连接查询，当查询条件中包含父对象属性时
                            String[] keyArr = key.split("\\.");

                            Join<Object, Object> join = root.join(keyArr[0]);
                            if (keyArr.length > 2) {
                                for (int i = 1; i < keyArr.length - 1; i++) {// 遍历除第一个和最后一个以外的中间元素
                                    join = join.join(keyArr[i]);
                                }
                            }
                            Path<Object> path = join
                                    .get(keyArr[keyArr.length - 1]);
                            predicates.add(cb.equal(path, val));
                        } else {
                            predicates
                                    .add(cb.equal(root.<String>get(key), val));
                        }
                    }

                    if (predicates.size() > 0) {
                        return cb.and(predicates
                                .toArray(new Predicate[predicates.size()]));
                    }
                    return cb.conjunction();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                    throw new SystemException(sysErrMsg, e);
                }
            }
        };
    }
}
