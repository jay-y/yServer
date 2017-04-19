package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleDao extends SysBaseDao<Role> {
    @Query("select o from Role o where o.name = :name and o.enname = :enname")
    Role findByNameAndEnname(@Param("name") String name, @Param("enname") String enname);
}
