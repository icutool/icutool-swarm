package cn.icutool.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 存储标签(BlogTags)实体类
 *
 * @author icutool
 * @since 2024-12-29 22:14:19
 */
@AllArgsConstructor
@NoArgsConstructor
public class BlogTags implements Serializable {
    private static final long serialVersionUID = 402357844069238190L;
    /**
     * 标签ID
     */
    private Long id;
    /**
     * 标签名称
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

