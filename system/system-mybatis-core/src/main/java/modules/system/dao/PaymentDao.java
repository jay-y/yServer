package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Payment;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

/**
 * Description: PaymentDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
@MyBatisMapper
public interface PaymentDao extends SysBaseDao<Payment> {
}
