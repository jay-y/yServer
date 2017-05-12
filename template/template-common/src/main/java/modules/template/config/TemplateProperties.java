package modules.template.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.yserver.utils.PropertiesLoader;

import java.util.Map;

/**
 * ClassName: 模块全局配置类 <br>
 * Reason: TemplateProperties. <br>
 * Date: 2016年7月27日 下午2:10:46 <br>
 * Author: ysj
 */
public class TemplateProperties
{
    /**
     * 属性文件加载对象
     */
    private static final PropertiesLoader loader = new PropertiesLoader("META-INF/template.properties");

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public static String getConfig(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
