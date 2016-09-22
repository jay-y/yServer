package modules.template.controller;

import modules.template.controller.base.TmpBaseController;
import modules.template.model.Template;
import modules.template.svc.impl.TemplateServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yserver.y;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ysj on 2016/9/6.
 */
@Controller
public class TemplateController extends TmpBaseController {
    //列表页面
    private static final String INDEX_MAIN = "index";

    //Login页面
    private static final String INDEX_LOGIN = "admin/login";

    private static final String INDEX_HOME = "home/index";

    //重定向请求
    private static final String INDEX_REDIRECT = "redirect:/a";

    @Autowired
    private TemplateServiceImpl templateSvc;

    @RequestMapping(value = "/data")
    public String data(HttpServletResponse response) {
        ResponseBuilder respBuilder = getRespBuilder(response);
        try {
            List<Template> list = templateSvc.findAll();
            logger.debug(y.json().toJson(list));
            return respBuilder
                    .setData(list)
                    .success();
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return respBuilder
                    .setMsg(e.toString())
                    .error();
        }
    }

    /**
     * 访问首页
     *
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = {"", "${adminPath}"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
//        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
//        // 登录成功后，验证码计算器清零
//        SystemAuthorizingRealm.isValidateCodeLogin(principal.getLoginName(), false, true);
//        // 如果已登录，再次访问主页，则退出原账号。
//        if (Constant._TRUE.equals(GlobalProperties.getConfig("notAllowRefreshIndex"))) {
//            String logined = CookieUtil.getCookie(request, "LOGINED");
//            if (StringUtils.isBlank(logined) || Constant._FALSE.equals(logined)) {
//                CookieUtil.setCookie(response, "LOGINED", Constant._TRUE);
//            } else if (StringUtils.equals(logined, Constant._TRUE)) {
//                UserUtil.getSubject().logout();
//                return INDEX_REDIRECT;
//            }
//        }
        return INDEX_MAIN;
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "/a/home")
    public String home() {
        return INDEX_HOME;
    }

    /**
     * 管理登录
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletResponse response) {
//        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
//
//        // 如果已登录，再次访问主页，则退出原账号。
//        if (Constant._TRUE.equals(GlobalProperties.getConfig("notAllowRefreshIndex"))) {
//            CookieUtil.setCookie(response, "LOGINED", Constant._FALSE);
//        }
//
//        // 如果已经登录，则跳转到管理首页
//        if (principal != null && !principal.isMobileLogin()) {
//            return INDEX_REDIRECT;
//        }
        return INDEX_LOGIN;
    }

    /**
     * 登录失败，真正登录的POST请求由FormAuthenticationFilter完成
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
//
//        // 如果已经登录，则跳转到管理首页
//        if (principal != null) {
//            return INDEX_REDIRECT;
//        }
//
//        String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
//        boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
//        boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
//        String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
//        String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
//
//        if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")) {
//            message = "用户或密码错误, 请重试.";
//        }
//
//        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
//        model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
//        model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
//        model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
//        model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
//
//        // 非授权异常，登录失败，验证码加1
//        if (!UnauthorizedException.class.getName().equals(exception)) {
//            model.addAttribute("isValidateCodeLogin", SystemAuthorizingRealm.isValidateCodeLogin(username, true, false));
//        }
//
//        // 验证失败清空验证码
//        request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, SynUtils.get32UUID());
//
//        // 如果是手机登录，则返回JSON字符串
//        if (mobile) {
//            return getRespBuilder(response).setModel(model).success();
//        }
        return INDEX_LOGIN;
    }
}
