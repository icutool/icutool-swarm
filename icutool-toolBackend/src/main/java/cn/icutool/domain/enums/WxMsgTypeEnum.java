package cn.icutool.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xietao
 */

@AllArgsConstructor
@Getter
public enum WxMsgTypeEnum {
    /**
     * 其他
     */
    OTHER(-1,"default "),

    /**
     * 登录验证码
     */
    LOGIN(0,"00"),

    /**
     * 日报记录
     */
    DAY_REPORT(1,"提交周报 "),

    /**
     * 消息转发
     */
    MESSAGE_BROKER(2,"提交 "),
    IMAGE_MESSAGE_BROKER(3,"提交图片 "),

    LIST_MESSAGE(4,"list ");

    private final Integer type;

    private final String prefix;


    public static Map<String, WxMsgTypeEnum> MSG_ENUM_MAP = new HashMap<>();

    static {
        MSG_ENUM_MAP = Arrays.stream(WxMsgTypeEnum.values()).collect(Collectors.toMap(WxMsgTypeEnum::getPrefix, Function.identity()));
    }
    public static WxMsgTypeEnum of(Integer type) {
        return MSG_ENUM_MAP.get(type);
    }

}
