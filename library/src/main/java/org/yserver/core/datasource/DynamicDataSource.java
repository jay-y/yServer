package org.yserver.core.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName: DynamicDataSource <br>
 * Function: 动态数据源. <br>
 * date: 2016年7月21日 上午10:59:37 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    /**
     * 自动查找datasource.
     *
     * @see AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey()
    {
        return DataSourceHolder.getDataSource();
    }
}

