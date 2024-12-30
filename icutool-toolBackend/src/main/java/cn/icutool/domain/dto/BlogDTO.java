package cn.icutool.domain.dto;

import cn.icutool.domain.entity.BlogCategories;
import cn.icutool.domain.entity.BlogTags;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogDTO {
    /**
     * 文章ID
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 作者
     */
    private String authorName;
    /**
     * 分类
     */
    private String category;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章摘要
     */
    private String excerpt;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
