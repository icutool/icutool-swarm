package cn.icutool.mapper;

import cn.icutool.domain.AutoReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AutoReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AutoReply record);

    int insertSelective(AutoReply record);

    AutoReply selectByPrimaryKey(Integer id);

    List<AutoReply> selectAll();

    int updateByPrimaryKeySelective(AutoReply record);

    int updateByPrimaryKey(AutoReply record);

    AutoReply selectByQuestion(String question);

    int removeByIds(List<Integer> ids);

    List<AutoReply> queryPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("keyword") String keyword);

    int count(String keyword);
}