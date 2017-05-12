package modules.system.svc;

import modules.system.dao.AreaDao;
import modules.system.model.Area;
import modules.system.svc.base.SysBaseService;

import java.util.List;

/**
 * Description: AreaService.<br>
 * Date: 2016/9/18 15:28<br>
 * Author: ysj
 */
public interface AreaService extends SysBaseService<Area, AreaDao>
{
    Area find(String id);

    List<Area> findAllParent();

    List<Area> findAllChild(String type, String pcode);

    List<Area> findAllChildByTypeAndPcode(String type, String pcode);
}
