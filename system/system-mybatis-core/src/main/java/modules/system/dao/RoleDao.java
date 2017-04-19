package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Role;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

@MyBatisMapper
public interface RoleDao extends SysBaseDao<Role> {
}
