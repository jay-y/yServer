package org.yserver.core.jpa;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.yserver.core.controller.BaseController;
import org.yserver.core.model.BaseEntity;
import org.yserver.core.model.Pagination;
import org.yserver.utils.Log;
import org.yserver.y;

import javax.servlet.http.HttpServletResponse;

/**
 * Description: JpaBaseController.<br>
 * Date: 2016/9/6 0:17<br>
 * Author: ysj
 */
public abstract class JpaBaseController<T extends BaseEntity, S extends JpaBaseService> extends BaseController
{
    protected Log logger = Log.getLogger(this.getClass());

    protected abstract S getService();

    protected abstract String indexMain();

    protected abstract String indexForm();

    public String index()
    {
        return indexMain();
    }

    public String data(Pagination<T> request, HttpServletResponse response)
    {
        Page<T> result = getService().findAll(y.json().toJson(request.getParams()), request.getPageable());
        return getRespBuilder(response).setData(result).success();
    }

    public String form(HttpServletResponse response, Model model)
    {
        return getRespBuilder(response).setLoad(indexForm()).setModel(model).success();
    }

    public String save(T entity, HttpServletResponse response, Model model)
    {
        return getRespBuilder(response).setLoad(indexMain()).setModel(model).setData(getService().save(entity)).setMsg("保存成功").success();
    }

    public String delete(T entity, HttpServletResponse response, Model model)
    {
        ResponseBuilder respBuilder = getRespBuilder(response).setLoad(indexMain()).setModel(model);
        try
        {
            getService().delete(entity);
            return respBuilder.setMsg("删除成功").success();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return respBuilder.setMsg("删除失败,有子关联存在").error();
        }
    }
}
