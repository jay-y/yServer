package org.yserver.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * ClassName: RandomUtil <br>
 * Reason: 随机字符串生成工具类. <br>
 * date: 2015年7月24日 下午4:13:08 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public class RandomUtil {
    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 基于Base62编码的SecureRandom随机生成bytes.
     */
    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return EncodeUtil.encodeBase62(randomBytes);
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.uuid());
        System.out.println(RandomUtil.uuid().length());
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomUtil.randomLong() + "  " + RandomUtil.randomBase62(5));
        }
    }
}
