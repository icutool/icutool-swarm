package cn.icutool.dao;

import cn.icutool.domain.entity.MessageBroker;
import cn.icutool.mapper.MessageBrokerMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBrokerDao extends ServiceImpl<MessageBrokerMapper, MessageBroker> {
    public List<MessageBroker> queryPage(Integer pageNum, Integer pageSize, String keyword, Long userId){
        return baseMapper.queryPage(pageNum, pageSize, keyword, userId);
    }

    public int count(String keyword, Long userId){
        LambdaQueryWrapper<MessageBroker> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MessageBroker::getUserId, userId);
        // 判断 keyword 是否为空字符串或 null
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like(MessageBroker::getContent, keyword);
        }
        return baseMapper.selectCount(queryWrapper);
    }

    public int update(MessageBroker messageBroker){
        return baseMapper.update(messageBroker);
    }
}
