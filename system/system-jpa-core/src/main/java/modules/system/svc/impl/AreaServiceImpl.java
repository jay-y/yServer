package modules.system.svc.impl;

import modules.system.dao.AreaDao;
import modules.system.model.Area;
import modules.system.svc.AreaService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

import java.util.List;

/**
 * Description: AreaServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@Service
@DataSource("default")
@Transactional
public class AreaServiceImpl
        extends SysBaseServiceImpl<Area, AreaDao> implements AreaService {
    @Autowired
    private AreaDao dao;

    @Override
    public AreaDao getDao() {
        return dao;
    }

    @Override
    public List<Area> findAllParent() {
        return getDao().findAllParent();
    }

    @Override
    public List<Area> findAllChild(String type, String pcode) {
        return getDao().findAllChild(type, pcode);
    }

    @Override
    public List<Area> findAllChildByTypeAndPcode(String type, String pcode) {
        return getDao().findAllChildByTypeAndPcode(type, pcode);
    }
}
