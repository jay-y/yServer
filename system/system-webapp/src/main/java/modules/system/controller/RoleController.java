package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.Menu;
import modules.system.model.Role;
import modules.system.svc.RoleService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/26.
 */

@Controller
@RequestMapping(value = "${adminPath}/role")
public class RoleController extends SysBaseController<Role, RoleService>
{
    private static final String INDEX_PATH = "system/role";

    @Autowired
    private RoleService service;

    @Override
    protected RoleService getService()
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
    public Role get(@RequestParam(required = false) String id)
    {
        Role entity = StringUtils.isNotBlank(id) ? getService().find(id) : new Role();
        return entity;
    }

    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping(value = "")
    public String index()
    {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<Role> request, HttpServletResponse response)
    {
        return super.data(request, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model)
    {
        return super.form(response, model);
    }

    @RequestMapping(value = "formInit")
    public String formInit(Role entity, HttpServletResponse response)
    {
        Map<String, Object> result = new HashMap<>();
        String ids = "";
        if (entity.getMenus().size() > 0)
        {
            for (Menu menu : entity.getMenus())
            {
                ids += "," + menu.getCode();
            }
            ids = ids.substring(1);
        }
        result.put("menuList", UserUtil.getMenuList());
        result.put("ids", ids);
        return getRespBuilder(response).setData(result).success();
    }

    @RequestMapping(value = "save")
    public String save(Role entity, HttpServletResponse response, Model model)
    {
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(Role entity, HttpServletResponse response, Model model)
    {
        return super.delete(entity, response, model);
    }
}
