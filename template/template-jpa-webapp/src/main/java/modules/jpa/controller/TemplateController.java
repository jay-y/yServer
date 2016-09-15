package modules.jpa.controller;

import modules.template.api.TemplateService;
import modules.template.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yserver.core.controller.BaseController;
import org.yserver.y;

import java.util.List;
import java.util.Map;

/**
 * Created by ysj on 2016/9/6.
 */
@Controller
public class TemplateController extends BaseController {

    @Autowired
    private TemplateService templateSvc;

    /**
     * 访问测试页
     *
     * @return
     */
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list() {
//        logBefore(logger, "template/list:::" + y.json().toJson(pd));
        List<Template> list =  templateSvc.findAllCreated();
        logger.debug(y.json().toJson(list));
        try {
            return success(list);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return fail(e.toString());
        } finally {
//            logAfter(logger);
        }
    }
}
