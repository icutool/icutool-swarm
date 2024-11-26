package cn.icutool.mapper;

import cn.icutool.domain.entity.MessageBroker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2024-05-22
 */
public interface MessageBrokerMapper extends BaseMapper<MessageBroker> {
    /**
     *  查询消息列表
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @param userId
     * @return 消息列表
     */
    List<MessageBroker> queryPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("keyword") String keyword, @Param("userId") Long userId);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MessageBroker selectByPrimaryKey(Integer id);

    /**
     * 统计总行数
     *
     * @param messageBroker 查询条件
     * @return 总行数
     */
    long count(MessageBroker messageBroker);

    /**
     * 新增数据
     *
     * @param messageBroker 实例对象
     * @return 影响行数
     */
    int insert(MessageBroker messageBroker);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeeklyReport> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MessageBroker> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeeklyReport> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MessageBroker> entities);

    /**
     * 修改数据
     *
     * @param messageBroker 实例对象
     * @return 影响行数
     */
    int update(MessageBroker messageBroker);

}
