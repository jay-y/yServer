package modules.jpa.svc;

import modules.jpa.dao.TemplateDao;
import modules.template.api.TemplateService;
import modules.template.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

import java.util.List;

@Service("templateService")
@DataSource(value="default")
@Transactional
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateDao dao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Template> findAllCreated() {
        return dao.findAllCreated();
    }
}