package cn.icutool.domain.enums;

import cn.icutool.domain.bo.WSUserInfoBO;
import cn.icutool.domain.vo.ws.WSAuthorize;
import cn.icutool.domain.vo.ws.WSLoginCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum WSRespTypeEnum {
    TEST_CODE(50, "测试", String.class),
    LOGIN_CODE(51, "登录验证码返回", WSLoginCode.class),
    TOKEN_CODE(52, "登录成功返回token", WSAuthorize.class),
    LOGIN_ERROR(53,"认证失败", String.class),
    LOGIN_SUCCESS(54,"认证成功", WSUserInfoBO.class),
    NEW_MSG(55, "新消息", String.class),
    ;

    private final Integer type;
    private final String desc;
    private final Class dataClass;

    private static final Map<Integer, WSRespTypeEnum> cache;

    static {
        cache = Arrays.stream(WSRespTypeEnum.values()).collect(Collectors.toMap(WSRespTypeEnum::getType, Function.identity()));
    }

    public static WSRespTypeEnum of(Integer type) {
        return cache.get(type);
    }
}
