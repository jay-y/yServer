package modules.template.dao.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.yserver.core.jpa.JpaBaseDao;

import java.io.Serializable;

/**
 * Description: TmpBaseDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
@NoRepositoryBean
public interface TmpBaseDao<T> extends JpaBaseDao<T, Serializable> {
}
