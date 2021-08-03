package kayroc.java.utils;

import java.util.Random;
import java.util.UUID;

/**
 * UUID 工具类
 *
 * @author kayroc
 */
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
public final class UUIDUtils {

    public UUIDUtils() {
    }

    /**
     * 获取随机唯一数
     *
     * @return {@link UUID}
     */
    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

    /**
     * 获取随机唯一数 HashCode
     *
     * @return 随机 UUID hashCode
     */
    public static int randomUUIDToHashCode() {
        return UUID.randomUUID().hashCode();
    }

    /**
     * 获取随机规则生成 UUID
     *
     * @return 随机规则生成 UUID
     */
    public static UUID getRandomUUID() {
        // 获取随机数
        String random1 = String.valueOf(900000 + new Random().nextInt(10000));
        // 获取随机数
        String random2 = String.valueOf(900000 + new Random().nextInt(10000));
        // 获取当前时间
        String time = System.currentTimeMillis() + random1 + random2;
        // 生成唯一随机 UUID
        return new UUID(time.hashCode(), ((long) random1.hashCode() << 32) | random2.hashCode());
    }

    /**
     * 获取随机规则生成 UUID 字符串
     *
     * @return 随机规则生成 UUID 字符串
     */
    public static String getRandomUUIDToString() {
        return getRandomUUID().toString();
    }
}
