package cn.icutool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author xietao
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/detail/{articleId}")
    public HashMap detailById(@PathVariable("articleId") String articleId) {
        System.out.println(articleId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", articleId);
        map.put("title", "如何开发一个个人博客？");
        map.put("author", "PB");
        map.put("date", "2024-12-10");
        map.put("wordCount", "8019");
        map.put("readTime", "17");
        map.put("views", "1");
        map.put("tags", new String[]{"BIOS", "配置"});
        map.put("content", "<p>这是文章内容...</p>");
        map.put("updateTime", "2024-12-10");
        map.put("commitId", "#900cee9");
        map.put("licenseType", "CC BY-NC 4.0");
        map.put("license", "https://creativecommons.org/licenses/by-nc/4.0/");
        return map;
    }
}
