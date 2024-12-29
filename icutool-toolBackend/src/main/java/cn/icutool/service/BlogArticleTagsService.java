package cn.icutool.service;

import cn.icutool.entity.BlogArticleTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 文章与标签的多对多关系(BlogArticleTags)表服务接口
 *
 * @author icutool
 * @since 2024-12-29 22:14:39
 */
public interface BlogArticleTagsService {

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    BlogArticleTags queryById(Long articleId);

    /**
     * 分页查询
     *
     * @param blogArticleTags 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<BlogArticleTags> queryByPage(BlogArticleTags blogArticleTags, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param blogArticleTags 实例对象
     * @return 实例对象
     */
    BlogArticleTags insert(BlogArticleTags blogArticleTags);

    /**
     * 修改数据
     *
     * @param blogArticleTags 实例对象
     * @return 实例对象
     */
    BlogArticleTags update(BlogArticleTags blogArticleTags);

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long articleId);

}
