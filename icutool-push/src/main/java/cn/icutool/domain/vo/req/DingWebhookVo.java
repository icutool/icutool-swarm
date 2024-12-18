package cn.icutool.domain.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author no1plus
 * @since 2024-12-18 21:49:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DingWebhookVo {
    private String msgtype;
    private Markdown markdown;
}
