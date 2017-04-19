package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Area;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

import java.util.List;

/**
 * Description: AreaDao.<br>
 * Date: 2016/9/23 16:57<br>
 * Author: ysj
 */
@MyBatisMapper
public interface AreaDao extends SysBaseDao<Area> {
    List<Area> findAllChild(Area entity);
}
