package cn.icutool.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author <a href="https://icutool.cn">旧年</a>
 * @since 2024-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("weekly_report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工作主题
     */
    @TableField("subject")
    private String subject;

    /**
     * 工作详情
     */
    @TableField("content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 逻辑删除 0未删除 1删除
     */
    @TableField("del_flag")
    @TableLogic(value = "0", delval = "1")
    private Boolean delFlag;

}
