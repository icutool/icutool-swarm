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
 * @author xietao
 * @since 2024-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("message_broker")
@Builder
public class MessageBroker implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息详情
     */
    @TableField("content")
    private String content;

    /**
     * 消息类型 0文本 1图片
     */
    @TableField("msg_type")
    private Integer msgType;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE )
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
    private Boolean delFlag;


}
