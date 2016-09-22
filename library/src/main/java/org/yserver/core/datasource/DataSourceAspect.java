package org.yserver.core.datasource;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.yserver.utils.ReflectionUtil;
import org.yserver.y;

import java.lang.reflect.Method;

/**
 * ClassName: DataSourceAspect <br>
 * Function: DataSourceAspect. <br>
 * Reason: AOP. <br>
 * date: 2016年7月22日 下午7:45:39 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public class DataSourceAspect implements MethodBeforeAdvice,
        AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {
        DataSourceHolder.clearDataSource();
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        DataSource datasource = null;
        String source = null;
        Class<?> cls;
        // 这里DataSource是自定义的注解，不是java里的DataSource接口
        if (method.isAnnotationPresent(DataSource.class)) {
            datasource = method.getAnnotation(DataSource.class);
        } else if (target.getClass().isAnnotationPresent(DataSource.class)) {
            cls = target.getClass();
            datasource = cls.getAnnotation(DataSource.class);
        } else {
            cls = ReflectionUtil.getUserClass(target.getClass());
            if (cls.isAnnotationPresent(DataSource.class)) {
                datasource = cls.getAnnotation(DataSource.class);
            } else {
                y.log().warn("{} could not be loaded.", target.getClass().getName());
            }
        }
        if (null != datasource) source = datasource.value();
        DataSourceHolder.setDataSource(source);
    }
}
