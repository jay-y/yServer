package org.yserver.core.config;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    Welcome to use " + GlobalProperties.getProperty("platform")
                + "\r\n    Powered By " + GlobalProperties.getProperty("website") + "\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }

    /**
     * 手动注入ApplicationContext
     *
     * @param applicationContext
     */
    public static void injectApplicationContext(ApplicationContext applicationContext) {
        if (SpringContextHolder.applicationContext == null) {
            synchronized (SpringContextHolder.class) {
                if (SpringContextHolder.applicationContext == null) {
                    SpringContextHolder.applicationContext = applicationContext;
                }
            }
        }
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("clear SpringContextHolder in ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.debug("ApplicationContext injected into the SpringContextHolder:{}", applicationContext);
        if (SpringContextHolder.applicationContext != null) {
            logger.info("ApplicationContext in the SpringContextHolder is covered, the original ApplicationContext is:" + SpringContextHolder.applicationContext);
        }
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null, "ApplicaitonContext attribute not injected,please define applicationContext.xml in SpringContextHolder.");
    }
}