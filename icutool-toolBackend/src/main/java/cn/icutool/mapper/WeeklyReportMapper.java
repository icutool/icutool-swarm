package cn.icutool.mapper;

import cn.icutool.domain.entity.WeeklyReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 周报Mapper
 * @author <a href="https://icutool.cn">旧年</a>
 * @since 2024-05-21
 */
public interface WeeklyReportMapper extends BaseMapper<WeeklyReport> {

    /**
     *  查询分页数据
     * @return 分页数据
     */
    List<WeeklyReport> queryPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("keyword") String keyword,@Param("userId") Long userId);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeeklyReport selectByPrimaryKey(Integer id);

    /**
     * 统计总行数
     *
     * @param weeklyReport 查询条件
     * @return 总行数
     */
    long count(WeeklyReport weeklyReport);

    /**
     * 新增数据
     *
     * @param weeklyReport 实例对象
     * @return 影响行数
     */
    int insert(WeeklyReport weeklyReport);


    /**
     * 新增数据
     *
     * @param weeklyReport 实例对象
     * @return 影响行数
     */
    int insertSelective(WeeklyReport weeklyReport);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeeklyReport> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WeeklyReport> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeeklyReport> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WeeklyReport> entities);

    /**
     * 修改数据
     *
     * @param weeklyReport 实例对象
     * @return 影响行数
     */
    int update(WeeklyReport weeklyReport);

}
