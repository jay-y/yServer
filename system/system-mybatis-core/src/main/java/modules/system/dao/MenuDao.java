package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Menu;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

/**
 * Description: MenuDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
@MyBatisMapper
public interface MenuDao extends SysBaseDao<Menu> {
}
