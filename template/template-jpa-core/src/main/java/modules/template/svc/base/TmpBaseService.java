package modules.template.svc.base;

import org.yserver.core.jpa.JpaBaseDao;
import org.yserver.core.jpa.JpaBaseEntity;
import org.yserver.core.jpa.JpaBaseService;

import java.io.Serializable;

public interface TmpBaseService<T extends JpaBaseEntity, DAO extends JpaBaseDao<T, Serializable>> extends JpaBaseService<T, Serializable, DAO> {

}
