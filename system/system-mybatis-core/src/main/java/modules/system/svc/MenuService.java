package modules.system.svc;

import modules.system.dao.MenuDao;
import modules.system.model.Menu;
import modules.system.svc.base.SysBaseService;

import java.util.List;

/**
 * Description: MenuService.<br>
 * Date: 2016/9/18 15:28<br>
 * Author: ysj
 */
public interface MenuService extends SysBaseService<Menu, MenuDao>
{
    Menu find(String id);

    List<Menu> findAllParent();

    List<Menu> findAllChild(String pcode);
}
