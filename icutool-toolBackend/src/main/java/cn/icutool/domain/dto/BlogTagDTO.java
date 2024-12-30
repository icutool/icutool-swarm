package cn.icutool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTagDTO {
    /**
     * 标签ID
     */
    private Long articleId;
    /**
     * 标签名称
     */
    private String tagName;
}
