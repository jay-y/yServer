package org.yserver.core.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.yserver.utils.PropertiesLoader;

import java.util.Map;

/**
 * 全局配置类
 *
 * @author ThinkGem
 * @version 2014-06-25
 */
public class GlobalProperties {

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
    public static GlobalProperties getInstance() {
        return global;
    }

    /**
     * 获取配置
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
