package modules.system.svc;

import modules.system.dao.PaymentDao;
import modules.system.model.Payment;
import modules.system.svc.base.SysBaseService;

public interface PaymentService extends SysBaseService<Payment, PaymentDao>
{
    Payment find(String id);
}
