package org.yserver.core.datasource;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
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
        // 这里DataSource是自定义的注解，不是java里的DataSource接口
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource datasource = method.getAnnotation(DataSource.class);
            DataSourceHolder.setDataSource(datasource.value());
        } else if (target.getClass().isAnnotationPresent(DataSource.class)) {
            DataSource datasource = target.getClass().getAnnotation(DataSource.class);
            DataSourceHolder.setDataSource(datasource.value());
            y.log().debug(datasource.value());
        } else {
            try {
                // target是被织入增强处理的目标对象，通过获取getDataSource函数来获取target的数据源名称
                DataSourceHolder.setDataSource(target.getClass()
                        .getMethod("getDataSource").invoke(target).toString());
            } catch (NoSuchMethodException e) {
                // do nothing
                y.log().error(e.getMessage(),e);
            }
        }
    }
}
