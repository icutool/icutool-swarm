package cn.icutool.mapper;

import cn.icutool.domain.entity.BlogTags;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 存储标签(BlogTags)表数据库访问层
 *
 * @author icutool
 * @since 2024-12-29 22:14:19
 */
public interface BlogTagsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogTags queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param blogTags 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<BlogTags> queryAllByLimit(BlogTags blogTags, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param blogTags 查询条件
     * @return 总行数
     */
    long count(BlogTags blogTags);

    /**
     * 新增数据
     *
     * @param blogTags 实例对象
     * @return 影响行数
     */
    int insert(BlogTags blogTags);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogTags> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogTags> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogTags> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogTags> entities);

    /**
     * 修改数据
     *
     * @param blogTags 实例对象
     * @return 影响行数
     */
    int update(BlogTags blogTags);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

