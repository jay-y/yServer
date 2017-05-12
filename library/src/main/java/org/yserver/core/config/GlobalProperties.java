package org.yserver.core.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.yserver.core.listener.WebContextListener;
import org.yserver.utils.PropertiesLoader;

import java.util.Map;

/**
 * Description: 全局配置类.<br>
 * Author: ysj
 */
public class GlobalProperties
{

    /**
     * 当前对象实例
     */
    private static GlobalProperties global = new GlobalProperties();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("META-INF/global.properties");

    /**
     * 获取当前对象实例
     */
    public static GlobalProperties getInstance()
    {
        return global;
    }

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public static String getProperty(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取管理端请求根路径
     */
    public static String getAdminPath()
    {
        return getProperty("adminPath");
    }

    /**
     * 获取客户端请求根路径
     */
    public static String getClientPath()
    {
        return getProperty("clientPath");
    }

    /**
     * 获取接口根路径
     */
    public static String getApiPath()
    {
        return getProperty("apiPath");
    }

    /**
     * 获取View路径
     */
    public static String getViewPrefix()
    {
        return getProperty("viewPrefix");
    }

    /**
     * 获取View后缀
     */
    public static String getViewSuffix()
    {
        return getProperty("viewSuffix");
    }

    /**
     * 获取文件的根目录
     *
     * @return
     */
    public static String getWebAppDir()
    {
        String dir;
        try
        {
            dir = WebContextListener.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        }
        catch (Exception e)
        {
            return "";
        }
        if (!dir.endsWith("/"))
        {
            dir += "/";
        }
        return dir;
    }
}
