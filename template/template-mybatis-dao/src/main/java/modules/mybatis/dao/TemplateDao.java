package modules.mybatis.dao;

import org.springframework.stereotype.Repository;

/**
 * Description: TestDao.<br>
 * Date: 2016/9/6 1:28<br>
 * Author: ysj
 */
@Repository("TemplateDao")
public class TemplateDao extends BaseTemplateDao {
    private static final String MAPPER_NAME = "TemplateMapper";

    @Override
    public String getMapperName() {
        return MAPPER_NAME;
    }
}