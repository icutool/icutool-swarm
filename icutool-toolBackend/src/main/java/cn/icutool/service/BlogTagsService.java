package cn.icutool.service;

import cn.icutool.entity.BlogTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 存储标签(BlogTags)表服务接口
 *
 * @author icutool
 * @since 2024-12-29 22:14:19
 */
public interface BlogTagsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogTags queryById(Long id);

    /**
     * 分页查询
     *
     * @param blogTags 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<BlogTags> queryByPage(BlogTags blogTags, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param blogTags 实例对象
     * @return 实例对象
     */
    BlogTags insert(BlogTags blogTags);

    /**
     * 修改数据
     *
     * @param blogTags 实例对象
     * @return 实例对象
     */
    BlogTags update(BlogTags blogTags);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
