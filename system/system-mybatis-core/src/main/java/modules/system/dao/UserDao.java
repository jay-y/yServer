package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.User;
import org.apache.ibatis.annotations.Param;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

/**
 * Description: UserDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
@MyBatisMapper
public interface UserDao extends SysBaseDao<User> {
    User findByUserName(@Param(value = "username") String username);
}