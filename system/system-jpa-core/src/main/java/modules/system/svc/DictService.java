package modules.system.svc;

import modules.system.dao.DictDao;
import modules.system.model.Dict;
import modules.system.svc.base.SysBaseService;

import java.util.List;

/**
 * Description: DictService.<br>
 * Date: 2016/9/18 15:28<br>
 * Author: ysj
 */
public interface DictService extends SysBaseService<Dict, DictDao>
{

    List<Dict> findListByType(String type);
}
