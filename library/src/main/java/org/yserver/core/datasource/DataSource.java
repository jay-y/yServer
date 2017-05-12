package org.yserver.core.datasource;

import java.lang.annotation.*;

/**
 * ClassName: DataSource <br>
 * date: 2016年7月22日 下午7:45:23 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource
{
    String value() default "";
}
