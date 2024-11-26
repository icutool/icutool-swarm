package cn.icutool.service.strategy.wechatMsg;

import cn.icutool.domain.enums.WxMsgTypeEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.icutool.domain.enums.WxMsgTypeEnum.MSG_ENUM_MAP;

@Slf4j
@Component
public class WechatMsgPrefixContext {

    private final Map<WxMsgTypeEnum, MsgPrefixStrategy> strategyMap;
    private final MsgPrefixStrategy defaultStrategy;

    @Autowired
    public WechatMsgPrefixContext(List<MsgPrefixStrategy> strategies, DefaultPrefixStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
        // 将特定的策略注册到Map中
        strategyMap = strategies.stream()
                .filter(strategy -> !strategy.getKey().equals(WxMsgTypeEnum.OTHER)) // 排除通用策略
                .collect(Collectors.toMap(
                        MsgPrefixStrategy::getKey,
                        strategy -> strategy
                ));
    }

    public WxMpXmlOutMessage handlePrefix(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        WxMsgTypeEnum prefix = validMsgType(wxMessage, weixinService); // 获取字符串的前缀
        log.info("消息策略模式匹配:{}",prefix);
        MsgPrefixStrategy strategy = strategyMap.getOrDefault(prefix, defaultStrategy); // 如果没有匹配到则使用通用处理器
        return strategy.msgHandle(wxMessage, weixinService);
    }

    public WxMsgTypeEnum validMsgType(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        // 获取消息类型
        String msgType = wxMessage.getMsgType();
        // 获取用户消息
        String content = wxMessage.getContent();
        if ("image".equals(msgType)){
            return WxMsgTypeEnum.IMAGE_MESSAGE_BROKER;
        } else if ("text".equals(msgType)){
            if (content.startsWith("00") && content.length() == 6){
                return WxMsgTypeEnum.LOGIN;
            } else {
                WxMsgTypeEnum result = MSG_ENUM_MAP.entrySet().stream()
                        .filter(entry -> content.startsWith(entry.getKey()))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .orElse(WxMsgTypeEnum.OTHER);
                return result;
            }
        }
        return WxMsgTypeEnum.OTHER;
    }
}
