package com.ace.secretscript.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 西野
 * @version V1.0
 * @title Tools.java
 * @package com.xiye.common.util
 * @description 常用工具
 * @date 2020-03-27
 */
public class Tools {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Author 西野
     * @Date 2020-03-27 16:34:06
     * @Description java.security.SecureRandom真随机数
     * 系统将确定在所请求的包中是否有算法实现；如果没有，则抛出异常NoSuchProviderException
     * 如果仅指定算法名称，用SecureRandom random = SecureRandom.getInstance("SHA1PRNG")
     * 如果既指定了算法名称又指定了包提供程序SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN")
     * @Param []
     * @Return int
     */
    public static int getRandomNum() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        /* 生成0~999999的随机数 */
        return random.nextInt(1000000);
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 16:34:18
     * @Description 检测字符串是否不为空(null, " ", " null ")
     * @Param [s]
     * @Return boolean 为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s) {
        return s != null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 16:34:31
     * @Description 检测字符串是否为空(null, " ", " null ")
     * @Param [s]
     * @Return boolean 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 16:34:42
     * @Description 字符串转换为字符串数组
     * @Param [str, splitRegex] str 字符串;splitRegex 分隔符
     * @Return java.lang.String[]
     */
    public static String[] str2StrArray(String str, String splitRegex) {
        if (isEmpty(str)) {
            return new String[0];
        }
        return str.split(splitRegex);
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 16:35:01
     * @Description 用默认的分隔符(, )将字符串转换为字符串数组
     * @Param [str] str 字符串
     * @Return java.lang.String[]
     */
    public static String[] str2StrArray(String str) {
        return str2StrArray(str, ",\\s*");
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 16:35:31
     * @Description 按照参数format的格式，日期转字符串
     * @Param [date, format]
     * @Return java.lang.String
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 16:36:42
     * @Description 验证邮箱
     * @Param [email]
     * @Return boolean
     */
    public static boolean checkEmail(String email) {
        boolean flag;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            LoggerUtil.error(e.toString(), e);
            flag = false;
        }
        return flag;
    }

    /**
     * @Author 西野
     * @Date 2020-03-27 22:56:28
     * @Description Iterable的工具类，用来处理给jdk1.8的lambda表达式forEach增加序列
     * @Param [elements, action]
     * @Return void
     */
    public static <E> void forEach(Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
        }
    }
    /**
     * @Author 西野
     * @Date 2020-03-27 09:52:31
     * @Description 得到32位的uuid
     * @Param []
     * @Return java.lang.String
     */
    public static String get32Uuid() {
        return UUID.randomUUID().toString().trim().replace("-", "");
    }

}
