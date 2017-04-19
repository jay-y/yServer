package modules.system.svc;

import modules.system.dao.UserDao;
import modules.system.model.User;
import modules.system.svc.base.SysBaseService;

/**
 * Description: UserService.<br>
 * Date: 2016/9/18 15:28<br>
 * Author: ysj
 */
public interface UserService extends SysBaseService<User, UserDao> {
    User findByUserName(String username);
}
