package com.mybatis.dao;

import com.template.model.Template;
import org.mybatis.spring.SqlSessionTemplate;
import org.yserver.core.mybatis.MyBatisBaseDaoImpl;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/9.
 */
public abstract class BaseTemplateDao extends MyBatisBaseDaoImpl<Template> {
    private static final String DATA_SOURCE = "default";

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;

    public String getDataSource() {
        return DATA_SOURCE;
    }

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }
}
