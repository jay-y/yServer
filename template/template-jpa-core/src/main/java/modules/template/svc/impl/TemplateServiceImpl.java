package modules.template.svc.impl;

import modules.template.dao.TemplateDao;
import modules.template.model.Template;
import modules.template.svc.TemplateService;
import modules.template.svc.base.TmpBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

import java.util.List;

@Service("templateService")
@DataSource(value = "default")
@Transactional
public class TemplateServiceImpl extends TmpBaseServiceImpl<Template, TemplateDao> implements TemplateService {
    @Autowired
    private TemplateDao dao;

    @Override
    public TemplateDao getDao() {
        return dao;
    }

    @Override
    public List<Template> findAllCreated() {
        return getDao().findAllCreated();
    }
}