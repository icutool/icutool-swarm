package cn.icutool.utils;

public class IStringUtil {
    public static String removeHtmlTags(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        // 正则表达式匹配 HTML 标签
        return input.replaceAll("<[^>]+>", "");
    }
}
