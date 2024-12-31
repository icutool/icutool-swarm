package cn.icutool.mapper;

import cn.icutool.domain.dto.ArticleSwitch;
import cn.icutool.domain.dto.BlogDTO;
import cn.icutool.domain.entity.BlogArticles;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 存储文章的主要信息(BlogArticles)表数据库访问层
 *
 * @author icutool
 * @since 2024-12-29 22:13:35
 */
public interface BlogArticlesMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogArticles queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param blogArticles 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<BlogArticles> queryAllByLimit(@Param("blogArticles") BlogArticles blogArticles, @Param("pageable") Pageable pageable);

    BlogDTO queryOne(Long id);

    /**
     * 统计总行数
     *
     * @param keyword 查询条件
     * @return 总行数
     */
    long count(@Param("keyword") String keyword);

    /**
     * 新增数据
     *
     * @param blogArticles 实例对象
     * @return 影响行数
     */
    int insert(BlogArticles blogArticles);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticles> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogArticles> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticles> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogArticles> entities);

    /**
     * 修改数据
     *
     * @param blogArticles 实例对象
     * @return 影响行数
     */
    int update(BlogArticles blogArticles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<BlogDTO> queryPageByLimit(@Param("keyword") String keyword, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    ArticleSwitch selectPrev(Date time);

    ArticleSwitch selectNext(Date time);
}

