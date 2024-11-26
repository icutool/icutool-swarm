package cn.icutool.domain.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileReq {
    @NotBlank
    @ApiModelProperty("手机号")
    private String mobile;

    @NotBlank
    @ApiModelProperty("验证码")
    private String smsCode;
}
