package cn.icutool.service.impl;

import cn.icutool.entity.BlogArticleTags;
import cn.icutool.dao.BlogArticleTagsDao;
import cn.icutool.service.BlogArticleTagsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 文章与标签的多对多关系(BlogArticleTags)表服务实现类
 *
 * @author icutool
 * @since 2024-12-29 22:14:39
 */
@Service("blogArticleTagsService")
public class BlogArticleTagsServiceImpl implements BlogArticleTagsService {
    @Resource
    private BlogArticleTagsDao blogArticleTagsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    @Override
    public BlogArticleTags queryById(Long articleId) {
        return this.blogArticleTagsDao.queryById(articleId);
    }

    /**
     * 分页查询
     *
     * @param blogArticleTags 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<BlogArticleTags> queryByPage(BlogArticleTags blogArticleTags, PageRequest pageRequest) {
        long total = this.blogArticleTagsDao.count(blogArticleTags);
        return new PageImpl<>(this.blogArticleTagsDao.queryAllByLimit(blogArticleTags, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param blogArticleTags 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticleTags insert(BlogArticleTags blogArticleTags) {
        this.blogArticleTagsDao.insert(blogArticleTags);
        return blogArticleTags;
    }

    /**
     * 修改数据
     *
     * @param blogArticleTags 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticleTags update(BlogArticleTags blogArticleTags) {
        this.blogArticleTagsDao.update(blogArticleTags);
        return this.queryById(blogArticleTags.getArticleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long articleId) {
        return this.blogArticleTagsDao.deleteById(articleId) > 0;
    }
}
