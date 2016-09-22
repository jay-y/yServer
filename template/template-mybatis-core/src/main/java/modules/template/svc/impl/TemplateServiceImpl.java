package modules.template.svc.impl;

import modules.template.dao.TemplateDao;
import modules.template.dao.impl.TemplateDaoImpl;
import modules.template.model.Template;
import modules.template.svc.TemplateService;
import modules.template.svc.base.TmpBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;

import javax.annotation.Resource;

/**
 * Description: Template.<br>
 * Date: 2016/9/6 1:54<br>
 * Author: ysj
 */
@Service("templateService")
@DataSource(value = "default")
@Transactional
public class TemplateServiceImpl extends TmpBaseServiceImpl<Template, TemplateDao>
        implements TemplateService {
    @Resource(name = "TemplateDao")
    private TemplateDaoImpl dao;

    @Override
    public TemplateDaoImpl getDao() {
        return dao;
    }
}
