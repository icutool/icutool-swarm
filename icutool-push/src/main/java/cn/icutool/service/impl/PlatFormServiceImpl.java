package cn.icutool.service.impl;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.icutool.domain.dto.BarkDto;
import cn.icutool.domain.dto.flyBook.ContentBody;
import cn.icutool.domain.dto.flyBook.FlyBookDto;
import cn.icutool.domain.dto.flyBook.MsgTypeAndContent;
import cn.icutool.domain.dto.flyBook.ViewContent;
import cn.icutool.service.PlatFormService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author xietao
 * @since 2024-12-03 22:47:43
 */

@RefreshScope
@Service
@Slf4j
public class PlatFormServiceImpl implements PlatFormService {
    @Value("${bark.token}")
    private String barkToken;
    @Value("${flyBook.url}")
    private String flyBookUrl;
    private static final String barkUrl = "https://api.day.app/push";
    @Override
    public void sendMsgToPlatForm(String msg) {
        BarkDto barkDto = JSON.parseObject(msg, BarkDto.class);
        barkDto.setDevice_key(barkToken);
        pushByPost(barkUrl, JSON.toJSONString(barkDto),"Bark");
        MsgTypeAndContent msgTypeAndContent = new MsgTypeAndContent("text", barkDto.getBody());
        ViewContent viewContent = new ViewContent(barkDto.getTitle(), Collections.singletonList(msgTypeAndContent));
        HashMap<String, ViewContent> viewMap = new HashMap<>();
        viewMap.put("zh_cn", viewContent);
        ContentBody contentBody = new ContentBody(viewMap);
        FlyBookDto flyBookDto = new FlyBookDto("post", contentBody);
        pushByPost(flyBookUrl, JSON.toJSONString(flyBookDto),"飞书");
    }

    private static void pushByPost(String url, String json, String platform) {
        try (HttpResponse execute = HttpUtil.createPost(url).body(json).execute()) {
            String body = execute.body();
            JSONObject sendCallBack = JSONObject.parseObject(body);
            if (sendCallBack.getInteger("code") == 200) {
                log.info("发送{} Post通知成功 返回信息：{}", platform, body);
            } else {
                log.error("发送{} 通知失败 返回信息：{}", platform, body);
            }
        } catch (HttpException e) {
            log.error("发送{} 通知失败 异常信息：{}", platform, e.getMessage(), e);
        }
    }
}
