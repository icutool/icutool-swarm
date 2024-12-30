package cn.icutool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticlesDTO {
    private BlogDTO article;
    private ArticleSwitch prev;
    private ArticleSwitch next;
}
