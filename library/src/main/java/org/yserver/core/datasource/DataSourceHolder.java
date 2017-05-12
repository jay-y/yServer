package org.yserver.core.datasource;

import org.yserver.y;

/**
 * ClassName: DataSourceHolder <br>
 * Function: DataSourceHolder. <br>
 * date: 2016年7月21日 上午11:04:17 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public class DataSourceHolder
{
    // 线程本地环境
    private static final ThreadLocal<String> dataSources = new ThreadLocal<>();

    // 获取数据源
    public static String getDataSource()
    {
        y.log().debug("Thread[" + Thread.currentThread().getName() + "] get data source key[" + dataSources.get() + "]");
        return dataSources.get();
    }

    // 设置数据源
    public static void setDataSource(String customerType)
    {
        y.log().debug("Thread[" + Thread.currentThread().getName() + "] set data source key[" + customerType + "]");
        dataSources.set(customerType);
    }

    // 清除数据源
    public static void clearDataSource()
    {
        dataSources.remove();
    }
}
