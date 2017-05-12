package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.Dict;
import modules.system.model.User;
import modules.system.svc.DictService;
import modules.system.utils.UserUtil;
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
 * Description: DictController.<br>
 * Date: 2016/9/19 14:40<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "${adminPath}/dict")
public class DictController extends SysBaseController<Dict, DictService>
{
    private static final String INDEX_PATH = "system/dict";

    @Autowired
    private DictService service;

    @Override
    protected DictService getService()
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
    public Dict get(@RequestParam(required = false) String id)
    {
        Dict entity = StringUtils.isNotBlank(id) ? getService().find(id) : new Dict();
        wrapEntity(entity);
        return entity;
    }

    @RequestMapping(value = "")
    public String index()
    {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<Dict> request, HttpServletResponse response)
    {
        return super.data(request, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model)
    {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(Dict entity, HttpServletResponse response, Model model)
    {
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(Dict entity, HttpServletResponse response, Model model)
    {
        return super.delete(entity, response, model);
    }

    /**
     * 加工实体
     *
     * @param entity
     * @return
     */
    private Dict wrapEntity(Dict entity)
    {
        User user = UserUtil.getUser();
        if (StringUtils.isEmpty(entity.getCode()))
        {
            entity.setCreator(user);
        }
        else
        {
            entity.setEditor(user);
        }
        return entity;
    }
}
