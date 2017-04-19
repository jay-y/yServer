package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Logistics;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

/**
 * Description: LogisticsDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
@MyBatisMapper
public interface LogisticsDao extends SysBaseDao<Logistics> {
}
