package org.yserver.core.mybatis;

import org.yserver.core.model.BaseEntity;
import org.yserver.utils.Log;
import org.yserver.utils.RandomUtil;
import org.yserver.utils.StringUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: MyBatisBaseServiceImpl <br>
 * Reason: MyBatisBaseServiceImpl. <br>
 * date: 2016年7月26日 下午1:40:44 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public abstract class MyBatisBaseServiceImpl<T extends BaseEntity, DAO extends MyBatisBaseDao> implements MyBatisBaseService<T, DAO> {
    private final static Log logger = Log.getLogger(MyBatisBaseServiceImpl.class);

    public T save(T entity) {
        try {
            if (StringUtils.isNotBlank(entity.getCode())) {
                entity.setUpdatedTime(new Date());
                getDao().update(entity);
            } else {
                //生成主键
                entity.setCode(RandomUtil.uuid());
                entity.setCreatedTime(new Date());
                getDao().insert(entity);
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return entity;
    }

    public List<T> save(List<T> entities) {
        Iterator<T> iterator = entities.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            this.save(entity);
        }
        return entities;
    }

    public void delete(T entity) {
        try {
            getDao().delete(entity);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void delete(List<T> list) {
        try {
            getDao().delete(list);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void deleteAll() {
        try {
            getDao().deleteAll();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * find:(获取). <br>
     *
     * @param entity
     * @return
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:41 <br>
     */
    public T find(T entity) {
        try {
            return (T) getDao().findOne(entity);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * list:(列表). <br>
     *
     * @param entity
     * @return
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:14 <br>
     */
    public List<T> findAll(T entity) {
        try {
            return (List<T>) getDao().findAll(entity);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * listAll:(列表(全部)). <br>
     *
     * @return
     * @throws Exception
     * @author ysj
     * @since JDK 1.7
     * date: 2016年8月12日 下午5:50:27 <br>
     */
    public List<T> findAll() {
        try {
            return (List<T>) getDao().findAll();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
