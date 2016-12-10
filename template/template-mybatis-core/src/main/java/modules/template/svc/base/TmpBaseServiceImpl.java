package modules.template.svc.base;

import modules.template.dao.base.TmpBaseDao;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.core.model.BaseEntity;
import org.yserver.core.mybatis.MyBatisBaseServiceImpl;

/**
 * Description: Template.<br>
 * Date: 2016/9/6 1:54<br>
 * Author: ysj
 */
@DataSource(value = "default")
@Transactional
public abstract class TmpBaseServiceImpl<T extends BaseEntity, DAO extends TmpBaseDao> extends MyBatisBaseServiceImpl<T, DAO> {
}
