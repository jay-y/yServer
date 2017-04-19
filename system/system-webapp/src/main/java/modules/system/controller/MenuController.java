package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.Menu;
import modules.system.svc.MenuService;
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
 * Description: 菜单控制层.<br>
 * Date: 2016/9/20 15:05<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "${adminPath}/menu")
public class MenuController extends SysBaseController<Menu, MenuService> {
    private static final String INDEX_PATH = "system/menu";

    @Autowired
    private MenuService service;

    @Override
    protected MenuService getService() {
        return service;
    }

    @Override
    protected String indexMain() {
        return INDEX_PATH + "/index";
    }

    @Override
    protected String indexForm() {
        return INDEX_PATH + "/form";
    }

    @ModelAttribute
    public Menu get(@RequestParam(required = false) String id) {
        Menu entity = StringUtils.isNotBlank(id) ? service.find(id) : new Menu();
        wrapEntity(entity);
        return entity;
    }

    @RequestMapping(value = "")
    public String index() {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<Menu> request, HttpServletResponse response) {
        return super.data(request, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model) {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(Menu entity, HttpServletResponse response, Model model) {
        /** TEST begin **/
//        Menu parent = new Menu();
//        parent.setType("0");
//        parent.setName("系统管理");
//        parent.setUrl("#");
//        parent.setIcon("fa-th-large");
//        parent.setOrder("1");
//        parent = service.save(parent);
//
//        List<Menu> menus = new ArrayList<>();
//        entity = new Menu();
//        entity.setParent(parent);
//        entity.setType("1");
//        entity.setName("用户管理");
//        entity.setUrl("/a/user");
//        entity.setIcon("fa-user");
//        entity.setOrder("1");
//        menus.add(entity);
//
//        entity = new Menu();
//        entity.setParent(parent);
//        entity.setType("1");
//        entity.setName("菜单管理");
//        entity.setUrl("/a/menu");
//        entity.setIcon("fa-book");
//        entity.setOrder("2");
//        menus.add(entity);
//        service.save(menus);
//        return success(response, model, null);
        /** TEST end **/
        wrapEntity(entity);
        // 清理缓存
        UserUtil.removeCache(UserUtil.CACHE_MENU_LIST);
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(Menu entity, HttpServletResponse response, Model model) {
        // 清理缓存
        UserUtil.removeCache(UserUtil.CACHE_MENU_LIST);
        return super.delete(entity, response, model);
    }

    /**
     * 加工实体
     *
     * @param entity
     * @return
     */
    private Menu wrapEntity(Menu entity) {
        if (null == entity.getParent()
                || (null != entity.getParent() && StringUtils.isBlank(entity.getParent().getCode()))) {
            entity.setType("0");
            entity.setParent(null);
        } else {
            entity.setType("1");
            if (null != entity.getParent()
                    && StringUtils.isNotBlank(entity.getParent().getCode())) {
                entity.setPcode(entity.getParent().getCode());
            }
        }
        return entity;
    }
}
