package cn.icutool.dao;

import cn.icutool.entity.BlogArticleTags;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 文章与标签的多对多关系(BlogArticleTags)表数据库访问层
 *
 * @author icutool
 * @since 2024-12-29 22:14:39
 */
public interface BlogArticleTagsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    BlogArticleTags queryById(Long articleId);

    /**
     * 查询指定行数据
     *
     * @param blogArticleTags 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<BlogArticleTags> queryAllByLimit(BlogArticleTags blogArticleTags, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param blogArticleTags 查询条件
     * @return 总行数
     */
    long count(BlogArticleTags blogArticleTags);

    /**
     * 新增数据
     *
     * @param blogArticleTags 实例对象
     * @return 影响行数
     */
    int insert(BlogArticleTags blogArticleTags);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticleTags> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogArticleTags> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticleTags> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogArticleTags> entities);

    /**
     * 修改数据
     *
     * @param blogArticleTags 实例对象
     * @return 影响行数
     */
    int update(BlogArticleTags blogArticleTags);

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 影响行数
     */
    int deleteById(Long articleId);

}

