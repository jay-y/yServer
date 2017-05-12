package modules.system.svc.base;

import modules.system.dao.base.SysBaseDao;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.core.model.BaseEntity;
import org.yserver.core.mybatis.MyBatisBaseServiceImpl;

/**
 * Description: SysMyBatisBaseServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@DataSource("default")
@Transactional
public abstract class SysBaseServiceImpl<T extends BaseEntity, DAO extends SysBaseDao<T>> extends MyBatisBaseServiceImpl<T, DAO> implements SysBaseService<T, DAO>
{
}
