package cn.icutool.service.impl;

import cn.icutool.entity.BlogArticles;
import cn.icutool.dao.BlogArticlesDao;
import cn.icutool.service.BlogArticlesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 存储文章的主要信息(BlogArticles)表服务实现类
 *
 * @author icutool
 * @since 2024-12-29 22:13:37
 */
@Service("blogArticlesService")
public class BlogArticlesServiceImpl implements BlogArticlesService {
    @Resource
    private BlogArticlesDao blogArticlesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogArticles queryById(Long id) {
        return this.blogArticlesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param blogArticles 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<BlogArticles> queryByPage(BlogArticles blogArticles, PageRequest pageRequest) {
        long total = this.blogArticlesDao.count(blogArticles);
        return new PageImpl<>(this.blogArticlesDao.queryAllByLimit(blogArticles, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param blogArticles 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticles insert(BlogArticles blogArticles) {
        this.blogArticlesDao.insert(blogArticles);
        return blogArticles;
    }

    /**
     * 修改数据
     *
     * @param blogArticles 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticles update(BlogArticles blogArticles) {
        this.blogArticlesDao.update(blogArticles);
        return this.queryById(blogArticles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.blogArticlesDao.deleteById(id) > 0;
    }
}
