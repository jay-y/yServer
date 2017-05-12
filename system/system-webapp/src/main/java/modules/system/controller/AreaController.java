package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.Area;
import modules.system.model.User;
import modules.system.svc.AreaService;
import modules.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yserver.utils.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description: 区域控制层.<br>
 * Date: 2016/9/20 15:05<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "${adminPath}/area")
public class AreaController extends SysBaseController<Area, AreaService>
{
    private static final String INDEX_PATH = "system/area";

    @Autowired
    private AreaService service;

    @Override
    protected AreaService getService()
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
    public Area get(@RequestParam(required = false) String id)
    {
        Area entity = StringUtils.isNotBlank(id) ? getService().find(id) : new Area();
        wrapEntity(entity);
        return entity;
    }

    @RequestMapping(value = "")
    public String index()
    {
        return super.index();
    }

    @RequestMapping(value = "getParentArea")
    public String getParentArea(HttpServletResponse response)
    {
        List<Area> list = getService().findAllParent();
        return getRespBuilder(response).setData(list).success();
    }

    @RequestMapping(value = "getChildsArea")
    public String getChildsArea(Area entity, HttpServletResponse response)
    {
        List<Area> list = getService().findAllChild(entity.getType(), entity.getCode());
        return getRespBuilder(response).setData(list).success();
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model)
    {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(Area entity, HttpServletResponse response, Model model)
    {
        wrapEntity(entity);
        // 清理缓存
        UserUtil.removeCache(UserUtil.CACHE_AREA_LIST);
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(Area entity, HttpServletResponse response, Model model)
    {
        // 清理缓存
        UserUtil.removeCache(UserUtil.CACHE_AREA_LIST);
        return super.delete(entity, response, model);
    }

    /**
     * 加工实体
     *
     * @param entity
     * @return
     */
    private Area wrapEntity(Area entity)
    {
        User user = UserUtil.getUser();
        if (null == entity.getParent() || null != entity.getParent() && StringUtils.isBlank(entity.getParent().getCode()))
        {
            entity.setType("0");
            entity.setParent(null);
        }
        else if (null != entity.getParent() && StringUtils.isNotBlank(entity.getParent().getCode()))
        {
            Area parent = getService().find(entity.getParent().getCode());
            entity.setType(String.valueOf(Integer.parseInt(parent.getType()) + 1));
            entity.setParent(parent);
        }
        if (StringUtils.isEmpty(entity.getCode()))
        {
            entity.setCreatedBy(user.getCode());
        }
        else
        {
            entity.setUpdatedBy(user.getCode());
        }
        return entity;
    }
}
