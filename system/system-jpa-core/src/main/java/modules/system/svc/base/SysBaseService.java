package modules.system.svc.base;

import org.yserver.core.jpa.JpaBaseDao;
import org.yserver.core.jpa.JpaBaseService;
import org.yserver.core.model.BaseEntity;

import java.io.Serializable;

public interface SysBaseService<T extends BaseEntity, DAO extends JpaBaseDao<T, Serializable>> extends JpaBaseService<T, Serializable, DAO>
{

}
