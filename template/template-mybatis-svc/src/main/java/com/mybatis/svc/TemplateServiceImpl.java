package com.mybatis.svc;

import com.mybatis.dao.TemplateDao;
import com.template.api.TemplateService;
import com.template.model.Template;
import org.springframework.stereotype.Service;
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
            return dao.findForList(dao.getMapperName()+".findAllCreated",null);
        } catch (Exception e) {
            y.log().error(e.getMessage(),e);
        }
        return null;
    }
}
