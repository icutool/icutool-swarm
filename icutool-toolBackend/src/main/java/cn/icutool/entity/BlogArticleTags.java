package cn.icutool.entity;

import java.io.Serializable;

/**
 * 文章与标签的多对多关系(BlogArticleTags)实体类
 *
 * @author icutool
 * @since 2024-12-29 22:14:39
 */
public class BlogArticleTags implements Serializable {
    private static final long serialVersionUID = -86563467837126685L;
/**
     * 文章ID（外键）
     */
    private Long articleId;
/**
     * 标签ID（外键）
     */
    private Long tagId;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

}

