package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Description: MenuDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
public interface MenuDao extends SysBaseDao<Menu>
{

    @Query("select o from Menu o where o.type = 0")
    List<Menu> findAllParent();

    @Query("select o from Menu o where o.type != 0 and o.parent.code = :pcode")
    List<Menu> findAllChild(@Param("pcode") String pcode);
}
