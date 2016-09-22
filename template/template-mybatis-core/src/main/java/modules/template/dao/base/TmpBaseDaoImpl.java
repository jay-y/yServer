package modules.template.dao.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.yserver.core.mybatis.MyBatisBaseDaoImpl;

import javax.annotation.Resource;

/**
 * Description: TmpBaseDaoImpl.<br>
 * Date: 2016/9/6 1:28<br>
 * Author: ysj
 */
public abstract class TmpBaseDaoImpl<T extends Object> extends MyBatisBaseDaoImpl<T> {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }
}