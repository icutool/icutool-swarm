package cn.icutool.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xietao
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {
    @Value("${icutool}")
    private String icutool;
    @GetMapping
    public String test() {
        return icutool;
    }
}
