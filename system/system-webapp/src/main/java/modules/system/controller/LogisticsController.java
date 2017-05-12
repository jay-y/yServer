package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.Logistics;
import modules.system.svc.LogisticsService;
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
 * Description: LogisticsController.<br>
 * Date: 2016/9/19 14:40<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "${adminPath}/logistics")
public class LogisticsController extends SysBaseController<Logistics, LogisticsService>
{
    private static final String INDEX_PATH = "system/logistics";

    @Autowired
    private LogisticsService service;

    @Override
    protected LogisticsService getService()
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
    public Logistics get(@RequestParam(required = false) String id)
    {
        Logistics entity = StringUtils.isNotBlank(id) ? getService().find(id) : new Logistics();
        return entity;
    }

    @RequestMapping(value = "")
    public String index()
    {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<Logistics> request, HttpServletResponse response)
    {
        return super.data(request, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model)
    {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(Logistics entity, HttpServletResponse response, Model model)
    {
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(Logistics entity, HttpServletResponse response, Model model)
    {
        return super.delete(entity, response, model);
    }
}
