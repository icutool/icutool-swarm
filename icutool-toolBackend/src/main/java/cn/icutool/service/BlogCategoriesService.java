package cn.icutool.service;

import cn.icutool.entity.BlogCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 存储文章分类(BlogCategories)表服务接口
 *
 * @author icutool
 * @since 2024-12-29 22:13:58
 */
public interface BlogCategoriesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogCategories queryById(Long id);

    /**
     * 分页查询
     *
     * @param blogCategories 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<BlogCategories> queryByPage(BlogCategories blogCategories, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param blogCategories 实例对象
     * @return 实例对象
     */
    BlogCategories insert(BlogCategories blogCategories);

    /**
     * 修改数据
     *
     * @param blogCategories 实例对象
     * @return 实例对象
     */
    BlogCategories update(BlogCategories blogCategories);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
