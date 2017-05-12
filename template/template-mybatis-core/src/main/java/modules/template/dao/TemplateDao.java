package modules.template.dao;

import modules.template.dao.base.TmpBaseDao;
import modules.template.model.Template;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

@MyBatisMapper
public interface TemplateDao extends TmpBaseDao<Template>
{
}
