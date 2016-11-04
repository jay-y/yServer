package modules.template.svc.base;

import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.core.jpa.JpaBaseDao;
import org.yserver.core.model.BaseEntity;
import org.yserver.core.jpa.JpaBaseServiceImpl;

import java.io.Serializable;

/**
 * Description: SysServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@DataSource("default")
@Transactional
public abstract class TmpBaseServiceImpl<T extends BaseEntity, DAO extends JpaBaseDao<T, Serializable>>
        extends JpaBaseServiceImpl<T, Serializable, DAO> implements TmpBaseService<T, DAO> {
}
