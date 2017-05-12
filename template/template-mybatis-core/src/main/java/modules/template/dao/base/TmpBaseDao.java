package modules.template.dao.base;

import org.yserver.core.mybatis.MyBatisBaseDao;

import java.io.Serializable;

public interface TmpBaseDao<T extends Object> extends MyBatisBaseDao<T, Serializable>
{
}
