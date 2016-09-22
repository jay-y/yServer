package modules.template.svc.base;

import modules.template.dao.base.TmpBaseDao;
import org.yserver.core.mybatis.MyBatisBaseService;

public interface TmpBaseService<T, DAO extends TmpBaseDao> extends MyBatisBaseService<T, DAO> {
}
