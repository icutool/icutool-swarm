package cn.icutool.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateReq {

    /**
     * 用户id
     */
    @NotNull
    private Long id;
    /**
     * 用户名
     */
    @Size(min = 4, max = 10, message = "用户名长度必须在4到10之间")
    private String username;
    /**
     * 昵称
     */
    @Size(min = 4, max = 10, message = "昵称长度必须在4到10之间")
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
}
