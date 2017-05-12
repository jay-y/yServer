package modules.system.svc.impl;

import modules.system.dao.DictDao;
import modules.system.model.Dict;
import modules.system.svc.DictService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

@Service
@DataSource("default")
@Transactional
public class DictServiceImpl extends SysBaseServiceImpl<Dict, DictDao> implements DictService
{
    @Autowired
    private DictDao dao;

    @Override
    public DictDao getDao()
    {
        return dao;
    }

    @Override
    public Dict find(String id)
    {
        return getDao().findOne(new Dict(id));
    }
}
