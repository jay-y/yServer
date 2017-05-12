package modules.system.svc.base;

import modules.system.dao.base.SysBaseDao;
import org.yserver.core.model.BaseEntity;
import org.yserver.core.mybatis.MyBatisBaseService;

public interface SysBaseService<T extends BaseEntity, DAO extends SysBaseDao<T>> extends MyBatisBaseService<T, DAO>
{
}
