package modules.system.interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.yserver.utils.DateUtil;
import org.yserver.utils.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * 日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {
    private Log logger = Log.getLogger(LogInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (logger.isDebug()) {
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("The start time: {} URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
        return true;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("ViewName: " + modelAndView.getViewName());
        }
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        // 打印JVM信息。
        if (logger.isDebug()) {
            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis();    //2、结束时间
            logger.debug("The end time: {} used: {} URI: {} maximum memory allocated memory: {}m: {}m has been assigned the remaining space in memory: {}m memory limit: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
                    DateUtil.formatDateTime(endTime - beginTime),
                    request.getRequestURI(),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }

    }

}
