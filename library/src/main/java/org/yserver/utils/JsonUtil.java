package org.yserver.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Description: Gson封装.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public class JsonUtil {
    private final static Gson GSON = new Gson();

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String jsonString = null;
        if (GSON != null) {
            jsonString = GSON.toJson(object);
        }
        return jsonString;
    }

    /**
     * 转成json(格式化后的)
     *
     * @param object
     * @return
     */
    public static String toPrettyJson(Object object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    /**
     * 转成bean
     *
     * @param JsonString
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String JsonString, Class<T> cls) {
        T t = null;
        if (GSON != null) {
            t = GSON.fromJson(JsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param JsonString
     * @param cls
     * @return
     */
    public static <T> List<T> jsonToList(String JsonString, Class<T> cls) {
        List<T> list = null;
        if (GSON != null) {
            list = GSON.fromJson(JsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param JsonString
     * @return
     */
    public static <T> List<Map<String, T>> jsonToListMap(String JsonString) {
        List<Map<String, T>> list = null;
        if (GSON != null) {
            list = GSON.fromJson(JsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param JsonString
     * @return
     */
    public static <T> Map<String, T> jsonToMap(String JsonString) {
        Map<String, T> map = null;
        if (GSON != null) {
            map = GSON.fromJson(JsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    private JsonUtil() {
    }
}
