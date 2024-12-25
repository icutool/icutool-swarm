package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.domain.dto.BarkDto;
import cn.icutool.domain.vo.req.DingWebhookVo;
import cn.icutool.domain.vo.req.Markdown;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author no1plus
 * @since 2024-12-18 23:28:53
 */
@RestController
@RequestMapping("/common")
public class CommonPushController {
    private final Logger log = LoggerFactory.getLogger(CommonPushController.class);
    @PostMapping("/push")
    public AjaxResult push(@RequestBody HashMap<String, String> params) {
        log.info("dingWebhookVo = {}" , JSON.toJSONString(params));
        return AjaxResult.success();
    }

    @GetMapping("/pushGet")
    public AjaxResult pushGet(@RequestParam String title,
                              @RequestParam String body,
                              @RequestParam String copyStr) {
        log.info("title={} body={} copyStr={}" , title, body, copyStr);
        return AjaxResult.success();
    }
}
