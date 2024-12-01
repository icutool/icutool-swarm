package cn.icutool.domain.vo.request;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFeedbackReq implements Serializable {
    private static final long serialVersionUID = 240334479191290697L;
    /**
     * 用户ID，用于关联用户
     */
    private Integer userId;

    /**
     * 反馈内容，使用TEXT类型保存可变长度文本
     */
    @NotBlank
    private String feedbackText;

    /**
     * 反馈类型（如：0:Bug、1:建议等）
     */
    private String feedbackType;

    /**
     * 产品类型（如：0:tool程序员工具箱、1:笔记）
     */
    @NotBlank
    private String productType;

    /**
     * 邮箱
     */
    private String email;

}

