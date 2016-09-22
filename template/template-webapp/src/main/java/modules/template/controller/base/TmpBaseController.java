package modules.template.controller.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yserver.core.config.Constant;
import org.yserver.core.controller.BaseController;

/**
 * Description: SysBaseController.<br>
 * Date: 2016/9/20 21:11<br>
 * Author: ysj
 */
public class TmpBaseController extends BaseController {
    @Value("${adminPath}")
    protected String adminPath;

    /**
     * 添加Model消息
     *
     * @param model
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute(Constant.ServerConfig.RESPONSE_MSG, sb.toString());
    }

    /**
     * 添加Model消息
     *
     * @param model
     * @param status
     * @param messages
     */
    protected void addMessage(Model model, char status, String... messages) {
        model.addAttribute(Constant.ServerConfig.RESPONSE_STATUS, status);
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute(Constant.ServerConfig.RESPONSE_MSG, sb.toString());
    }

    /**
     * 添加Flash消息
     *
     * @param redirectAttributes
     * @param status
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, char status, String... messages) {
        redirectAttributes.addFlashAttribute(Constant.ServerConfig.RESPONSE_STATUS, status);
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute(Constant.ServerConfig.RESPONSE_MSG, sb.toString());
    }
}
