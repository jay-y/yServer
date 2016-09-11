package org.yserver.utils;

import java.util.UUID;

/**
 * Description: 综合工具包.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public class SynUtils {

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(get32UUID());
    }
}
