package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Description: UserDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
public interface UserDao extends SysBaseDao<User> {

    @Query("select o from User o where o.username = :username")
    User findByUserName(@Param("username") String username);
}
