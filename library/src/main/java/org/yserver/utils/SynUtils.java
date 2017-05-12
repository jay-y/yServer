package org.yserver.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

/**
 * Description: 综合工具包.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public class SynUtils
{

    public static String get32UUID()
    {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * 串接arr参数，生成sha1 digest
     *
     * @param arr
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String genSha1Digest(String... arr) throws NoSuchAlgorithmException
    {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (String a : arr)
        {
            sb.append(a);
        }
        return DigestUtils.sha1Hex(sb.toString());
    }

    public static void main(String[] args)
    {
        System.out.println(get32UUID());
    }
}
