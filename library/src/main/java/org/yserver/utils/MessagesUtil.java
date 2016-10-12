package org.yserver.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Description: 信息提取工具类.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public final class MessagesUtil {

    /**
     * getValue:根据系统当前语言环境，从公共ResourceBundle中获得属性值. <br/>
     *
     * @param key
     * @return
     * @date: 2014年6月15日 下午3:43:38 <br/>
     */
    public static String getValue(String key) {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle resb = ResourceBundle.getBundle(
                "META-INF/messages", currentLocale);
        return resb.getString(key);
    }

    private MessagesUtil() {
    }
}
