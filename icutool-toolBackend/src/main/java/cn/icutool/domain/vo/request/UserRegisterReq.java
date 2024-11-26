package cn.icutool.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterReq implements Serializable {
    private static final long serialVersionUID = -40356744423868312L;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Size(min = 4, max = 10, message = "用户名长度必须在4到10之间")
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号长度必须为11位")
    private String mobile;

}
