package modules.system.svc.impl;

import modules.system.dao.MenuDao;
import modules.system.model.Menu;
import modules.system.svc.MenuService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

import java.util.List;

/**
 * Description: MenuServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@Service
@DataSource("default")
@Transactional
public class MenuServiceImpl
        extends SysBaseServiceImpl<Menu, MenuDao> implements MenuService {
    @Autowired
    private MenuDao dao;

    @Override
    public MenuDao getDao() {
        return dao;
    }

    @Override
    public List<Menu> findAllParent() {
        return getDao().findAllParent();
    }

    @Override
    public List<Menu> findAllChild(String pcode) {
        return getDao().findAllChild(pcode);
    }
}
