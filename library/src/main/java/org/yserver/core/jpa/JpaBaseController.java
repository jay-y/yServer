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
public abstract class JpaBaseController<T extends BaseEntity, S extends JpaBaseService> extends BaseController {
    protected Log logger = Log.getLogger(this.getClass());

    protected abstract String main();

    protected abstract String form();

    protected abstract S service();

    public String index() {
        return main();
    }

    public String data(Pagination<T> request, HttpServletResponse response) {
        Page<T> result = service().findAll(
                y.json().toJson(request.getParams()), request.getPageable());
        return getRespBuilder(response)
                .setData(result)
                .success();
    }

    public String form(HttpServletResponse response, Model model) {
        return getRespBuilder(response)
                .setLoad(form())
                .setModel(model)
                .success();
    }

    public String save(T entity, HttpServletResponse response, Model model) {
        return getRespBuilder(response)
                .setLoad(main())
                .setModel(model)
                .setData(service().save(entity))
                .setMsg("保存成功")
                .success();
    }

    public String delete(T entity, HttpServletResponse response, Model model) {
        ResponseBuilder respBuilder = getRespBuilder(response)
                .setLoad(main())
                .setModel(model);
        try {
            service().delete(entity);
            return respBuilder
                    .setMsg("删除成功")
                    .success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return respBuilder
                    .setMsg("删除失败,有子关联存在")
                    .error();
        }
    }
}