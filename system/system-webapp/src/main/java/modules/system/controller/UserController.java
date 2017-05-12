package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.User;
import modules.system.svc.UserService;
import modules.system.utils.UserUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yserver.core.model.Pagination;
import org.yserver.utils.CiphertextUtil;
import org.yserver.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: UserController.<br>
 * Date: 2016/9/19 14:40<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "${adminPath}/user")
public class UserController extends SysBaseController<User, UserService>
{
    //首页
    private static final String INDEX_HOME = "system/index";

    private static final String INDEX_PATH = "system/user";

    @Autowired
    private UserService service;

    @Override
    protected UserService getService()
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
    public User get(@RequestParam(required = false) String id)
    {
        User entity = StringUtils.isNotBlank(id) ? getService().find(id) : new User();
        return entity;
    }

    @RequestMapping(value = "")
    public String index()
    {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<User> request, HttpServletResponse response)
    {
        return super.data(request, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model)
    {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(User entity, HttpServletRequest request, HttpServletResponse response, Model model)
    {
        User tmp = service.findByUserName(entity.getUsername());
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = oldPassword;
        if (!oldPassword.equals(entity.getPassword()))
        {
            newPassword = new SimpleHash(CiphertextUtil.SHA_1, entity.getUsername(), entity.getPassword()).toString();
        }
        ResponseBuilder respBuilder = getRespBuilder(response).setLoad(indexMain()).setModel(model);
        if (StringUtils.isBlank(entity.getCode()))
        {
            if (null != tmp)
            {
                return respBuilder.setMsg("保存失败,用户已存在").error();
            }
        }
        entity.setPassword(newPassword);
        User result = service.save(entity);
        return respBuilder.setData(result.getCode()).setMsg("保存成功").success();
    }

    @RequestMapping(value = "update")
    public String update(User entity, HttpServletResponse response, Model model)
    {
        ResponseBuilder respBuilder = getRespBuilder(response).setLoad(INDEX_HOME).setModel(model);
        User result = service.save(entity);
        UserUtil.clearCache(result);
        return respBuilder.setData(result).setMsg("更新成功").success();
    }

    @RequestMapping(value = "delete")
    public String delete(User entity, HttpServletResponse response, Model model)
    {
        User currentUser = UserUtil.getUser();
        ResponseBuilder respBuilder = getRespBuilder(response).setLoad(indexMain()).setModel(model);
        if (null != currentUser && StringUtils.isNotBlank(currentUser.getCode()))
        {
            if (entity.getCode().equals("0") || entity.getRole().getCode().equals("0"))
            {
                return respBuilder.setMsg("危险操作,不能删除超级管理员").error();
            }
            else if (currentUser.getCode().equals(entity.getCode()))
            {
                return respBuilder.setMsg("删除失败,不能删除当前用户").fail();
            }
            else
            {
                service.delete(entity);
                return respBuilder.setMsg("删除成功").success();
            }
        }
        else
        {
            return respBuilder.setMsg("删除失败").fail();
        }
    }
}
