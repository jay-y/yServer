package org.yserver.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.yserver.y;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Description: 远程访问安全认证拦截器.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public class RemoteAccessAuthInteceptor implements HandlerInterceptor {
    /**
     * <p>
     * 属性文件加载
     * </p>
     */
    @Autowired
    private Properties globalConfigurer;

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object o, Exception e) {
        y.log().debug("[GOD]拦截器结束");
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object o, ModelAndView mv) {
        y.log().debug("[GOD]拦截器工作");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o) {
        y.log().debug("[GOD]拦截器开启");
        String sysToken = (String) globalConfigurer.get("global.remote.access.hessian.token");
        return sysToken.equals(request.getParameter("TOKEN"));
    }
}