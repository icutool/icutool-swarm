package cn.icutool.controller;

import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.CommonUtilsService;
import cn.icutool.service.impl.WebSocketServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * @author xietao
 */
@RestController
@RequestMapping("/util")
@RefreshScope
public class CommonUtilsController {
    private final CommonUtilsService commonUtilsService;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public CommonUtilsController(CommonUtilsService commonUtilsService, KafkaTemplate<String, String> kafkaTemplate) {
        this.commonUtilsService = commonUtilsService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/ip")
    public AjaxResult getIpInfo(@RequestParam @NotBlank String ip) {
        IpInfoDTO ipInfoDTO = commonUtilsService.searchIpInfo(ip);
        return AjaxResult.success(ipInfoDTO);
    }
    @GetMapping("/send")
    public AjaxResult send(@RequestParam @NotBlank String ip) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ip",ip);
        kafkaTemplate.send("icutool-push", JSON.toJSONString(map));
        return AjaxResult.success();
    }
}
