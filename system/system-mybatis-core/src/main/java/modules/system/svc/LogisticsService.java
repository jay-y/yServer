package modules.system.svc;

import modules.system.dao.LogisticsDao;
import modules.system.model.Logistics;
import modules.system.svc.base.SysBaseService;

public interface LogisticsService extends SysBaseService<Logistics, LogisticsDao> {
    Logistics find(String id);
}
