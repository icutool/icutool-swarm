package cn.icutool.domain.entity;

import java.io.Serializable;

/**
 * 存储文章分类(BlogCategories)实体类
 *
 * @author icutool
 * @since 2024-12-29 22:13:58
 */
public class BlogCategories implements Serializable {
    private static final long serialVersionUID = 845769511351034759L;
/**
     * 分类ID
     */
    private Long id;
/**
     * 分类名称
     */
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

