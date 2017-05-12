package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.Payment;
import modules.system.svc.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yserver.core.model.Pagination;
import org.yserver.utils.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Description: PaymentController.<br>
 * Date: 2016/9/19 14:40<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "${adminPath}/payment")
public class PaymentController extends SysBaseController<Payment, PaymentService>
{
    private static final String INDEX_PATH = "system/payment";

    @Autowired
    private PaymentService service;

    @Override
    protected PaymentService getService()
    {
        return service;
    }

    @Override
    protected String indexMain()
    {
        return INDEX_PATH + "/index";
    }

    @Override
    protected String indexForm()
    {
        return INDEX_PATH + "/form";
    }

    @ModelAttribute
    public Payment get(@RequestParam(required = false) String id)
    {
        Payment entity = StringUtils.isNotBlank(id) ? getService().find(id) : new Payment();
        return entity;
    }

    @RequestMapping(value = "")
    public String index()
    {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<Payment> request, HttpServletResponse response)
    {
        return super.data(request, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model)
    {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(Payment entity, HttpServletResponse response, Model model)
    {
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(Payment entity, HttpServletResponse response, Model model)
    {
        return super.delete(entity, response, model);
    }
}
