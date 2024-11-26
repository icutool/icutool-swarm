package cn.icutool.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WxCaptchaVO {
    /**
     * 验证码
     */
    private String code;
    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;
    /**
     * 是否登录
     */
    private Boolean flag;
    /**
     * 用户信息
     */
    private Long userId;
}
