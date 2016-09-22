package org.yserver.core.mybatis;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public interface MyBatisBaseDao<T extends Object> {

	/**
	 * 获取SqlSessionTemplate
	 * @return
	 */
	SqlSessionTemplate getSqlSessionTemplate();

	/**
	 * 获取映射名
	 * @return
	 */
	String getMapperName();
	
	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	int insert(String str, T obj) throws Throwable;

	/**
	 * 批量插入
	 * @param str
	 * @param objs
	 * @throws Throwable
	 */
	int batchInsert(String str, List<T> objs) throws Throwable;
	
	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	int update(String str, T obj) throws Throwable;

	/**
	 * 批量修改
	 * @param str
	 * @param objs
	 * @throws Throwable
	 */
	void batchUpdate(String str, List<T> objs) throws Throwable;
	
	/**
	 * 删除对象 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	int delete(String str, T obj) throws Throwable;

	/**
	 * 批量删除
	 * @param str
	 * @param objs
	 * @throws Throwable
	 */
	int batchDelete(String str, List<T> objs) throws Throwable;

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	T findForObject(String str, T obj) throws Throwable;

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	List<T> findForList(String str, T obj) throws Throwable;
	
	/**
	 * 查找对象封装成Map
	 * @param sql
	 * @param obj
	 * @param key
	 * @param value
	 * @return
	 * @throws Throwable
	 */
	Map findForMap(String sql, T obj, String key, String value) throws Throwable;
	
}
