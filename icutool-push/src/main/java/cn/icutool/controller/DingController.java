package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.domain.dto.BarkDto;
import cn.icutool.domain.vo.req.DingWebhookVo;
import cn.icutool.domain.vo.req.Markdown;
import cn.icutool.service.PlatFormService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author no1plus
 * @since 2024-12-18 21:43:52
 */
@RestController
@RequestMapping("/ding")
public class DingController {
    private final Logger log = LoggerFactory.getLogger(DingController.class);
    private final PlatFormService platFormService;

    public DingController(PlatFormService platFormService) {
        this.platFormService = platFormService;
    }

    @PostMapping("/push")
    public AjaxResult push(@RequestBody DingWebhookVo dingWebhookVo) {
        log.info("dingWebhookVo = {}" , JSON.toJSONString(dingWebhookVo));
        Markdown markdown = dingWebhookVo.getMarkdown();
        BarkDto barkDto = new BarkDto(markdown.getTitle(), markdown.getText());
        platFormService.sendMsgToPlatForm(barkDto);
        return AjaxResult.success();
    }

}
