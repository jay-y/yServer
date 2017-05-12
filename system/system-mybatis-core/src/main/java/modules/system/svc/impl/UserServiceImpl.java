package modules.system.svc.impl;

import modules.system.dao.UserDao;
import modules.system.model.User;
import modules.system.svc.UserService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

/**
 * Description: UserServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@Service
@DataSource("default")
@Transactional
public class UserServiceImpl extends SysBaseServiceImpl<User, UserDao> implements UserService
{
    @Autowired
    private UserDao dao;

    @Override
    public UserDao getDao()
    {
        return dao;
    }

    @Override
    public User find(String id)
    {
        return getDao().findOne(new User(id));
    }

    @Override
    public User findByUserName(String username)
    {
        return getDao().findByUserName(username);
    }
}
