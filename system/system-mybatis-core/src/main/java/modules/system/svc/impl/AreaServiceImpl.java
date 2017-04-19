package modules.system.svc.impl;

import modules.system.dao.AreaDao;
import modules.system.model.Area;
import modules.system.svc.AreaService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.y;

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
    public Area find(String id) {
        return getDao().findOne(new Area(id));
    }

    @Override
    public List<Area> findAllParent() {
        Area entity = new Area();
        entity.setType("0");
        try {
            return (List<Area>) getDao().findAll(entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Area> findAllChild(String type, String pcode) {
        Area parent = new Area();
        Area entity = new Area();
        parent.setCode(pcode);
        entity.setParent(parent);
        entity.setType(type);
        try {
            return (List<Area>) getDao().findAllChild(entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Area> findAllChildByTypeAndPcode(String type, String pcode) {
        Area parent = new Area();
        Area entity = new Area();
        parent.setCode(pcode);
        entity.setParent(parent);
        entity.setType(type);
        try {
            return (List<Area>) getDao().findAll(entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage());
        }
        return null;
    }
}
