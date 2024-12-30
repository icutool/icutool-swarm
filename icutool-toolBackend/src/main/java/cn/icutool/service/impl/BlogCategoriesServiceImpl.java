package cn.icutool.service.impl;

import cn.icutool.domain.entity.BlogCategories;
import cn.icutool.mapper.BlogCategoriesMapper;
import cn.icutool.service.BlogCategoriesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * 存储文章分类(BlogCategories)表服务实现类
 *
 * @author icutool
 * @since 2024-12-29 22:13:58
 */
@Service("blogCategoriesService")
public class BlogCategoriesServiceImpl implements BlogCategoriesService {
    private final BlogCategoriesMapper blogCategoriesMapper;

    public BlogCategoriesServiceImpl(BlogCategoriesMapper blogCategoriesMapper) {
        this.blogCategoriesMapper = blogCategoriesMapper;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogCategories queryById(Long id) {
        return this.blogCategoriesMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param blogCategories 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<BlogCategories> queryByPage(BlogCategories blogCategories, PageRequest pageRequest) {
        long total = this.blogCategoriesMapper.count(blogCategories);
        return new PageImpl<>(this.blogCategoriesMapper.queryAllByLimit(blogCategories, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param blogCategories 实例对象
     * @return 实例对象
     */
    @Override
    public BlogCategories insert(BlogCategories blogCategories) {
        this.blogCategoriesMapper.insert(blogCategories);
        return blogCategories;
    }

    /**
     * 修改数据
     *
     * @param blogCategories 实例对象
     * @return 实例对象
     */
    @Override
    public BlogCategories update(BlogCategories blogCategories) {
        this.blogCategoriesMapper.update(blogCategories);
        return this.queryById(blogCategories.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.blogCategoriesMapper.deleteById(id) > 0;
    }
}
