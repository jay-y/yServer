package modules.system.svc;

import modules.system.dao.DictDao;
import modules.system.model.Dict;
import modules.system.svc.base.SysBaseService;

public interface DictService extends SysBaseService<Dict, DictDao>
{
    Dict find(String id);
}
