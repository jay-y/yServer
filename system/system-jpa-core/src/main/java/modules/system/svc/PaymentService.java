package modules.system.svc;

import modules.system.dao.PaymentDao;
import modules.system.model.Payment;
import modules.system.svc.base.SysBaseService;

/**
 * Description: PaymentService.<br>
 * Date: 2016/9/18 15:28<br>
 * Author: ysj
 */
public interface PaymentService extends SysBaseService<Payment, PaymentDao>
{
}
