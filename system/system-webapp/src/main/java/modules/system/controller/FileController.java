package modules.system.controller;

import modules.system.controller.base.SysBaseController;
import modules.system.model.File;
import modules.system.svc.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yserver.core.model.Pagination;
import org.yserver.utils.FileUtils;
import org.yserver.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016-11-14.
 */
@Controller
@RequestMapping(value = "${adminPath}/file")
public class FileController extends SysBaseController<File, FileService> {
    private static final String INDEX_PATH = "system/file";

    @Autowired
    private FileService service;

    @Override
    protected FileService getService() {
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
    public File get(@RequestParam(required = false) String id) {
        File entity = StringUtils.isNotBlank(id) ? getService().find(id) : new File();
        return entity;
    }

    @RequestMapping(value = "")
    public String index() {
        return super.index();
    }

    @RequestMapping(value = "data")
    public String data(Pagination<File> pagination, HttpServletResponse response) {
        return super.data(pagination, response);
    }

    @RequestMapping(value = "form")
    public String form(HttpServletResponse response, Model model) {
        return super.form(response, model);
    }

    @RequestMapping(value = "save")
    public String save(File entity, HttpServletRequest request, HttpServletResponse response, Model model) {
        return super.save(entity, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(File entity, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank(entity.getUri()) && !entity.getUri().contains("http://") && !entity.getUri().contains("https://")) {
            // 文件保存路径
            String ctxPath = request.getSession().getServletContext().getRealPath("/") + "/";
            FileUtils.deleteFile(ctxPath + entity.getUri());
        }
        return super.delete(entity, response, model);
    }
}
