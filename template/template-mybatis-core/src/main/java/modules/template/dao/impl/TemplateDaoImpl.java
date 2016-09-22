package modules.template.dao.impl;

import modules.template.dao.TemplateDao;
import modules.template.dao.base.TmpBaseDaoImpl;
import modules.template.model.Template;
import org.springframework.stereotype.Repository;

/**
 * Description: TemplateDaoImpl.<br>
 * Date: 2016/9/6 1:28<br>
 * Author: ysj
 */
@Repository("TemplateDao")
public class TemplateDaoImpl extends TmpBaseDaoImpl<Template> implements TemplateDao {
    private static final String MAPPER_NAME = "TemplateMapper";

    @Override
    public String getMapperName() {
        return MAPPER_NAME;
    }
}