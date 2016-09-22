package modules.template.dao;

import modules.template.dao.base.TmpBaseDao;
import modules.template.model.Template;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description: TODO.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
public interface TemplateDao extends TmpBaseDao<Template> {

    @Query("select o from Template o where o.updatedTime is null")
    List<Template> findAllCreated();

    @Query("select o from Template o where o.code=?")
    List<Template> getListByCode(String code);
}
