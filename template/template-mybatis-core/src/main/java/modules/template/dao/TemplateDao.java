package modules.template.dao;

import org.springframework.stereotype.Repository;

/**
 * Description: TemplateDao.<br>
 * Date: 2016/9/6 1:28<br>
 * Author: ysj
 */
@Repository("TemplateDao")
public class TemplateDao extends BaseDao {
    private static final String MAPPER_NAME = "TemplateMapper";

    @Override
    public String getMapperName() {
        return MAPPER_NAME;
    }
}