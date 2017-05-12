package org.yserver.utils;

import java.util.List;
import java.util.Map;

/**
 * Description: JsonUtil.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public interface JsonUtil
{
    /**
     * 对象转JSON字符串
     *
     * @param object
     * @return
     */
    String toJson(Object object);

    /**
     * 对象转JSON字符串(格式化后的)
     *
     * @param object
     * @return
     */
    String toPrettyJson(Object object);

    /**
     * Json字符串转对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    <T> T jsonToObject(String json, Class<T> cls);

    /**
     * Json字符串转换成List
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    <T> List<T> jsonToList(String json, Class<T> cls);

    /**
     * Json字符串转换成List<Map>
     *
     * @param json
     * @return
     */
    <T> List<Map<String, T>> jsonToListMap(String json);

    /**
     * Json字符串转换成Map
     *
     * @param json
     * @return
     */
    <T> Map<String, T> jsonToMap(String json);
}
