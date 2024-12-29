package cn.icutool.service.impl;

import cn.icutool.entity.BlogTags;
import cn.icutool.dao.BlogTagsDao;
import cn.icutool.service.BlogTagsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 存储标签(BlogTags)表服务实现类
 *
 * @author icutool
 * @since 2024-12-29 22:14:19
 */
@Service("blogTagsService")
public class BlogTagsServiceImpl implements BlogTagsService {
    @Resource
    private BlogTagsDao blogTagsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogTags queryById(Long id) {
        return this.blogTagsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param blogTags 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<BlogTags> queryByPage(BlogTags blogTags, PageRequest pageRequest) {
        long total = this.blogTagsDao.count(blogTags);
        return new PageImpl<>(this.blogTagsDao.queryAllByLimit(blogTags, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param blogTags 实例对象
     * @return 实例对象
     */
    @Override
    public BlogTags insert(BlogTags blogTags) {
        this.blogTagsDao.insert(blogTags);
        return blogTags;
    }

    /**
     * 修改数据
     *
     * @param blogTags 实例对象
     * @return 实例对象
     */
    @Override
    public BlogTags update(BlogTags blogTags) {
        this.blogTagsDao.update(blogTags);
        return this.queryById(blogTags.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.blogTagsDao.deleteById(id) > 0;
    }
}
