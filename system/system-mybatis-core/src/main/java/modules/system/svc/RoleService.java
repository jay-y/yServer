package modules.system.svc;

import modules.system.dao.RoleDao;
import modules.system.model.Role;
import modules.system.svc.base.SysBaseService;

public interface RoleService extends SysBaseService<Role, RoleDao> {
    Role find(String id);

    Role findByNameAndEnname(String name, String enname);
}
