package modules.template.svc;

import modules.template.api.TemplateService;
import modules.template.dao.TemplateDao;
import modules.template.model.Template;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.core.mybatis.MyBatisBaseServiceImpl;
import org.yserver.y;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: Template.<br>
 * Date: 2016/9/6 1:54<br>
 * Author: ysj
 */
@Service("templateService")
@DataSource(value="default")
@Transactional
public class TemplateServiceImpl extends MyBatisBaseServiceImpl<Template, TemplateDao>
        implements TemplateService {
    @Resource(name = "TemplateDao")
    private TemplateDao dao;

    @Override
    public TemplateDao getDao() {
        return dao;
    }

    @Override
    public List<Template> findAllCreated() {
        try {
            return getDao().findForList(getDao().getMapperName()+".findAllCreated",null);
        } catch (Exception e) {
            y.log().error(e.getMessage(),e);
        }
        return null;
    }
}
