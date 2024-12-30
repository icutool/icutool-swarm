package cn.icutool.service;

import cn.icutool.domain.dto.BlogArticlesDTO;
import cn.icutool.domain.dto.BlogDTO;
import cn.icutool.domain.dto.PageDTO;
import cn.icutool.domain.entity.BlogArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 存储文章的主要信息(BlogArticles)表服务接口
 *
 * @author icutool
 * @since 2024-12-29 22:13:37
 */
public interface BlogArticlesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogArticles queryById(Long id);

    /**
     * 通过ID查询单条页面所需数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogArticlesDTO queryDetailById(Long id);

    /**
     * 分页查询
     *
     * @param keyword 筛选条件
     * @param pageNum      页码
     * @param pageSize      页大小
     * @return 查询结果
     */
    PageDTO<BlogDTO> queryByPage(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param blogArticles 实例对象
     * @return 实例对象
     */
    BlogArticles insert(BlogArticles blogArticles);

    /**
     * 修改数据
     *
     * @param blogArticles 实例对象
     * @return 实例对象
     */
    BlogArticles update(BlogArticles blogArticles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
