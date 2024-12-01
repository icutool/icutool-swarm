package cn.icutool.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.util.Date;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_feedback")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFeedback implements Serializable {
    private static final long serialVersionUID = 240334479191290697L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID，用于关联用户
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 用户IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 反馈内容，使用TEXT类型保存可变长度文本
     */
    @TableField("feedback_text")
    private String feedbackText;

    /**
     * 反馈类型（如：0:Bug、1:建议等）
     */
    @TableField("feedback_type")
    private String feedbackType;

    /**
     * 产品类型（如：0:tool程序员工具箱、1:笔记）
     */
    @TableField("product_type")
    private String productType;

    /**
     * 反馈状态：0待处理、1已处理、2关闭
     */
    @TableField("status")
    private String status;

    /**
     * 反馈创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 逻辑删除 0未删除 1删除
     */
    @TableField("del_flag")
    @TableLogic(value = "0", delval = "1")
    private Boolean delFlag;

}

