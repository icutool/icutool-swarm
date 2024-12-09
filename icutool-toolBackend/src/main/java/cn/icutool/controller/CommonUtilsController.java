package cn.icutool.controller;

import cn.hutool.extra.servlet.ServletUtil;
import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.service.CommonUtilsService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/myIP")
    public AjaxResult checkMyIP(HttpServletRequest request) {
        String clientIP = ServletUtil.getClientIP(request);
        if (StringUtils.isNotEmpty(clientIP)){
            IpInfoDTO ipInfoDTO = commonUtilsService.searchIpInfo(clientIP);
            ipInfoDTO.setIp(clientIP);
            return AjaxResult.success(ipInfoDTO);
        }
        return AjaxResult.error("获取IP失败");
    }
}
