package cn.algerfan.order.util;

import java.util.Random;

/**
 * @author algerfan
 * @time 2019 17: 59
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return String.valueOf(System.currentTimeMillis() + number);
    }
}
