package modules.system.svc.impl;

import modules.system.dao.LogisticsDao;
import modules.system.model.Logistics;
import modules.system.svc.LogisticsService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

/**
 * Description: LogisticsServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@Service
@DataSource("default")
@Transactional
public class LogisticsServiceImpl
        extends SysBaseServiceImpl<Logistics, LogisticsDao> implements LogisticsService {
    @Autowired
    private LogisticsDao dao;

    @Override
    public LogisticsDao getDao() {
        return dao;
    }
}
