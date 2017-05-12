package modules.system.controller.base;

import org.yserver.core.model.BaseEntity;
import org.yserver.core.mybatis.MyBatisBaseService;
import org.yserver.core.mybatis.MybatisBaseController;

/**
 * Description: SysBaseController.<br>
 * Date: 2016/12/30 15:20<br>
 * Author: ysj
 */
//public abstract class SysBaseController<T extends BaseEntity, S extends JpaBaseService> extends JpaBaseController<T, S> {
//}
public abstract class SysBaseController<T extends BaseEntity, S extends MyBatisBaseService> extends MybatisBaseController<T, S>
{
}
