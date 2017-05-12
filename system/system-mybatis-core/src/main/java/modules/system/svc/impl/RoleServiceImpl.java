package modules.system.svc.impl;

import modules.system.dao.RoleDao;
import modules.system.model.Role;
import modules.system.svc.RoleService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.y;

@Service
@DataSource("default")
@Transactional
public class RoleServiceImpl extends SysBaseServiceImpl<Role, RoleDao> implements RoleService
{
    @Autowired
    private RoleDao dao;

    @Override
    public RoleDao getDao()
    {
        return dao;
    }

    @Override
    public Role find(String id)
    {
        return getDao().findOne(new Role(id));
    }

    public Role findByNameAndEnname(String name, String enname)
    {
        Role entity = new Role();
        entity.setName(name);
        entity.setEnname(enname);
        try
        {
            return getDao().findOne(entity);
        }
        catch (Throwable e)
        {
            y.log().error(e.getMessage());
        }
        return null;
    }
}
