package SXT._12ManualSORMFramework.utils;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装了字符串常用的操作
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:57
 **/
public class StringUtils {
    /**
     * 将目标字符串首字母变成大写
     * @param str 目标字符串
     * @return 首字母变成大写的字符串
     */
    public static String firstCharToUppercase(String str) {
        return str.toUpperCase().substring(0, 1).concat(str.substring(1));
    }
}
