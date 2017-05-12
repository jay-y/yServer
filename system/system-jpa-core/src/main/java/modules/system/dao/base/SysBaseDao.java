package modules.system.dao.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.yserver.core.jpa.JpaBaseDao;

import java.io.Serializable;

/**
 * Description: SysJpaBaseDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
@NoRepositoryBean
public interface SysBaseDao<T> extends JpaBaseDao<T, Serializable>
{
}
