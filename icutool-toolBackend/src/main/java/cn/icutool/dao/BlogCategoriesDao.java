package cn.icutool.dao;

import cn.icutool.entity.BlogCategories;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 存储文章分类(BlogCategories)表数据库访问层
 *
 * @author icutool
 * @since 2024-12-29 22:13:58
 */
public interface BlogCategoriesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogCategories queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param blogCategories 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<BlogCategories> queryAllByLimit(BlogCategories blogCategories, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param blogCategories 查询条件
     * @return 总行数
     */
    long count(BlogCategories blogCategories);

    /**
     * 新增数据
     *
     * @param blogCategories 实例对象
     * @return 影响行数
     */
    int insert(BlogCategories blogCategories);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogCategories> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogCategories> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogCategories> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogCategories> entities);

    /**
     * 修改数据
     *
     * @param blogCategories 实例对象
     * @return 影响行数
     */
    int update(BlogCategories blogCategories);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

