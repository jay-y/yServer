package org.yserver.core.mybatis;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public abstract class MyBatisBaseDaoImpl<T extends Object> implements MyBatisBaseDao<T> {
    /**
     * 保存对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int insert(String str, T obj) throws Exception {
        return getSqlSessionTemplate().insert(str, obj);
    }

    /**
     * 批量更新
     *
     * @param str
     * @param objs
     * @return
     * @throws Exception
     */
    public int batchInsert(String str, List<T> objs) throws Exception {
        return getSqlSessionTemplate().insert(str, objs);
    }

    /**
     * 修改对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int update(String str, T obj) throws Exception {
        return getSqlSessionTemplate().update(str, obj);
    }

    /**
     * 批量更新
     *
     * @param str
     * @param objs
     * @return
     * @throws Exception
     */
    public void batchUpdate(String str, List<T> objs) throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionTemplate().getSqlSessionFactory();
        //批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            if (objs != null) {
                for (int i = 0, size = objs.size(); i < size; i++) {
                    sqlSession.update(str, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 批量删除
     *
     * @param str
     * @param objs
     * @return
     * @throws Exception
     */
    public int batchDelete(String str, List<T> objs) throws Exception {
        return getSqlSessionTemplate().delete(str, objs);
    }

    /**
     * 删除对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int delete(String str, T obj) throws Exception {
        return getSqlSessionTemplate().delete(str, obj);
    }

    /**
     * 查找对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public T findForObject(String str, T obj) throws Exception {
        return getSqlSessionTemplate().selectOne(str, obj);
    }

    /**
     * 查找对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public List<T> findForList(String str, T obj) throws Exception {
        return getSqlSessionTemplate().selectList(str, obj);
    }

    public Map findForMap(String str, T obj, String key, String value) throws Exception {
        return getSqlSessionTemplate().selectMap(str, obj, key);
    }
}


