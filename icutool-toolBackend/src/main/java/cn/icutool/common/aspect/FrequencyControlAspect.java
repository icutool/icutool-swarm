package cn.icutool.common.aspect;

import cn.hutool.core.util.StrUtil;
import cn.icutool.common.RequestHolder;
import cn.icutool.common.annotation.FrequencyControl;
import cn.icutool.domain.dto.FrequencyControlDTO;
import cn.icutool.service.frequencycontrol.FrequencyControlUtil;
import cn.icutool.utils.SecurityUtils;
import cn.icutool.utils.SpElUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.icutool.service.frequencycontrol.FrequencyControlStrategyFactory.TOTAL_COUNT_WITH_IN_FIX_TIME_FREQUENCY_CONTROLLER;

/**
 * Description: 频控实现
 * @author xietao
 */
@Slf4j
@Aspect
@Component
public class FrequencyControlAspect {

    @Around("@annotation(cn.icutool.common.annotation.FrequencyControl)||@annotation(cn.icutool.common.annotation.FrequencyControlContainer)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        FrequencyControl[] annotationsByType = method.getAnnotationsByType(FrequencyControl.class);
        Map<String, FrequencyControl> keyMap = new HashMap<>();
        for (int i = 0; i < annotationsByType.length; i++) {
            FrequencyControl frequencyControl = annotationsByType[i];
            //默认方法限定名+注解排名（可能多个）
            String prefix = StrUtil.isBlank(frequencyControl.prefixKey()) ? SpElUtils.getMethodKey(method) + ":index:" + i : frequencyControl.prefixKey();
            String key = "";
            switch (frequencyControl.target()) {
                case EL:
                    key = SpElUtils.parseSpEl(method, joinPoint.getArgs(), frequencyControl.spEl());
                    break;
                case OPENID:
                    key = SpElUtils.parseSpEl(method, joinPoint.getArgs(), frequencyControl.spEl(), WxMpXmlMessage.class).getFromUser();
                    break;
                case UID:
                    key = SecurityUtils.getUserId().toString();
                    break;
                case IP:
                    key = RequestHolder.get().getIp();
            }
            keyMap.put(prefix + ":" + key, frequencyControl);
        }
        // 将注解的参数转换为编程式调用需要的参数
        List<FrequencyControlDTO> frequencyControlDTOS = keyMap.entrySet().stream().map(entrySet -> buildFrequencyControlDTO(entrySet.getKey(), entrySet.getValue())).collect(Collectors.toList());
        // 调用编程式注解
        return FrequencyControlUtil.executeWithFrequencyControlList(TOTAL_COUNT_WITH_IN_FIX_TIME_FREQUENCY_CONTROLLER, frequencyControlDTOS, joinPoint::proceed);
    }

    /**
     * 将注解参数转换为编程式调用所需要的参数
     *
     * @param key              频率控制Key
     * @param frequencyControl 注解
     * @return 编程式调用所需要的参数-FrequencyControlDTO
     */
    private FrequencyControlDTO buildFrequencyControlDTO(String key, FrequencyControl frequencyControl) {
        FrequencyControlDTO frequencyControlDTO = new FrequencyControlDTO();
        frequencyControlDTO.setCount(frequencyControl.count());
        frequencyControlDTO.setTime(frequencyControl.time());
        frequencyControlDTO.setUnit(frequencyControl.unit());
        frequencyControlDTO.setKey(key);
        return frequencyControlDTO;
    }
}
