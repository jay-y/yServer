package modules.template.svc;

import modules.template.api.TemplateService;
import modules.template.dao.TemplateDao;
import modules.template.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.core.jpa.JpaBaseServiceImpl;

import java.io.Serializable;
import java.util.List;

@Service("templateService")
@DataSource(value = "default")
@Transactional
public class TemplateServiceImpl extends JpaBaseServiceImpl<Template, Serializable, TemplateDao> implements TemplateService {
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