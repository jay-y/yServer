package org.yserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 日志工具类.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public class Log {
    private Logger logger;

    /**
     * 获取构造器，根据类初始化Logger对象
     *
     * @param clazz Class对象
     * @return Logger对象
     */
    public static Log getLogger(Class clazz) {
        return new Log(LoggerFactory.getLogger(clazz));
    }

    /**
     * 获取构造器，根据类名初始化Logger对象
     *
     * @param loggerName 类名字符串
     * @return Logger对象
     */
    public static Log getLogger(String loggerName) {
        return new Log(LoggerFactory.getLogger(loggerName));
    }

    public void info(String object) {
        logger.info(object);
    }

    public void info(String object, Throwable e) {
        logger.info(object, e);
    }

    public void warn(String object) {
        logger.warn(object);
    }

    public void warn(String object, Throwable e) {
        logger.warn(object,e);
    }

    public void error(String object) {
        logger.error(genTag()+object);
    }

    public void error(String object, Throwable e) {
        logger.error(genTag()+object,e);
    }

    public void trace(String object) {
        logger.trace(object);
    }

    public void trace(String object, Throwable e) {
        logger.trace(object, e);
    }

    public void debug(String object) {
        logger.debug(genTag()+object);
    }

    public void debug(String object, Throwable e) {
        logger.debug(genTag()+object,e);
    }

    private String genTag(){
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d):";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return tag;
    }

    /**
     * 构造方法，初始化Log4j的日志对象
     */
    private Log(org.slf4j.Logger logger) {
        this.logger = logger;
    }
}