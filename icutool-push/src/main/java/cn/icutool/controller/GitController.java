package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author xietao
 * @since 2024-12-02 23:07:10
 */
@Slf4j
@RestController
@RequestMapping("/git")
public class GitController {
    @PostMapping("/webhook")
    public AjaxResult webhook(@RequestBody HashMap<String, Object> map) {
        log.info("map = {}" , JSON.toJSONString(map));
        return AjaxResult.success("webhook");
    }
}
