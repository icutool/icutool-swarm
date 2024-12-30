package cn.icutool.domain.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 存储文章的主要信息(BlogArticles)实体类
 *
 * @author icutool
 * @since 2024-12-29 22:13:35
 */
public class BlogArticles implements Serializable {
    private static final long serialVersionUID = 703876419950159291L;
    /**
     * 文章ID
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 作者ID（外键）
     */
    private Long authorUid;
    /**
     * 分类ID（外键）
     */
    private Long categoryId;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(Long authorUid) {
        this.authorUid = authorUid;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

