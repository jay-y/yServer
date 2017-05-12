package modules.system.controller;

import modules.system.model.File;
import modules.system.model.User;
import modules.system.security.FormAuthenticationFilter;
import modules.system.security.SystemAuthorizingRealm;
import modules.system.svc.FileService;
import modules.system.utils.FileUtil;
import modules.system.utils.UserUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.yserver.core.config.Constant;
import org.yserver.core.config.GlobalProperties;
import org.yserver.core.controller.BaseController;
import org.yserver.core.servlet.ValidateCodeServlet;
import org.yserver.utils.CookieUtil;
import org.yserver.utils.FileUtils;
import org.yserver.utils.StringUtils;
import org.yserver.utils.SynUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: AdminController.<br>
 * Date: 2016/9/19 14:40<br>
 * Author: ysj
 */
@Controller
@RequestMapping
public class AdminController extends BaseController
{
    //主面板
    private static final String INDEX_MAIN = "index";

    //首页
    private static final String INDEX_HOME = "system/index";

    //登录页
    private static final String INDEX_LOGIN = "system/login";

    //重定向
    private static final String INDEX_REDIRECT = "system/redirect";

    @Autowired
    private FileService fileService;

    /**
     * 主面板
     *
     * @return
     */
    @RequestMapping(value = "${adminPath}")
    public String index(HttpServletRequest request, HttpServletResponse response)
    {
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
        // 登录成功后，验证码计算器清零
        if (null != principal)
        {
            CookieUtil.setCookie(response, "LOGIN_STATUS", Constant._TRUE);
            SystemAuthorizingRealm.isValidateCodeLogin(principal.getLoginName(), false, true);
        }
        else
        {
            return INDEX_REDIRECT;
        }
        // 如果已登录，再次访问主页，则退出原账号。
        if (Constant._TRUE.equals(GlobalProperties.getProperty("notAllowRefreshIndex")))
        {
            String logined = CookieUtil.getCookie(request, "LOGINED");
            if (StringUtils.isBlank(logined) || Constant._FALSE.equals(logined))
            {
                CookieUtil.setCookie(response, "LOGINED", Constant._TRUE);
            }
            else if (StringUtils.equals(logined, Constant._TRUE))
            {
                UserUtil.getSubject().logout();
                return INDEX_REDIRECT;
            }
        }
        return INDEX_MAIN;
    }

    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping(value = "${adminPath}/home")
    public String home(HttpServletResponse response, Model model)
    {
        User entity = UserUtil.getUser();
        return getRespBuilder(response).setLoad(INDEX_HOME).setModel(model).setData(entity).setMsg("登陆成功").success();
    }

    /**
     * 管理登录
     *
     * @return
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();

        // 如果已登录，再次访问主页，则退出原账号。
        if (Constant._TRUE.equals(GlobalProperties.getProperty("notAllowRefreshIndex")))
        {
            CookieUtil.setCookie(response, "LOGINED", Constant._FALSE);
        }

        // 如果已经登录，则跳转到管理首页
        if (principal != null && !principal.isMobileLogin())
        {
            return getRespBuilder(response).setRedirect(true).setLoad(adminPath).redirect();
        }
        String loginStatus = CookieUtil.getCookie(request, "LOGIN_STATUS");
        if (StringUtils.isNotBlank(loginStatus) && Constant._TRUE.equals(loginStatus))
        {
            CookieUtil.setCookie(response, "LOGIN_STATUS", Constant._FALSE);
            return INDEX_REDIRECT;
        }
        return INDEX_LOGIN;
    }

    /**
     * 登录失败，真正登录的POST请求由FormAuthenticationFilter完成
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
    {
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();

        // 如果已经登录，则跳转到管理首页
        if (principal != null)
        {
            return getRespBuilder(response).setRedirect(true).setLoad(adminPath).redirect();
        }

        String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
        boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
        boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
        String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

        if (StringUtils.isBlank(message) || StringUtils.equals(message, "null"))
        {
            message = "用户或密码错误, 请重试.";
        }

        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);

        // 非授权异常，登录失败，验证码加1
        if (!UnauthorizedException.class.getName().equals(exception))
        {
            model.addAttribute("isValidateCodeLogin", SystemAuthorizingRealm.isValidateCodeLogin(username, true, false));
        }

        // 验证失败清空验证码
        request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, SynUtils.get32UUID());

        // 如果是手机登录，则返回JSON字符串
        if (mobile)
        {
            return getRespBuilder(response).setModel(model).success();
        }
        return INDEX_LOGIN;
    }

    /**
     * 文件上传
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "${adminPath}/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
    {
        List<File> fileList = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0)
        {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++)
            {
                File fileObj = null;
                String fileMD5 = null;
                MultipartFile file = files[i];
                // 判断文件是否为空
                if (!file.isEmpty())
                {
                    try
                    {
                        String fileName = SynUtils.get32UUID();
                        double size = file.getSize();
                        String[] contentType = file.getContentType().split("/");
                        String type = contentType[0];
                        String format = contentType[1];
                        String uri = "source/upload/" + format + "/" + fileName + "." + format;
                        // 文件保存路径
                        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "/";
                        String filePath = ctxPath + uri;
                        // 转存文件
                        if (FileUtils.createFile(filePath))
                        {
                            java.io.File newFile = new java.io.File(filePath);
                            file.transferTo(newFile);
                            fileMD5 = FileUtil.getFileMD5(newFile);
                            fileObj = fileService.findByRemarks(fileMD5);
                        }
                        if (null == fileObj)
                        {
                            fileObj = new File();
                            fileObj.setRemarks(fileMD5);
                        }
                        else
                        {
                            FileUtils.deleteFile(ctxPath + fileObj.getUri());
                        }
                        fileObj.setName(fileName);
                        fileObj.setUri(uri);
                        fileObj.setSize(size);
                        fileObj.setFormat(format);
                        fileObj.setType(type);
                        fileList.add(fileObj);
                    }
                    catch (Exception e)
                    {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
            if (fileList.size() > 0)
            {
                fileList = fileService.save(fileList);
            }
        }
        return getRespBuilder(response).setData(fileList).setMsg("上传成功").success();
    }
}
