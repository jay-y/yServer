package modules.system.svc.impl;

import modules.system.dao.MenuDao;
import modules.system.model.Menu;
import modules.system.svc.MenuService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.y;

import java.util.List;

/**
 * Description: MenuServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@Service
@DataSource("default")
@Transactional
public class MenuServiceImpl extends SysBaseServiceImpl<Menu, MenuDao> implements MenuService
{
    @Autowired
    private MenuDao dao;

    @Override
    public MenuDao getDao()
    {
        return dao;
    }

    @Override
    public Menu find(String id)
    {
        return getDao().findOne(new Menu(id));
    }

    @Override
    public List<Menu> findAllParent()
    {
        Menu entity = new Menu();
        entity.setType("0");
        try
        {
            return (List<Menu>) getDao().findAll(entity);
        }
        catch (Throwable e)
        {
            y.log().error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Menu> findAllChild(String pcode)
    {
        Menu parent = new Menu();
        Menu entity = new Menu();
        parent.setCode(pcode);
        entity.setParent(parent);
        try
        {
            return (List<Menu>) getDao().findAll(entity);
        }
        catch (Throwable e)
        {
            y.log().error(e.getMessage());
        }
        return null;
    }
}
