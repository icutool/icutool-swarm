package cn.icutool.utils;

public class IStringUtil {
    public static String removeHtmlTags(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        // 正则表达式匹配 HTML 标签
        return input.replaceAll("<[^>]+>", "");
    }
    // 判断字符串是否是IP
    public static boolean isIP(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        // 正则表达式匹配 IP 地址
        return input.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    }
}
